package ru.bellintegrator.practice.offices;

import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
public class OfficeDAOImpl implements OfficeDAO {
    private final EntityManager em;

    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> getOfficesByOrgId(Long id) {
        return null; //?
    }

    @Override
    public Office getOfficeById(Long id) {
        return em.find(Office.class, id);
    }

    @Override
    public void updateOffice(Office office) {
        em.getTransaction().begin();
        em.merge(office);
        em.getTransaction().commit();
    }

    @Override
    public void saveOffice(Office office) {
        em.persist(office);
    }
}
