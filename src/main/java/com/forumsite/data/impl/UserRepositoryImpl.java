package com.forumsite.data.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.forumsite.data.UserRepository;
import com.forumsite.model.User;

@ApplicationScoped
public class UserRepositoryImpl extends AbstractJPARepository<User> implements UserRepository {

    public UserRepositoryImpl(){
        super(User.class);
    }
    
    @Override
    public Optional<User> getByName(String username) {
        try{
            return Optional.ofNullable(
                    em().createNamedQuery("User.findByName",User.class)
                        .setHint("javax.persistence.fetchgraph",em().getEntityGraph("graph.User.fetchComments"))
                        .setParameter("username", username)
                        .getSingleResult());
        }catch(NoResultException nre){}
        return Optional.empty();
    }

    @Override
    public List<User> list() {
        CriteriaBuilder cb = cb();
        CriteriaQuery<User> crit = cb.createQuery(User.class);
        Root<User> r = crit.from(User.class);
        crit.orderBy(cb.desc(r.get("id")));
        return em().createQuery(crit).setMaxResults(20).getResultList();
    }

    @Override
    public List<User> search(String username) {
        final String usernameParam = new StringBuilder("%").append(username)
                                                   .append("%")
                                                   .toString()
                                                   .toLowerCase();

        return listWhere((cb,r,query) -> { return cb.like(
                                                   cb.lower(r.get("username")),
                                                   usernameParam
                                                  );
                                         }
                                          ,15,null);
    }

}
