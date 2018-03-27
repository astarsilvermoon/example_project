package ru.bellintegrator.practice.dictionaries.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionaries.dao.CountryCodeDAO;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class CountryCodeDAOImpl implements CountryCodeDAO{

    private final EntityManager em;

    @Autowired
    public CountryCodeDAOImpl (EntityManager em){
        this.em = em;
    }

    @Override
    public List<CountryCode> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CountryCode> criteria = builder.createQuery(CountryCode.class);

        Root<CountryCode> root = criteria.from(CountryCode.class);
        TypedQuery<CountryCode> query = em.createQuery(criteria);
        return query.getResultList();
    }
}
