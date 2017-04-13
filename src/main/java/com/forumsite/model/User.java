package com.forumsite.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.QueryHint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.picketlink.idm.permission.annotations.AllowedOperation;
import org.picketlink.idm.permission.annotations.AllowedOperations;

@AllowedOperations({
    @AllowedOperation(value="update", mask=1)
})
@Entity(name="Users")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username","email"}))
@NamedQueries({
        @NamedQuery(name="User.findByName",
                    query="SELECT u FROM Users u WHERE u.username= :username",
                    hints = { @QueryHint(name = "org.hibernate.cacheable", value ="true")})
})
@NamedEntityGraphs({
    @NamedEntityGraph(name="graph.User.fetchComments",
                      attributeNodes={@NamedAttributeNode(value="comments", subgraph="subgraph.comment.associations")},
                      subgraphs={@NamedSubgraph(name="subgraph.comment.associations",
                                                attributeNodes={@NamedAttributeNode("thread")})})
})
@Cacheable(true)
public class User extends Identity implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -7803845757682579173L;

    @Id
    @GeneratedValue
    @Column(name="id",nullable=false,updatable=false)
    private Long id;
    
    @NotNull(message="{field.required}")
    @Size(min = 5, max = 25,message="{field.between}")
    @Column
    private String username;
    
    @Transient
    private String password;
    
    @NotNull
    @Column
    @Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}",message="{email.valid}") //XXX Weak method of validating email change
    private String email;
    
    @Column
    @Size(max=255,message="field.maxsize")
    private String description;
    
    @Column
    private String imageName;
    
    @OneToMany(mappedBy="author",orphanRemoval=true)
    private List<ForumThread> threads; 
    
    @OneToMany(mappedBy="author",orphanRemoval=true)
    private List<Comment> comments;
    
    public User() {
        super();
    }

    public User(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, String description, String imageName) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.description = description;
        this.imageName = imageName;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", username=").append(username).append(", email=").append(email)
               .append(", description=").append(description).append("]");
        return builder.toString();
    }

    public long getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    public List<ForumThread> getThreads() {
        return threads;
    }

    public void setThreads(List<ForumThread> threads) {
        this.threads = threads;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addThread(ForumThread t){
        List<ForumThread> threads = getThreads();
        if(threads == null){
            threads = new ArrayList<ForumThread>();
            setThreads(threads);
        }
        threads.add(t);
    }
    
    public void addComments(Comment c){
        List<Comment> comments = getComments();
        if(comments == null){
            comments = new ArrayList<Comment>();
            setComments(comments);
        }
        comments.add(c);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        User other = (User) obj;
        if (password == null) {
            if (other.password != null) return false;
        }
        else if (!password.equals(other.password)) return false;
        if (username == null) {
            if (other.username != null) return false;
        }
        else if (!username.equals(other.username)) return false;
        return true;
    }
    
}
