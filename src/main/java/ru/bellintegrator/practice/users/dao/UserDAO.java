package ru.bellintegrator.practice.users.dao;

import ru.bellintegrator.practice.userdocs.model.UserDoc;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.view.UserFilterView;
import ru.bellintegrator.practice.users.view.UserView;

import java.util.List;

/**
 * Created by Alena on 06.03.2018.
 */
public interface UserDAO {

    /**
     * Получить всех пользователей (officeId обязательный параметр)
     * @return
     */
    List<User> getAll(Long officeId, UserFilterView user);

    /**
     * Получить пользователя по его идентификатору
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * Изменить пользователя
     * @param user
     */
    Integer updateUser(User user);

    /**
     * Сохранить пользователя
     * @param user
     */
    Boolean saveUser(User user);

    /**
     * Удалить пользователя
     *
     */
    void  deleteUser(Long id);

}
