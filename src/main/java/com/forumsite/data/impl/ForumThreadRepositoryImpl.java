package com.forumsite.data.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.forumsite.data.ForumThreadRepository;
import com.forumsite.model.Comment;
import com.forumsite.model.ForumThread;
import com.forumsite.model.User;

@ApplicationScoped
public class ForumThreadRepositoryImpl extends AbstractJPARepository<ForumThread> 
                                                                implements ForumThreadRepository {
    
    public ForumThreadRepositoryImpl() {
        super(ForumThread.class);
    }

    @Override
    public Optional<ForumThread> getByName(String threadName) {
        try{
            return Optional
                          .ofNullable(
                                (ForumThread) em().createQuery("SELECT t "
                                                               + "FROM ForumThread t "
                                                                     + "INNER JOIN FETCH t.comments tc "
                                                                     + "INNER JOIN FETCH t.author ta "
                                                                     + "INNER JOIN FETCH tc.author ca "
                                                               + "WHERE t.name =:name")
                                                  .setParameter("name", threadName)
                                                  .setHint("org.hibernate.cacheable", false)
                                                  .getSingleResult());
        }catch(NoResultException nre){}
        return Optional.empty();
    }

    @Override
    public void add(ForumThread thread,String firstMessage,String username) {
        User author = (User) em().createNamedQuery("User.findByName")
                                 .setParameter("username", username)
                                 .getSingleResult();
        Comment c = new Comment(firstMessage);
        c.setAuthor(author);
        c.setThread(thread);
        Date time = new Date();
        c.setLastUpdate(time);
        thread.setAuthor(author);
        thread.addComments(c);
        thread.setLastUpdate(time);
        em().persist(thread);
    }

    @Override
    public List<ForumThread> list() {
        CriteriaBuilder cb = cb();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        crit.orderBy(cb.desc(r.get("lastUpdate")));
        
        return em().createQuery(crit)
                   .setFirstResult(0)
                   .setMaxResults(20)
                   .setHint("org.hibernate.cacheable", "true")
                   .getResultList();
    }

    @Override
    public List<ForumThread> search(String threadName) {
        final String threadNameParam = prepareLikeQueryParam(threadName);
        
        return listWhere((cb,r,query) ->{
                                         return cb.like(cb.lower(r.get("name")), threadNameParam);
                                        }, 
                                        0,15, null,true);
    }

    @Override
    public List<ForumThread> search(String threadName, final String category) {
        final String threadNameParam = prepareLikeQueryParam(threadName);
        
        return listWhere((cb,r,query) ->{
                                         Predicate namePredicate = cb.like(cb.lower(r.get("name")), threadNameParam);
                                         Predicate categoryPredicate = cb.equal(r.get("category"), category);                                         
                                         return cb.and(namePredicate,categoryPredicate);
                                        }, 
                                        0,15, null, true);
    }
    
    @Override
    public List<ForumThread> getCategory(String category) {
        return listWhere((cb,root,query) -> {
                                             query.orderBy(cb.desc(root.get("lastUpdate")));
                                             return cb.equal(root.get("category"), category);
                                            }, 
                                            0,20, null, true);
    }
    
    @Override
    public List<ForumThread> latest() {
        
        CriteriaBuilder cb = cb();
        CriteriaQuery<ForumThread> crit = cb.createQuery(ForumThread.class);
        Root<ForumThread> r = crit.from(ForumThread.class);
        crit.orderBy(cb.desc(r.get("lastUpdate")));
        
        return em().createQuery(crit)
                   .setHint("javax.persistence.fetchgraph", entityGraph("graph.ForumThread.author"))
                   .setHint("org.hibernate.cacheable", "true")
                   .setMaxResults(5)
                   .getResultList();
    }
    
    @Override
    public void updateHits(Map<Long, Long> hits) {
       Query q = em().createQuery("UPDATE ForumThread t SET t.hits = :hits WHERE t.id = :id");
       for(Entry<Long, Long> e : hits.entrySet()){
           q.setParameter("hits", e.getValue())
            .setParameter("id", e.getKey())
            .executeUpdate();
       }
    }

    @Override
    public boolean checkName(String threadname) {
        Query q = em().createQuery("SELECT 1  FROM ForumThread t where t.name=:name")
                      .setParameter("name", threadname);     
        try{
            q.getSingleResult();
        }catch(NoResultException nre){
            return true;
        }
        return false;
    }
    
}
