package com.forumsite.data.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
        return (ForumThread) em.createQuery("SELECT t "
                                            + "FROM ForumThread t "
                                                 + "INNER JOIN FETCH t.comments tc "
                                                 + "INNER JOIN FETCH t.author ta "
                                                 + "INNER JOIN FETCH tc.author ca "
                                           + "WHERE t.name =:name")
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        crit.orderBy(cb.desc(r.get("createdOn")));
        
        return em.createQuery(crit)
                 .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph.ForumThread.author"))
                 .setMaxResults(10)
                 .getResultList();
    }

    @Override
    public List<ForumThread> searchThreads(String threadName) {

        CriteriaQuery<ForumThread> crit = prepareSearchQuery(threadName, null);

        return em.createQuery(crit)
                 .setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.ForumThread.author"))
                 .getResultList();
    }

    @Override
    public List<ForumThread> searchThreads(String threadName, String category) {

        CriteriaQuery<ForumThread> crit = prepareSearchQuery(threadName, category);
    
        return em.createQuery(crit)
                 .setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.ForumThread.author"))
                 .getResultList();
    }
    
    @Override
    public List<ForumThread> loadCategory(String category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        crit.where(cb.equal(r.get("category"),category)); 
        
        return em.createQuery(crit)
                 .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph.ForumThread.associations"))
                 .setMaxResults(10)
                 .getResultList();
    }
    
    @Override
    public void delete(long id) {
        em.remove(findThread(id));
    }

    @Override
    public List<ForumThread> latest() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        crit.orderBy(cb.desc(r.get("lastUpdate")));
        
        return em.createQuery(crit)
                 .setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.ForumThread.author"))
                 .setMaxResults(5)
                 .getResultList();
    }
    
    private CriteriaQuery<ForumThread> prepareSearchQuery(String name,String category){

        String query = prepareNameParam(name);
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        Predicate namePredicate = cb.like(cb.lower(r.get("name")), query);
        if(category != null){
            Predicate categoryPredicate = cb.and(cb.equal(r.get("category"), category));
            crit.where(namePredicate,categoryPredicate);
        }else{
            crit.where(namePredicate);
        }
        
        return crit;
    }
    
    private String prepareNameParam(String threadname){
       return new StringBuilder("%").append(threadname)
                                    .append("%")
                                    .toString()
                                    .toLowerCase(); 
    }
    
}
