package ru.bellintegrator.practice.users.controller;


import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.universal.view.ResponseView;
import ru.bellintegrator.practice.users.view.UserView;


import java.util.List;


public interface UserController {

    ResponseView list(@RequestBody UserView user);// пункт 14 ТЗ ? что должен возвращать?

    ResponseView getById(Long id);

    ResponseView update(UserView user);

    ResponseView delete(Long id);

    ResponseView save(UserView user);


}
