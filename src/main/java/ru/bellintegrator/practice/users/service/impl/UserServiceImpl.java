package ru.bellintegrator.practice.users.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.userdocs.model.UserDoc;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.service.UserService;
import ru.bellintegrator.practice.users.view.UserView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Alena on 26.03.2018.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<UserView> getUsersByOfficeId(Long officeId, UserView user) {
        try {
          List<User> list =  dao.getAll(officeId, user);

        Function<User, UserView> mapU = u -> {
            UserView view = new UserView();
            view.id = u.getId();
            view.firstName = u.getFirstName();
            view.lastName = u.getLastName();
            view.middleName = u.getMiddleName();
            view.position = u.getPosition();

            log.info("Get users by office id");
            log.info(view.toString());
            return view;
        };
        return list.stream().map(mapU).collect(Collectors.toList());
    }catch(Exception e){
            log.error(e.getMessage());
        e.printStackTrace();
    }
        return null;
    }

    @Override
    public UserView getById(Long id) {
        try {
        User user= dao.getUserById(id);

            UserView view = new UserView();
            if(user.getId()!= null)
                view.setId(user.getId());
            else{
                log.error("User id is NULL");
                throw new Exception("User id is NULL");
            }

            view.setFirstName(user.getFirstName()!= null ? user.getFirstName() : "");
            view.setLastName(user.getLastName()!= null ? user.getLastName() : "");
            view.setMiddleName(user.getMiddleName()!= null ? user.getMiddleName() : "");
            view.setFirstName(user.getFirstName()!= null ? user.getFirstName() : "");

            view.setPosition(user.getPosition()!= null ? user.getPosition() : "");
            view.setPhone(user.getPhone()!= null ? user.getPhone() : "");

            UserDoc doc = user.getUserDocs().get(0);
            if(doc!= null)
            view.setDocName(doc.getDocType().getName()!= null ? doc.getDocType().getName() : "");

            view.setDocNumber(doc.getDocNumber()!=null ? doc.getDocNumber() : "");
            view.setDocDate(doc.getDocDate());//?

            view.setCitizenshipName(doc.getCountryCode().getName()!= null ? doc.getCountryCode().getName() : "");
            view.setCitizenshipCode(doc.getCountryCode().getCode()!= null ? doc.getCountryCode().getCode() : "");
            view.setIdentified(user.getIdentified()!=null ? true : false);

            log.info(view.toString());
            return view;


        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(UserView view) {
        boolean res = checkRes(dao.updateUser(view));
        if(!res)
            log.error("Update user is false");
        else  log.info("Update user is successed");
        return res;
    }

    @Override
    public boolean delete(Long id) {
        if(id != null){
            boolean res = checkRes(dao.deleteUser(id));
            if(!res)
                log.error("Delete user is false");
            else log.info("Delete user successed");
            return res;
        }
        else return false;
    }

    //ToDo сделать save
    @Override
    public boolean save(UserView view) {
        dao.saveUser(view);
        return false;
    }

    public boolean checkRes(int val){
        if(val > 0)
            return true;
        else return false;
    }
}
