package com.forumsite.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1088066767659678206L;

    @Id
    @GeneratedValue
    @Column(name="id",nullable=false,updatable=false)
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="thread_id")
    private ForumThread thread;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="comment_author_id")
    private User author;
    
    @NotNull
    @Size(min=1,max=225)
    @Column
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    
    public Comment() {
        super();
    }

    public Comment(String message){
        super();
        this.message = message;
    }
    
    public Comment(ForumThread thread, User author, String message) {
        this(message);
        this.thread = thread;
        this.author = author;
    }

    public ForumThread getThread() {
        return thread;
    }

    public void setThread(ForumThread thread) {
        this.thread = thread;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Comment [id=").append(id).append(", thread_id=").append(thread.getId()).append(", author=").append(author.getUsername())
               .append(", message=").append(message).append(", lastUpdate=").append(lastUpdate).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((thread == null) ? 0 : thread.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Comment other = (Comment) obj;
        if (author == null) {
            if (other.author != null) return false;
        }
        else if (!author.equals(other.author)) return false;
        if (message == null) {
            if (other.message != null) return false;
        }
        else if (!message.equals(other.message)) return false;
        if (thread == null) {
            if (other.thread != null) return false;
        }
        else if (!thread.equals(other.thread)) return false;
        return true;
    }
    
}
