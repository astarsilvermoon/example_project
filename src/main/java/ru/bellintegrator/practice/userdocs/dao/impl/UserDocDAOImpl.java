package ru.bellintegrator.practice.userdocs.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.userdocs.dao.UserDocDAO;
import ru.bellintegrator.practice.userdocs.model.UserDoc;
import ru.bellintegrator.practice.users.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class UserDocDAOImpl implements UserDocDAO {

    private final EntityManager em;

    @Autowired
    public UserDocDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Integer deleteUserDocs(Long userId) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaDelete<UserDoc> remove = cb.createCriteriaDelete(UserDoc.class);
        Root e = remove.from(UserDoc.class);

        if(userId != null)
            remove.where(cb.equal(e.get("user").get("id"), userId));
        return this.em.createQuery(remove).executeUpdate();
    }

    @Override
    public UserDoc getDocsByUser(Long userId) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Organization> root = cq.from(UserDoc.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        cq = cq.select(root).distinct(true).orderBy(cb.desc(root.get("id")));

        if(userId!=null) {
            predicates.add(cb.equal(root.get("user").get("id"), userId));
            cq.where(cb.and(predicates.toArray(new Predicate[]{})));

        }
        TypedQuery<UserDoc> query = em.createQuery(cq);
        UserDoc userDoc = null;
        if(query.getFirstResult()>=0)
            userDoc = query.getSingleResult();
        return userDoc;
    }
}
