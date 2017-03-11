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


public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    private ExceptionHandler exceptionHandler;
    
    private FacesContext context;
    
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

        context = FacesContext.getCurrentInstance();
        while (queue.hasNext()){

            try {
                Throwable throwable = extractThrowable((ExceptionQueuedEventContext) queue.next()
                                                                                      .getSource());
                System.err.println("Exception: " + throwable.getMessage());

                Map<String, Object> requestMap = getRequestMap();
                
                if(exceptionTypeExpired(throwable)){ handleViewExpiredError(requestMap,throwable);}
                else if(exceptionTypeSecurity(throwable)){ handleAccessDeniedError(requestMap);}
                else{ handleGenericError(requestMap); }
                
                switchToErrorView();
                context.renderResponse();
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
        requestMap.put("error-title", "View Expired");
        requestMap.put("error-type","404");
        requestMap.put("error-message", "Sorry this view has expired");
        requestMap.put("error-link-message", "Main Page");
        requestMap.put("error-link-src", "main.jsf");
        requestMap.put("return-link-src",((ViewExpiredException) t).getViewId());
        
    }
    
    private void handleAccessDeniedError(Map<String, Object> requestMap){
        requestMap.put("error-title", "Access Denied");
        requestMap.put("error-type","403");
        requestMap.put("error-message", "Sorry you must be logged in to proceed");
        requestMap.put("error-link-message", "Login");
        requestMap.put("error-link-src", "login.jsf");
        
    }
    
    private void handleGenericError(Map<String, Object> requestMap){
        requestMap.put("error-title", "Oops");
        requestMap.put("error-type","404");
        requestMap.put("error-message", "Sorry something went wrong");
        requestMap.put("error-link-message", "Go back");
        requestMap.put("error-link-src", "main.jsf.jsf");
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
        return context.getExternalContext()
                      .getRequestMap();
    }

    private void switchToErrorView(){
        switchOutputView("/error.jsf");
    }
    
    private void switchOutputView(String pageName){
        context.getApplication()
               .getNavigationHandler()
               .handleNavigation(context, null, pageName);
    }
}
