package com.forumsite.web.comment;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.forumsite.model.Comment;
import com.forumsite.service.CommentManagement;
import java.io.Serializable;

@Named
@SessionScoped
public class CommentEditController implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3711072965560922295L;

    @Inject
    private CommentManagement cmgmt;
    
    @Inject
    private transient Logger logger;
    
    private Comment comment;
    
    private long commentId;
    
    public void fetchComment(){
        this.comment = cmgmt.getComment(commentId);
    }
    
    public String updateComment(){
        if(logger.isDebugEnabled()){
            logger.debug("Editing comment " + comment);
        }
        cmgmt.updateComment(this.comment);
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
