package ru.bellintegrator.practice.dictionaries.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionaries.dao.DocTypeDAO;
import ru.bellintegrator.practice.dictionaries.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class DocTypeDAOImpl implements DocTypeDAO {

    private final EntityManager em;

    @Autowired
    public DocTypeDAOImpl (EntityManager em){
        this.em = em;
    }

    @Override
    public List<DocType> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteria = builder.createQuery(DocType.class);

        Root<DocType> root = criteria.from(DocType.class);
        TypedQuery<DocType> query = em.createQuery(criteria);
        return query.getResultList();
    }
}
