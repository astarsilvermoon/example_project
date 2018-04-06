package ru.bellintegrator.practice.accounts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.accounts.view.AccountView;
import ru.bellintegrator.practice.universal.view.ResponseView;

public interface AccountController {

    ResponseEntity register(@RequestBody AccountView user);

    ResponseEntity activation(String code);

    ResponseEntity login(@RequestBody AccountView user);
}
