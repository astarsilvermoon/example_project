package ru.bellintegrator.practice.users.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dictionaries.dao.CountryCodeDAO;
import ru.bellintegrator.practice.dictionaries.dao.DocTypeDAO;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;
import ru.bellintegrator.practice.dictionaries.model.DocType;
import ru.bellintegrator.practice.exceptions.exceptions.UserException;
import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.userdocs.dao.UserDocDAO;
import ru.bellintegrator.practice.userdocs.model.UserDoc;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.service.UserService;
import ru.bellintegrator.practice.users.view.UserFilterView;
import ru.bellintegrator.practice.users.view.UserView;

import java.util.*;
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
    private final DocTypeDAO docTypeDAO;
    private final CountryCodeDAO countryCodeDAO;
    private final OfficeDAO officeDAO;
    private final UserDocDAO userDocDAO;

    @Autowired
    public UserServiceImpl(UserDAO dao, DocTypeDAO docTypeDAO, CountryCodeDAO countryCodeDAO, OfficeDAO officeDAO, UserDocDAO userDocDAO) {
        this.docTypeDAO = docTypeDAO;
        this.dao = dao;
        this.countryCodeDAO = countryCodeDAO;
        this.officeDAO = officeDAO;
        this.userDocDAO = userDocDAO;
    }

    @Override
    public List<UserView> getUsersByOfficeId(Long officeId, UserFilterView user) {
        if(officeId!=null) {
            List<User> list = dao.getAll(officeId, user);
            if (list != null ) {
                Function<User, UserView> mapU = u -> {
                    UserView view = new UserView();
                    view.id = u.getId();
                    view.firstName = u.getFirstName();
                    view.lastName = u.getLastName();
                    view.middleName = u.getMiddleName();
                    view.position = u.getPosition();

                    log.info("Получение списка работников у офиса с id=" + officeId);
                    log.info(view.toString());
                    return view;
                };
                return list.stream().map(mapU).collect(Collectors.toList());
            } else throw new UserException("Не найдены работники с идентификатором офиса=" + officeId);
        }else throw new UserException("Офис с идентификатором " + officeId + " не найден");
    }

    @Override
    public UserView getById(Long id) {
        User user = null;
        UserView view = null;
           if(id!=null) {
               user = dao.getUserById(id);
               if(user!=null) {
                   view = setUserViewFromUser(user);
               }else throw  new UserException("Пользователь с id=" + id + "не существует");
           }else{
                throw new UserException("Идентификатор пользователя NULL");
            }
            return view;

    }

    @Override
    public boolean update(UserView view) {
        boolean res = false;
           User finded = dao.getUserById(view.getId());//то что нашли в базе
           if(finded!=null && view!=null){

            User user = setUserFromView(finded, view);
                if(user!=null) {
                     res = checkRes(dao.updateUser(user));
                    if (!res)
                        throw  new UserException("Не удалось обновить данные о работнике");
                    else log.info("Update user is successed");
                    return res;
                }
        }else throw new UserException("Введенные данные пусты. Обновить невозможно");
        return res;
    }

    @Override
    public boolean delete(Long id) {
        if(id != null) {
            User finded = dao.getUserById(id);//то что нашли в базе
            if(finded!=null) {
                dao.deleteUser(id);
                log.info("Удаление работника прошло успешно");
            }else throw  new UserException("Не удалось найти работника с id=" + id);
        }else  throw  new UserException("Не удалось удалить работника. Идентификатор NULL");
            return true;
    }

    @Override
    public boolean save(UserView view) {
           if(view!=null) {
           User user = setUserFromView(new User(), view);
           if(user!=null) {
               dao.saveUser(user);
               log.info("Сохранение данных работника прошло успешно");
           }
       }else throw  new UserException("Введенные данные пусты");
        return true;//проверку
    }

    public boolean checkRes(int val){
        if(val > 0)
            return true;
        else return false;
    }


    public User setUserFromView(User user,UserView view){
        user.setFirstName(view.getFirstName()!=null ? view.getFirstName() : null);
        user.setLastName(view.getLastName()!=null ? view.getLastName() : null);
        user.setMiddleName(view.getMiddleName()!= null ? view.getMiddleName() : null);

        user.setPosition(view.getPosition()!= null ? view.getPosition() : null);
        user.setPhone(view.getPhone()!= null ? view.getPhone() : null);
        user.setIdentified(true);

        if(view.getOfficeId()!=null) {
            try {
                Office office = officeDAO.getOfficeById(view.getOfficeId());
                if(office!=null)
                    user.setOffice(office);

                List<User> users = dao.getAll(office.getId(), new UserFilterView(office.getId(),  user.getFirstName(), user.getLastName(),
                         user.getMiddleName(), user.getPosition(), null, null));
                if(users!=null){
                    UserDoc userDoc = userDocDAO.getDocsByUser(users.get(0).getId());
                    if(userDoc!=null) {
                        userDoc.setDocDate(view.getDocDate());
                        userDoc.setUser(user);
                        userDoc.setDocNumber(view.getDocNumber());

                        DocType docType = docTypeDAO.getByNameAndCode(view.getDocCode(),view.getDocName());
                        if(docType!=null)
                            userDoc.setDocType(docType);

                        CountryCode countryCode = countryCodeDAO.getByNameAndCode(view.getCitizenshipCode(), view.getCitizenshipName());
                        if(countryCode!=null)
                            userDoc.setCountryCode(countryCode);

                        user.setUserDoc(userDoc);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }


    public UserView setUserViewFromUser(User user) {
        UserView view = new UserView();
        if(user.getId()!=null)
            view.setId(user.getId());

        view.setFirstName(user.getFirstName()!= null ? user.getFirstName() : "");
        view.setLastName(user.getLastName()!= null ? user.getLastName() : "");
        view.setMiddleName(user.getMiddleName()!= null ? user.getMiddleName() : "");

        view.setPosition(user.getPosition()!= null ? user.getPosition() : "");
        view.setPhone(user.getPhone()!= null ? user.getPhone() : "");

        UserDoc doc = user.getUserDoc();
            if (doc != null) {
                view.setDocName(doc.getDocType().getName() != null ? doc.getDocType().getName() : "");

                view.setDocNumber(doc.getDocNumber() != null ? doc.getDocNumber() : "");
                view.setDocDate(doc.getDocDate());

                view.setCitizenshipName(doc.getCountryCode().getName() != null ? doc.getCountryCode().getName() : "");
                view.setCitizenshipCode(doc.getCountryCode().getCode() != null ? doc.getCountryCode().getCode() : "");
        }

        return view;

    }
}
