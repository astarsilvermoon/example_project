package ru.bellintegrator.practice.offices;

import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.organizations.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
    public List<Office> getOfficesByOrgId(Long id) { //большой большой вопрос
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

        Root<Office> office = criteria.from(Office.class);
        Join<Office,Organization> join = office.join("organization", JoinType.LEFT );
        criteria.where(builder.equal(office.get("orgId"), id));

        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();
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

    @Override
    public void deleteOffice(Office office) {
        em.getTransaction().begin();
        em.remove(office);
        em.getTransaction().commit();
    }

    public void main(String[] args){ // оттестить
        List<Office> offices = getOfficesByOrgId(1L);
        for(Office office : offices){
            System.out.println(office. getName() + "-" + office.getOrganizationId());
        }
    }


}
