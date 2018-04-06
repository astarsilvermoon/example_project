package ru.bellintegrator.practice.users.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.bellintegrator.practice.universal.view.ResponseView;
import ru.bellintegrator.practice.users.view.UserFilterView;
import ru.bellintegrator.practice.users.view.UserView;


import java.util.List;


public interface UserController {

    ResponseEntity list(@RequestBody UserFilterView user);

    ResponseEntity getById(Long id);

    ResponseEntity update(@RequestBody UserView user);

    ResponseEntity delete(@RequestBody UserView view);

    ResponseEntity save(@RequestBody UserView user);


}
