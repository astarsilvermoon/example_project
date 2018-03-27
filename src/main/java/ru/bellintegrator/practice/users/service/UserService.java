package ru.bellintegrator.practice.users.service;

import ru.bellintegrator.practice.users.view.UserView;

import java.util.List;

/**
 * Created by Alena on 26.03.2018.
 */
public interface UserService {

    List<UserView> getUsersByOfficeId(Long officeId, UserView view);

    UserView getById(Long id);

    boolean update(UserView view);

    boolean delete(Long id);

    boolean save(UserView view);
}
