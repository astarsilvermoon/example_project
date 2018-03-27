package ru.bellintegrator.practice.universal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.universal.service.UniversalService;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.view.UserView;

import java.util.Base64;
import java.util.UUID;

/**
 * Created by Alena on 27.03.2018.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UniversalServiceImpl implements UniversalService {
    private final Logger log = LoggerFactory.getLogger(UniversalServiceImpl.class);

    private final UserDAO dao;

    @Autowired
    public UniversalServiceImpl(UserDAO dao) {
        this.dao = dao;
    }
    /*ToDo
     какая логика у api/activation? Общая логика активации аккаунта:
 * 1. При регистрации создать неактивного пользователя
 * 2. Сгенерировать случайную строку. Хэш от неё записать в бд
 * 3. Сам email отправлять не надо. Добавить запись в БД. Предполагаем, что их обрабатывает отдельный сервис отправки email. Его делать не надо
 * 4. Контроллер activation берёт хэш от значения code и ищет по ней запись. Если находит, делает активным соответствующего пользователя
 * Для хэша используется SHA-256. Получение его вынесено в отдельный сервис.
+ хэш от пароля в базе надо хранить закодированным в base64, типа, требования безопасности))
     */

    @Override
    public boolean register(UserView user) {
        dao.saveUser(user);
        return false;
    }

    @Override
    public void activation(String code) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        byte[] bytesEncoded = Base64.getEncoder().encode(uuid.getBytes());
        String activationCode = new String(bytesEncoded);

    }

    @Override
    public boolean login(UserView user) {
        return false;
    }
}
