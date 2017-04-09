package com.forumsite.web.comment;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.forumsite.model.Comment;
import com.forumsite.service.CommentManagement;

import java.io.Serializable;

@Named
@Stateful
@ConversationScoped
public class CommentEditController implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3711072965560922295L;

    @Inject
    private CommentManagement cmgmt;
    
    @Inject
    private transient Logger logger;
    
    @Inject
    private Conversation conversation;
    
    private Comment comment;
    
    private long commentId;
    
    public void fetchComment(){
        if(comment == null){
            conversation.begin();
            this.comment = cmgmt.getComment(commentId).get();//XXX
        }
    }
    
    public String updateComment(){
        if(logger.isDebugEnabled()){
            logger.debug("Editing comment " + comment);
        }
        cmgmt.updateComment(this.comment);
        conversation.end();
        return "loadThread?faces-redirect=true&threadname=".concat(this.comment
                                                                       .getThread()
                                                                       .getName());
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
    
}
