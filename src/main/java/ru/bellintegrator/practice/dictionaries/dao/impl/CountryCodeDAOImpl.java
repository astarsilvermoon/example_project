package ru.bellintegrator.practice.dictionaries.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionaries.dao.CountryCodeDAO;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        List<CountryCode> list = null;
        if(query.getFirstResult()>=0){
            list = query.getResultList();
        }
        return list;
    }

    @Override
    public CountryCode getByNameAndCode(String name, String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CountryCode> criteria = builder.createQuery(CountryCode.class);
        Root<CountryCode> root = criteria.from(CountryCode.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if(name!= null && !name.equals(""))
            predicates.add(builder.equal(root.get("name"), name));

        if(code!= null && !code.equals(""))
            predicates.add(builder.equal(root.get("code"), code));

        criteria.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        TypedQuery<CountryCode> query = em.createQuery(criteria);
        CountryCode countryCode = null;
            if(query.getFirstResult()>=0){
             countryCode = query.getSingleResult();
        }
            return countryCode;
    }
}
