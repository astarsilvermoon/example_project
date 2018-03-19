package ru.bellintegrator.practice.dictionaries;

import ru.bellintegrator.practice.dictionaries.dao.DocTypeDAO;
import ru.bellintegrator.practice.dictionaries.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DocTypeDAOImpl implements DocTypeDAO {

    private final EntityManager em;


    public DocTypeDAOImpl (EntityManager em){
        this.em = em;
    }

    @Override
    public List<DocType> getAll() {
        TypedQuery<DocType> query = em.createQuery("SELECT dt FROM DocType dt", DocType.class);
        return query.getResultList();
    }
}
