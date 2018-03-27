package ru.bellintegrator.practice.offices.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;
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
    public List<Office> getOfficesByOrgId(Long orgId, OfficeView office) throws Exception {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

        Root<Office> root = criteria.from(Office.class);
        Join<Office,Organization> join = root.join("organization", JoinType.LEFT );
        //Constructing list of parameters
        List<Predicate> predicates = new ArrayList<Predicate>();

        //Adding predicates in case of parameter not being null
        if (office.getName() != null) {
            predicates.add(builder.equal(root.get("name"), office.getName()));
        }
        if (office.getPhone() != null) {
            predicates.add(builder.equal(root.get("phone"), office.getPhone()));
        }
        if (office.isActive() != null) {
            predicates.add(builder.equal(root.get("isActive"), office.isActive())); //is_active?
        }
        //query itself
             criteria.where(predicates.toArray(new Predicate[]{}));
        if(orgId != null)
            criteria.where(builder.equal(root.get("orgId"), orgId));
        else  throw new Exception("Organization id is NULL'");


        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Office getOfficeById(Long id) throws Exception {
        if(id!=null)
            return em.find(Office.class, id);
        else {
            throw new Exception("Office id is NULL'");
        }
    }
    //ToDo в сервисе проверки на нуль и перевод из вью в модель
    @Override
    public Integer updateOffice(OfficeView office) {
       /* em.getTransaction().begin();
        em.merge(office);
        em.getTransaction().commit();*/

        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        // create update
        CriteriaUpdate<Office> update = cb.createCriteriaUpdate(Office.class);
        // set the root class
        Root e = update.from(Office.class);
        // set update and where clause
        if(office.getId() != null)
            update.where(cb.equal(e.get("id"), office.getId()));
        if(office.getName() != null)
            update.set("name", office.getName());
        if(office.getAddress() != null)
            update.set("address", office.getAddress());
        if(office.getPhone() != null)
            update.set("phone", office.getPhone());
        if(office.getActive() != null)
            update.set("isActive" , office.getActive());

        // perform update
       return this.em.createQuery(update).executeUpdate();
    }

    //ToDo сделать save
    @Override
    public void saveOffice(OfficeView office) {
        em.persist(office);

    }

    @Override
    public Integer deleteOffice(Long id) {
        /*em.getTransaction().begin();
        em.remove(office);
        em.getTransaction().commit();*/

        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaDelete<Office> remove = cb.createCriteriaDelete(Office.class);
        Root e = remove.from(Office.class);
        // set update and where clause
        if(id != null)
            remove.where(cb.equal(e.get("id"), id));
      return this.em.createQuery(remove).executeUpdate();

    }
}
