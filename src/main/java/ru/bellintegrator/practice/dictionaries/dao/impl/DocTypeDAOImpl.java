package ru.bellintegrator.practice.dictionaries.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionaries.dao.DocTypeDAO;
import ru.bellintegrator.practice.dictionaries.model.DocType;

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
        List<DocType> list = null;
        if(query.getFirstResult()>=0) {
            list = query.getResultList();
        }
        return list;
    }

    @Override
    public DocType getByNameAndCode(String name, String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteria = builder.createQuery(DocType.class);
        Root<DocType> root = criteria.from(DocType.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if(name!= null && !name.equals(""))
        predicates.add(builder.equal(root.get("name"), name));

        if(code!= null && !code.equals(""))
            predicates.add(builder.equal(root.get("code"), code));

        criteria.select(root).where(builder.and(predicates.toArray(new Predicate[]{})));
        TypedQuery<DocType> query = em.createQuery(criteria);
        DocType docType = null;
        if(query.getFirstResult()>=0) {
            docType = query.getSingleResult();
        }
        return docType;
    }
}
