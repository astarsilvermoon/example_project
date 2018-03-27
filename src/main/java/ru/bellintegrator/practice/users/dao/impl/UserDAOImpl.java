package ru.bellintegrator.practice.users.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;
import ru.bellintegrator.practice.dictionaries.model.DocType;
import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.userdocs.model.UserDoc;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.view.UserView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public List<User> getAll(Long officeId, UserView user) throws Exception { //ждем фильтровую вьюшку

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Organization> root = cq.from(User.class);
        Join<User, UserDoc> userDocs = root.join("userDocs"); //? точно ли такое название
        Join<UserDoc, DocType> docType =  userDocs.join("docType");
        Join<UserDoc, CountryCode> countryCode = userDocs.join("countryCode");

        //Constructing list of parameters
        List<Predicate> predicates = new ArrayList<Predicate>();

        //Adding predicates in case of parameter not being null
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

        if (user.getDocCode() != null) {
            predicates.add(qb.equal(docType.get("code"), user.getDocCode())); //?
        }
        if (user.getCitizenshipCode() != null) {
            predicates.add(qb.equal(countryCode.get("code"), user.getCitizenshipCode())); //?
        }

        //query itself
        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        if(officeId!= null)
            cq.where(qb.equal(root.get("officeId"), officeId));
        else throw  new Exception("Office id is NULL");
        //execute query and do something with result
        TypedQuery<User> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) throws Exception {
        if(id!=null){
            return em.find(User.class, id);
        }else {
            throw new Exception("User with this id doesn't exist");
        }

    }

    @Override
    public Integer updateUser(UserView user) {
       /* em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        */

        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        // create update
        CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
        // set the root class
        Root e = update.from(User.class);
        Join<User, UserDoc> userDocs = e.join("userDocs"); //? точно ли такое название
        Join<UserDoc, DocType> docType =  userDocs.join("docType");
        Join<UserDoc, CountryCode> countryCode = userDocs.join("countryCode");

        if(user.getId() != null)
            update.where(cb.equal(e.get("id"), user.getId()));
        if(user.getFirstName() != null)
            update.set("firstName", user.getFirstName());
        if(user.getLastName() != null)
            update.set("lastName", user.getLastName());
        if(user.getMiddleName() != null)
            update.set("middleName", user.getMiddleName());
        if(user.getPosition() != null)
            update.set("position", user.getPosition());
        if(user.getPhone() != null)
            update.set("phone", user.getPhone());
        if(user.isIdentified() != null)
            update.set("isIdentified" , true);

        if(user.getDocName()!=null)
            update.set("name", user.getDocName()); //? docName
        if(user.getDocNumber()!=null)
            update.set("DocNumber", user.getDocNumber()); //? DocNumber
        if(user.getDocDate()!=null)
            update.set("docDate", user.getDocDate()); //? docDate
        if(user.getCitizenshipName()!=null)
            update.set("name", user.getCitizenshipName()); //? citizenshipName
        if(user.getCitizenshipCode()!=null)
            update.set("code", user.getCitizenshipCode()); //? citizenshipCode
        // perform update
        return this.em.createQuery(update).executeUpdate();
    }

    @Override
    public void saveUser(UserView user) {
        em.persist(user);
    }

    @Override
    public Integer deleteUser(Long id) {
       /* em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        */
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaDelete<User> remove = cb.createCriteriaDelete(User.class);
        Root e = remove.from(User.class);
        // set update and where clause
        if(id != null)
            remove.where(cb.equal(e.get("id"), id));
        return this.em.createQuery(remove).executeUpdate();
    }
}
