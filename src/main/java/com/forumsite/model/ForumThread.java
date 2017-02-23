package com.forumsite.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@NamedQueries({
    @NamedQuery(name="ForumThread.findByName",
                query="SELECT ft FROM ForumThread ft where ft.name = :name")
})
@NamedEntityGraphs({
    @NamedEntityGraph(name="graph.ForumThread.author",
                  attributeNodes={@NamedAttributeNode("author")}),
    @NamedEntityGraph(name="graph.ForumThread.associations",
                  attributeNodes={@NamedAttributeNode("author"),
                                  @NamedAttributeNode("comments")})
})
public class ForumThread implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -5542128382563301005L;

    @Id
    @GeneratedValue
    @Column(name="id",nullable=false,updatable=false)
    private Long id;
    
    @NotNull    
    @Size(max=50)
    @Column
    private String name;
    
    @NotNull
    @Pattern(regexp="\\w+")// XXX Change this regex when cat names are specified
    @Column
    private String category;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="author_id")
    private User author;
    
    @OneToMany(mappedBy="thread",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Comment> comments;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    public ForumThread() {
        super();
    }
    
    public ForumThread(String name, String category) {
        super();
        this.name = name;
        this.category = category;
    }

    public ForumThread(String name, String category, User author) {
        this(name,category);
        this.author = author;
    }
    
    public Long getId() {
        return id;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComments(Comment c){
        List<Comment> comments = getComments();
        if(comments == null){
            comments = new ArrayList<Comment>();
            setComments(comments);
        }
        comments.add(c);
    }
    
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ForumThread other = (ForumThread) obj;
        if (category == null) {
            if (other.category != null) return false;
        }
        else if (!category.equals(other.category)) return false;
        if (name == null) {
            if (other.name != null) return false;
        }
        else if (!name.equals(other.name)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Thread [id=").append(id).append(", name=").append(name).append(", category=").append(category)
               .append(", author=").append(author).append(", lastUpdate=").append(lastUpdate).append(", createdOn=")
               .append(createdOn).append("]");
        return builder.toString();
    }
    
}
