package ru.bellintegrator.practice.organizations.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organizations.dao.OrganizationDAO;
import ru.bellintegrator.practice.organizations.model.Organization;
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
    public List<Organization> getAll(OrganizationView org) { //на вход вьюшка фильтра вместо OrganizationView
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Organization> root = cq.from(Organization.class);

        //Constructing list of parameters
        List<Predicate> predicates = new ArrayList<Predicate>();

        //Adding predicates in case of parameter not being null
        if (org.getName() != null) {
            predicates.add(qb.equal(root.get("name"), org.getName()));
        }
        if (org.getInn() != null) {
            predicates.add(qb.equal(root.get("inn"), org.getInn()));
        }
        if (org.isActive() != null) {
            predicates.add(qb.equal(root.get("isActive"), org.isActive())); //is_active?
        }
        //query itself
        cq.select(root).where(predicates.toArray(new Predicate[]{}));
        //execute query and do something with result
        TypedQuery<Organization> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Organization getOrgById(Long id) throws Exception {
        if(id!=null)
        return em.find(Organization.class, id);
        else {
            throw new Exception("Organization id is NULL'");
        }
    }

    //ToDo в сервисе проверки на нуль и перевод из вью в модель
    @Override
    public Integer updateOrg(OrganizationView org) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        // create update
        CriteriaUpdate<Organization> update = cb.createCriteriaUpdate(Organization.class);
        // set the root class
        Root e = update.from(Organization.class);
        // set update and where clause
        if(org.getId() != null)
            update.where(cb.equal(e.get("id"), org.getId()));
        if(org.getName() != null)
            update.set("name", org.getName());
        if(org.getFullName() != null)
            update.set("fullName", org.getFullName());
        if(org.getInn() != null)
            update.set("inn", org.getInn());
        if(org.getKpp() != null)
            update.set("kpp", org.getKpp());
        if(org.getAddress() != null)
            update.set("address", org.getAddress());
        if(org.getPhone() != null)
            update.set("phone", org.getPhone());
        if(org.getActive() != null)
            update.set("isActive" , true);

        // perform update
        return this.em.createQuery(update).executeUpdate();

      /*  em.getTransaction().begin();
        em.merge(org);
        em.getTransaction().commit();*/
    }

    //ToDo сделать метод save
    @Override
    public void saveOrg(OrganizationView org) {
       em.persist(org);

    }

    @Override
    public Integer deleteOrg(Long id) {
      /*  em.getTransaction().begin();
        em.remove(org);
        em.getTransaction().commit(); */

       CriteriaBuilder cb = this.em.getCriteriaBuilder();
        // create update
        CriteriaDelete<Organization> remove = cb.createCriteriaDelete(Organization.class);
        // set the root class
        Root e = remove.from(Organization.class);
        // set update and where clause
        if(id != null)
            remove.where(cb.equal(e.get("id"), id));
        return this.em.createQuery(remove).executeUpdate();
    }
}
