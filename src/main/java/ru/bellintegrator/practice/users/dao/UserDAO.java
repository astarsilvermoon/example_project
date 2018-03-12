package ru.bellintegrator.practice.users.dao;

import ru.bellintegrator.practice.users.model.User;

import java.util.List;

/**
 * Created by Alena on 06.03.2018.
 */
public interface UserDAO {

    /**
     * Получить всех пользователей (officeId обязательный параметр)
     * @return
     */
    List<User> getAll();

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
    void updateUser(User user);

    /**
     * Сохранить пользователя
     * @param user
     */
    void saveUser(User user);
}
