package ru.bellintegrator.practice.offices.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.offices.view.OfficeFilterView;
import ru.bellintegrator.practice.offices.view.OfficeView;
import ru.bellintegrator.practice.organizations.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
@Repository
public class OfficeDAOImpl implements OfficeDAO {
    private final EntityManager em;

    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> getOfficesByOrgId(OfficeFilterView office) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

        Root<Office> root = criteria.from(Office.class);
        criteria = criteria.select(root).distinct(true);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(office.getOrgId() != null) {
            Join<Office, Organization> join = root.join("organizationId", JoinType.LEFT);//?
            predicates.add(builder.equal(join.get("id"), office.getOrgId()));
        } else  return null;

        if(office!=null) {

            if (office.getName() != null) {
                predicates.add(builder.equal(root.get("name"), office.getName()));
            }
            if (office.getPhone() != null) {
                predicates.add(builder.equal(root.get("phone"), office.getPhone()));
            }
            if (office.getIsActive() != null) {
                predicates.add(builder.equal(root.get("isActive"), office.getIsActive()));
            }
            criteria.where(builder.and(predicates.toArray(new Predicate[]{})));
        }

        TypedQuery<Office> query = em.createQuery(criteria);
        List<Office> list = null;
        if(query.getFirstResult()>=0){
            list = query.getResultList();
        }
        return list;
    }

    @Override
    public Office getOfficeById(Long id){
        Office office = em.find(Office.class, id);
        return office;
    }
    @Override
    @Transactional
    public Integer updateOffice(Office office) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<Office> update = cb.createCriteriaUpdate(Office.class);
        Root e = update.from(Office.class);
        if(office.getId() != null)
            update.where(cb.equal(e.get("id"), office.getId()));
        if(office.getName() != null)
            update.set("name", office.getName());
        if(office.getAddress() != null)
            update.set("address", office.getAddress());
        if(office.getPhone() != null)
            update.set("phone", office.getPhone());
        if(office.getIsActive() != null)
            update.set("isActive" , office.getIsActive());
        return this.em.createQuery(update).executeUpdate();
    }

    @Override
    @Transactional
    public Boolean saveOffice(Office office) {
        em.persist(office);
        return true;
    }

    @Override
    @Transactional
    public void deleteOffice(Long id) {
        em.remove(getOfficeById(id));
    }
}
