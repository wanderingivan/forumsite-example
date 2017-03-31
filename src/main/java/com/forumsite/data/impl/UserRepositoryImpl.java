package com.forumsite.data.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.forumsite.data.UserRepository;
import com.forumsite.model.User;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Inject
    private EntityManager em;
    
    public UserRepositoryImpl(){}
    
    @Override
    public User getUserByName(String username) {

        return em.createNamedQuery("User.findByName",User.class)
                 .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph.User.fetchComments"))
                 .setParameter("username", username)
                 .getSingleResult();
    }

    @Override
    public List<User> listUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> crit = cb.createQuery(User.class);
        Root<User> r = crit.from(User.class);
        crit.orderBy(cb.desc(r.get("id")));
        return em.createQuery(crit).getResultList();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(long userId) {
        em.remove(findUser(userId));
    }

    @Override
    public User findUser(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> searchUsers(String username) {
        StringBuilder sb = new StringBuilder("%").append(username).append("%");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> crit = cb.createQuery(User.class);
        Root<User> r = crit.from(User.class);
        crit.where(cb.like(cb.lower(r.get("username")), sb.toString().toLowerCase()));
        return em.createQuery(crit).getResultList();
    }

}
