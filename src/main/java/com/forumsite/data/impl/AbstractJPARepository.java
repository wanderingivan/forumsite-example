package com.forumsite.data.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.forumsite.data.Repository;
import com.forumsite.data.util.PredicateBuilder;
import com.forumsite.model.Identity;

public abstract class AbstractJPARepository<T extends Identity> implements Repository<T>{

    protected Class<T> type;

    @Inject
    private EntityManager em;
    
    public AbstractJPARepository(){}
    
    public AbstractJPARepository(Class<T> type){
        this.type = type;
    }
    
    @Override
    public void add(T t){
        em.persist(t);
    }
    
    @Override
    public void update(T t){
        em.merge(t);
    }
    
    @Override
    public Optional<T> get(long id){
        return Optional.ofNullable(em.find(type, id));
    }
    
    protected Optional<T> getWhere(PredicateBuilder<T> pb, EntityGraph<T> graph){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(type);
        Root<T> root = q.from(type);
        q.where(pb.build(cb, root,q));
        try{
            return Optional.ofNullable(em.createQuery(q)
                                         .getSingleResult());
        }catch(NoResultException nre){}

        return Optional.empty();
    }
    
    protected List<T> listWhere(PredicateBuilder<T> pb,int maxResults, EntityGraph<?> graph){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(type);
        Root<T> root = q.from(type);
        q.where(pb.build(cb, root,q));
        TypedQuery<T> tq =  em.createQuery(q);
        if(maxResults > 0){ tq.setMaxResults(maxResults); }
        if(graph != null){ tq.setHint("javax.persistence.fetchgraph", graph); }
        
        return tq.getResultList();
    }
    
    @Override
    public void delete(long userId) {
        em.remove(get(userId).get());
    } 
       
    protected EntityManager em(){
        return this.em;
    }
    
    protected CriteriaBuilder cb(){
        return em.getCriteriaBuilder();
    }
    
    protected String prepareLikeQueryParam(String param){
       return new StringBuilder("%").append(param)
                                    .append("%")
                                    .toString()
                                    .toLowerCase(); 
    }
    
    protected EntityGraph<?> entityGraph(String graphName){
        return em.getEntityGraph(graphName);
    }
}
