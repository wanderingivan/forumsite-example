package com.forumsite.data.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.forumsite.data.CommentRepository;
import com.forumsite.model.Comment;
import com.forumsite.model.ForumThread;
import com.forumsite.model.User;

@ApplicationScoped
public class CommentRepositoryImpl implements CommentRepository {

    @Inject
    private EntityManager em;
    
    //NOTE preliminary method should be replaced by stored procedure
    @Override
    public void createComment(Comment c, long thread_id, String username) {
        ForumThread t = em.find(ForumThread.class, thread_id);
        User author = (User) em.createNamedQuery("User.findByName")
                               .setParameter("username", username)
                               .getSingleResult();
        c.setAuthor(author);
        c.setThread(t);
        em.persist(c);
    }

    @Override
    public Comment retrieveComment(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public void updateComment(long commentId, String message) {
        em.createQuery("UPDATE Comment c SET c.message = :message WHERE c.id = :id")
          .setParameter("message", message)
          .setParameter("id",commentId)
          .executeUpdate();
    }

    @Override
    public void delete(long commentId) {
        em.createQuery("DELETE FROM Comment c WHERE c.id = :id")
          .setParameter("id",commentId)
          .executeUpdate();
    }

}
