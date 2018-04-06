package ru.bellintegrator.practice.organizations.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organizations.dao.OrganizationDAO;
import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.organizations.view.OrganizationFilterView;
import ru.bellintegrator.practice.organizations.view.OrganizationView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private final EntityManager em;

    @Autowired
    public OrganizationDAOImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Organization> getAll(OrganizationFilterView org) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Organization> root = cq.from(Organization.class);
        cq = cq.select(root).distinct(true);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if(org!=null) {
            if (org.getName() != null) {
                predicates.add(qb.equal(root.get("name"), org.getName()));
            }
            if (org.getInn() != null) {
                predicates.add(qb.equal(root.get("inn"), org.getInn()));
            }
            if (org.getIsActive() != null) {
                predicates.add(qb.equal(root.get("isActive"), org.getIsActive()));
            }
            cq.where(qb.and(predicates.toArray(new Predicate[]{})));
        }
        TypedQuery<Organization> query = em.createQuery(cq);
        List<Organization> list = null;
        if(query.getFirstResult()>=0){
            list = query.getResultList();
        }

        return list;
    }

    @Override
    public Organization getOrgById(Long id){
        Organization org = em.find(Organization.class, id);
        return org;
    }

    @Override
    @Transactional
    public Integer updateOrg(Organization org) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<Organization> update = cb.createCriteriaUpdate(Organization.class);
        Root e = update.from(Organization.class);
        if(org!=null) {
            if (org.getId() != null)
                update.where(cb.equal(e.get("id"), org.getId()));
            if (org.getName() != null)
                update.set("name", org.getName());
            if (org.getFullName() != null)
                update.set("fullName", org.getFullName());
            if (org.getInn() != null)
                update.set("inn", org.getInn());
            if (org.getKpp() != null)
                update.set("kpp", org.getKpp());
            if (org.getAddress() != null)
                update.set("address", org.getAddress());
            if (org.getPhone() != null)
                update.set("phone", org.getPhone());
            if (org.getIsActive() != null)
                update.set("isActive", true);
        }
        return this.em.createQuery(update).executeUpdate();
    }

    @Override
    @Transactional
    public Boolean saveOrg(Organization org) {
        em.persist(org);
        return true;

    }

    @Override
    @Transactional
    public void deleteOrg(Long id) {
        em.remove(getOrgById(id));
    }
}
