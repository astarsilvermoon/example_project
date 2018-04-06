package ru.bellintegrator.practice.users.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;
import ru.bellintegrator.practice.dictionaries.model.DocType;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.userdocs.model.UserDoc;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.view.UserFilterView;
import ru.bellintegrator.practice.users.view.UserView;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */

@Repository
public class UserDAOImpl implements UserDAO {
    private final EntityManager em;


    @Autowired
    public UserDAOImpl(EntityManager em){
        this.em=em;
    }

    @Override
    public List<User> getAll(Long officeId, UserFilterView user)  {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Organization> root = cq.from(User.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        cq = cq.select(root).distinct(true);

        if(officeId!= null)
            predicates.add(qb.equal(root.get("office").get("id"), officeId));

        if(user!=null) {

            if (user.getFirstName() != null) {
                predicates.add(qb.equal(root.get("firstName"), user.getFirstName()));
            }
            if (user.getLastName() != null) {
                predicates.add(qb.equal(root.get("lastName"), user.getLastName()));
            }
            if (user.getMiddleName() != null) {
                predicates.add(qb.equal(root.get("middleName"), user.getMiddleName()));
            }
            if (user.getPosition() != null) {
                predicates.add(qb.equal(root.get("position"), user.getPosition()));
            }
            if(user.getCitizenshipCode()  != null || user.getDocCode()!=null) {
                Join<User, UserDoc> userDocs = root.join("userDoc");
                if (user.getDocCode() != null) {
                    Join<UserDoc, DocType> docType = userDocs.join("docType");
                    predicates.add(qb.equal(docType.get("code"), user.getDocCode())); //?
                }
                if (user.getCitizenshipCode() != null) {
                    Join<UserDoc, CountryCode> countryCode = userDocs.join("countryCode");
                    predicates.add(qb.equal(countryCode.get("code"), user.getCitizenshipCode()));
                }
            }
        }
        cq.where(qb.and(predicates.toArray(new Predicate[]{})));
        TypedQuery<User> query = em.createQuery(cq);
        List<User> users = null;
        if(query.getFirstResult()>=0)
            users = query.getResultList();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User user = em.find(User.class, id);
        return user;

    }

    @Override
    @Transactional
    public Integer updateUser(User user) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
        Root e = update.from(User.class);
        if(user!=null) {
            if (user.getId() != null)
                update.where(cb.equal(e.get("id"), user.getId()));
            if (user.getFirstName() != null)
                update.set("firstName", user.getFirstName());
            if (user.getLastName() != null)
                update.set("lastName", user.getLastName());
            if (user.getMiddleName() != null)
                update.set("middleName", user.getMiddleName());
            if (user.getPosition() != null)
                update.set("position", user.getPosition());
            if (user.getPhone() != null)
                update.set("phone", user.getPhone());
        }
        return this.em.createQuery(update).executeUpdate();
    }

    @Override
    @Transactional
    public Boolean saveUser(User user) {
        em.persist(user);
        return  true;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        em.remove(getUserById(id));
    }

}
