package ru.bellintegrator.practice.dictionaries;

import ru.bellintegrator.practice.dictionaries.dao.CountryCodeDAO;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CountryCodeDAOImpl implements CountryCodeDAO{

    private final EntityManager em;

    public CountryCodeDAOImpl (EntityManager em){
        this.em = em;
    }

    @Override
    public List<CountryCode> getAll() {
        TypedQuery<CountryCode> query = em.createQuery("SELECT cc FROM CountryCode cc", CountryCode.class);
        return query.getResultList();
    }
}
