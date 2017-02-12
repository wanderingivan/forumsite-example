package com.forumsite.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name="Users")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username","email"}))
public class User implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -7803845757682579173L;

    @Id
    @GeneratedValue
    @Column(name="id",nullable=false,updatable=false)
    private Long id;
    
    @NotNull
    @Size(min = 5, max = 25)
    @Column
    private String username;
    
    @NotNull
    @Size(min = 5, max = 50)
    @Column
    private String password;
    
    @NotNull
    @Column
    @Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}") //XXX Weak method of validating email change
    private String email;
    
    @Column
    @Max(255)
    private String description;
    
    @Column
    private String imageName;
    
    @OneToMany(mappedBy="author",cascade=CascadeType.REMOVE)
    private List<Thread> threads; 
    
    @OneToMany(mappedBy="author",cascade=CascadeType.REMOVE)
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
        builder.append("User [getId()=").append(getId()).append(", getUsername()=").append(getUsername())
               .append(", getPassword()=").append(getPassword()).append(", getEmail()=").append(getEmail())
               .append(", getDescription()=").append(getDescription()).append("]");
        return builder.toString();
    }

    public Long getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return "[Omitted]";
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
    
    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addThread(Thread t){
        List<Thread> threads = getThreads();
        if(threads == null){
            threads = new ArrayList<Thread>();
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
