package ru.bellintegrator.practice.accounts.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.accounts.dao.AccountDAO;
import ru.bellintegrator.practice.accounts.model.Account;
import ru.bellintegrator.practice.accounts.view.AccountView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private final EntityManager em;

    @Autowired
    public AccountDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Boolean saveAccount(Account account) {
        em.persist(account);
        return true;
    }

    @Override
    public List<Account> findAccount(Account account) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Account> root = cq.from(Account.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        cq = cq.select(root);
        if(account!=null) {
            if (account.getLogin() != null)
                predicates.add(qb.equal(root.get("login"), account.getLogin()));
           /* if (account.getPassword() != null)
                predicates.add(qb.equal(root.get("password"), account.getPassword()));*/
          /*  if (account.getActivationCode() != null)
                predicates.add(qb.equal(root.get("activationCode"), account.getActivationCode()));*/

        cq.where(qb.and(predicates.toArray(new Predicate[]{})));
        }
        TypedQuery<Account> query = em.createQuery(cq);
        List<Account> list = null;
        if(query.getFirstResult()>=0){
            list = query.getResultList();
        }
        return list;
    }

    @Override
    public Account findActivationCode(String code) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Account> root = cq.from(Account.class);
        cq.select(root).where(qb.equal(root.get("activationCode"), code));
        TypedQuery<Account> query = em.createQuery(cq);

        Account account = null;
        if(query.getFirstResult()>=0){
            account = query.getSingleResult();
        }
        return account;
    }

    @Override
    @Transactional
    public Boolean updateAccount(Account account) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<Account> update = cb.createCriteriaUpdate(Account.class);
        Root e = update.from(Account.class);

        if(account!=null) {
            if (account.getId() != null)
                update.where(cb.equal(e.get("id"), account.getId()));
            if (account.getLogin() != null)
                update.set("login", account.getLogin());
            if (account.getPassword() != null)
                update.set("password", account.getPassword());
            if (account.getActivated() != null)
                update.set("isActivated", account.getActivated());
        }
            int result = this.em.createQuery(update).executeUpdate();

        return (result>0);
    }
}
