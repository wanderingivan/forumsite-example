package com.forumsite.data.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.forumsite.data.ForumThreadRepository;
import com.forumsite.model.Comment;
import com.forumsite.model.ForumThread;
import com.forumsite.model.User;

@ApplicationScoped
public class ForumThreadRepositoryImpl implements ForumThreadRepository {
    
    @Inject
    private EntityManager em;

    @Override
    public ForumThread findThread(long id) {
        return em.find(ForumThread.class, id);
    }

    @Override
    public ForumThread getThreadByName(String threadName) {
        return em.createNamedQuery("ForumThread.findByName",ForumThread.class)
                 .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph.ForumThread.associations"))
                 .setParameter("name", threadName)
                 .getSingleResult();

    }

    //XXX Should be replaced by a stored procedure
    @Override
    public void save(ForumThread thread,String firstMessage,String username) {
        User author = (User) em.createNamedQuery("User.findByName")
                .setParameter("username", username)
                .getSingleResult();
        Comment c = new Comment(firstMessage);
        c.setAuthor(author);
        c.setThread(thread);
        thread.setAuthor(author);
        thread.addComments(c);
        em.persist(thread);
    }

    @Override
    public void update(ForumThread thread) {
        em.merge(thread);
    }

    @Override
    public List<ForumThread> list() {
        @SuppressWarnings("rawtypes")
        EntityGraph graph = em.getEntityGraph("graph.ForumThread.associations");
        System.out.println(graph);
        //HashMap<String, EntityGraph> hints = new HashMap<>();
        //hints.put("javax.persistence.fetchgraph",graph);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        crit.orderBy(cb.desc(r.get("createdOn")));
        return em.createQuery(crit)
                 .setHint("javax.persistence.fetchgraph",graph)
                 .setMaxResults(10)
                 .getResultList();
    }

    @Override
    public List<ForumThread> searchThreads(String threadName) {
        StringBuilder sb = new StringBuilder("%").append(threadName).append("%");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        crit.where(cb.like(r.get("name"), sb.toString()));
        return em.createQuery(crit).getResultList();
    }

    @Override
    public List<ForumThread> loadCategory(String category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        crit.where(cb.like(r.get("category"),category)); 
        return em.createQuery(crit)
                 .setMaxResults(10)
                 .getResultList();
    }
    
    @Override
    public void delete(long id) {
        em.remove(findThread(id));
    }



}
