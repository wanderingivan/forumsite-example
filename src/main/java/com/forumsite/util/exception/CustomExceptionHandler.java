package com.forumsite.util.exception;

import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.deltaspike.security.api.authorization.AccessDeniedException;
import org.apache.log4j.Logger;


public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    
    private Logger logger = Logger.getLogger(CustomExceptionHandler.class);
    
    private ExceptionHandler exceptionHandler;
    
    private FacesContext ctx = FacesContext.getCurrentInstance();
    
    public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
            this.exceptionHandler = exceptionHandler;
    }
   

    @Override
    public ExceptionHandler getWrapped() {
        return exceptionHandler;
    }
    
    @Override
    public void handle() throws FacesException{
        final Iterator<ExceptionQueuedEvent> queue = getUnhandledExceptionQueuedEvents().iterator();

       
        while (queue.hasNext()){

            try {
                Throwable throwable = extractThrowable((ExceptionQueuedEventContext) queue.next()
                                                                                      .getSource());
                logger.error("Exception: " + throwable.getMessage());

                Map<String, Object> requestMap = getRequestMap();
                
                if(exceptionTypeExpired(throwable)){ handleViewExpiredError(requestMap,throwable);}
                else if(exceptionTypeSecurity(throwable)){ handleAccessDeniedError(requestMap);}
                else{ handleGenericError(requestMap); }
                
                switchToErrorView();
                ctx.renderResponse();
            } finally {
                queue.remove();
            }
        }
    }

    private Throwable extractThrowable(ExceptionQueuedEventContext exceptionQueuedEventContext){
        Throwable throwable = exceptionQueuedEventContext.getException();
        while( exceptionTypeWrapper(throwable) && throwable.getCause() != null){
            throwable = throwable.getCause();
        }
        return throwable;
    }
    
    private void handleViewExpiredError(Map<String, Object> requestMap,Throwable t){
        requestMap.put("error-title", getMessage("expired_view_title"));
        requestMap.put("error-type","404");
        requestMap.put("error-message", getMessage("expired_view_message"));
        requestMap.put("error-link-message", getMessage("main_view"));
        requestMap.put("error-link-src", "main.jsf");
        requestMap.put("return-link-src",((ViewExpiredException) t).getViewId());
        
    }
    
    private void handleAccessDeniedError(Map<String, Object> requestMap){
        requestMap.put("error-title", getMessage("access_denied_title"));
        requestMap.put("error-type","403");
        requestMap.put("error-message", getMessage("access_denied_message"));
        requestMap.put("error-link-message", getMessage("login"));
        requestMap.put("error-link-src", "login.jsf");
        
    }
    
    private void handleGenericError(Map<String, Object> requestMap){
        requestMap.put("error-title", getMessage("generic_error_title"));
        requestMap.put("error-type","404");
        requestMap.put("error-message", getMessage("generic_error_message"));
        requestMap.put("error-link-message", getMessage("main_view"));
        requestMap.put("error-link-src", "main.jsf");
    }
    
    private boolean exceptionTypeExpired(Throwable throwable){
        return throwable instanceof ViewExpiredException;
    }
    
    private boolean exceptionTypeWrapper(Throwable throwable){
        return throwable instanceof FacesException || throwable instanceof EJBException 
                                                   || throwable instanceof EJBTransactionRolledbackException;
    }

    private boolean exceptionTypeSecurity(Throwable throwable){
        return throwable instanceof AccessDeniedException || throwable instanceof org.picketlink.http.AccessDeniedException;
    }
    
    private Map<String,Object> getRequestMap(){
        return ctx.getExternalContext()
                      .getRequestMap();
    }

    private void switchToErrorView(){
        switchOutputView("/error.jsf");
    }
    
    private void switchOutputView(String pageName){
        ctx.getApplication()
               .getNavigationHandler()
               .handleNavigation(ctx, null, pageName);
    }
    
    private String getMessage(String key){
        return ctx.getApplication()
                  .getResourceBundle(ctx, "msg")
                  .getString(key);
    }

}
