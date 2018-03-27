package ru.bellintegrator.practice.users.dao;

import ru.bellintegrator.practice.users.model.User;
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
    List<User> getAll(Long officeId, UserView user) throws Exception;

    /**
     * Получить пользователя по его идентификатору
     * @param id
     * @return
     */
    User getUserById(Long id) throws Exception;

    /**
     * Изменить пользователя
     * @param user
     */
    Integer updateUser(UserView user);

    /**
     * Сохранить пользователя
     * @param user
     */
    void saveUser(UserView user);

    /**
     * Удалить пользователя
     *
     */
    Integer  deleteUser(Long id);
}
