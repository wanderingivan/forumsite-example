package com.forumsite.data.impl;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.forumsite.data.CommentRepository;
import com.forumsite.model.Comment;
import com.forumsite.model.ForumThread;
import com.forumsite.model.User;

@ApplicationScoped
public class CommentRepositoryImpl extends AbstractJPARepository<Comment> implements CommentRepository {

    
    public CommentRepositoryImpl(){
        super(Comment.class);
    }
    
    @Override
    public void add(Comment c, String threadName, String username) {
        ForumThread t = (ForumThread) em().createNamedQuery("ForumThread.findByName")
                                          .setParameter("name", threadName)
                                          .getSingleResult();
        User author = (User) em().createNamedQuery("User.findByName")
                                 .setParameter("username", username)
                                 .getSingleResult();
        c.setAuthor(author);
        c.setThread(t);
        c.setLastUpdate(new Date());
        add(c);
        t.addComments(c);
        author.addComments(c);
    }

    @Override
    public void update(long commentId, String message) {
        em().createQuery("UPDATE Comment c SET c.message = :message WHERE c.id = :id")
            .setParameter("message", message)
            .setParameter("id",commentId)
            .executeUpdate();
    }

    @Override
    public void delete(long commentId) {
        em().createQuery("DELETE FROM Comment c WHERE c.id = :id")
            .setParameter("id",commentId)
            .executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> list(){
        return (List<Comment>) em().createQuery("FROM Comment c JOIN FETCH c.author ORDER BY lastUpdate DESC")
                                   .setMaxResults(20)
                                   .getResultList();
    }

    @Override
    public List<Comment> getForThread(String threadName) {
        return listWhere((cb,root,query)->{ return cb.equal(root.get("thread").get("name"),threadName); }, 
                                                   0,0,null,true);
    }
 
}
