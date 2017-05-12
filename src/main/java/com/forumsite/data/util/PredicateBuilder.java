package com.forumsite.data.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.forumsite.model.Identity;

@FunctionalInterface
public interface PredicateBuilder<T extends Identity> {

    Predicate build(CriteriaBuilder cb,Root<T> root,CriteriaQuery<T> query);
}
