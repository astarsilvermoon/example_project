package ru.bellintegrator.practice.organizations;

import ru.bellintegrator.practice.organizations.dao.OrganizationDAO;
import ru.bellintegrator.practice.organizations.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
public class OrganizationDAOImpl implements OrganizationDAO {

    private final EntityManager em;


    public OrganizationDAOImpl(EntityManager em){
       this.em = em;
    }

    @Override
    public List<Organization> getAll() {
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);
        return query.getResultList();
    }

    @Override
    public Organization getOrgById(Long id) {
        return em.find(Organization.class, id);
    }

    @Override
    public void updateOrg(Organization org) {
        em.getTransaction().begin();
        em.merge(org);
        em.getTransaction().commit();
    }

    @Override
    public void saveOrg(Organization org) {
        em.persist(org);
    }

    @Override
    public void deleteOrg(Organization org) {
        em.getTransaction().begin();
        em.remove(org);
        em.getTransaction().commit();
    }
}
