package ru.bellintegrator.practice.accounts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.accounts.dao.AccountDAO;
import ru.bellintegrator.practice.accounts.model.Account;
import ru.bellintegrator.practice.accounts.service.AccountService;
import ru.bellintegrator.practice.accounts.service.GenerateHashService;
import ru.bellintegrator.practice.accounts.view.AccountView;
import ru.bellintegrator.practice.exceptions.exceptions.AccountException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Alena on 27.03.2018.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class AccountServiceImpl implements AccountService {
    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountDAO dao;
    private final GenerateHashService service;
    private  String uuid;

    @Autowired
    public AccountServiceImpl(AccountDAO dao, GenerateHashService service) {
        this.dao = dao;
        this.service = service;
    }
    /* какая логика у api/activation? Общая логика активации аккаунта:
 * 1. При регистрации создать неактивного пользователя
 * 2. Сгенерировать случайную строку. Хэш от неё записать в бд
 * 3. Сам email отправлять не надо. Добавить запись в БД. Предполагаем, что их обрабатывает отдельный сервис отправки email. Его делать не надо
 * 4. Контроллер activation берёт хэш от значения code и ищет по ней запись. Если находит, делает активным соответствующего пользователя
 * Для хэша используется SHA-256. Получение его вынесено в отдельный сервис.
+ хэш от пароля в базе надо хранить закодированным в base64
     */

    @Override
    public boolean register(AccountView view){
        if(view!=null) {
            Account account = new Account();
            account.setLogin(view.getLogin());
            List<Account> list = dao.findAccount(account);
            if ( list!= null && list.size()>0) {
                throw new AccountException("Аккаунт с таким логином уже существует");//логин занят
            } else {
                account.setPassword(service.encode(view.getPassword()));
                account.setActivated(false);
                account.setName(view.getName());
                account.setActivationCode(service.generateCode());
                boolean result = dao.saveAccount(account);
                log.info(view.toString());
                if(result) {
                    log.info("Сохранение пользователя прошло успешно");
                    return true;
                }else throw  new AccountException("Не удалось сохранить пользователя");
            }
        }else  throw new AccountException("Введенные данный о пользователе пусты");
    }

    @Override
    public boolean activation(String code){
        Boolean result =null;
        if(code!=null) {
            Account account = dao.findActivationCode(service.encode(code).toString());
            uuid = service.getUuid();
            if (account != null) {
                account.setActivated(true);
                result = dao.updateAccount(account);
                if(result) {
                    log.info("Активация прошла успешно");
                    //если все успешно то сообщение пользователю
                    return result;
                }else throw new AccountException("Не удалось активировать аккаунт");
            } else throw new AccountException("Код не найден. Аккаунт не существует");
        }else throw  new AccountException("Код активации NULL");
    }

    @Override
    public boolean login(AccountView view) {
        if(view!=null) {
            Account account = new Account();
            account.setLogin(view.getLogin());
            account.setPassword(service.encode(view.getPassword()));
            List<Account> finded = dao.findAccount(account);
            if (finded != null ) {
                log.info(view.toString());
                return true;
            } else throw  new AccountException("Пользователь не найден");
        }else  throw  new AccountException("Введенные данные пусты");
    }
    @Override
    public String getUuid() {
        return uuid;
    }
}
