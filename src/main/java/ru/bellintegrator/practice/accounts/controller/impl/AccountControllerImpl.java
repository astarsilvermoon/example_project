package ru.bellintegrator.practice.accounts.controller.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.accounts.controller.AccountController;
import ru.bellintegrator.practice.accounts.service.AccountService;
import ru.bellintegrator.practice.accounts.view.AccountView;
import ru.bellintegrator.practice.universal.view.ResponseView;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountControllerImpl implements AccountController {
    private  final AccountService service;

    @Autowired
    public AccountControllerImpl(AccountService service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody AccountView user) {
        ResponseView response = new ResponseView();
       try {
           Boolean result = service.register(user);
           if (result) {
               response.setResult(result);
           }
       }catch (Exception e){
           response.setError(e.getMessage());
       }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/activation", method = RequestMethod.GET) //api/activation?code=<код активации, который был отправлен пользователю>
    public ResponseEntity activation(@RequestParam("code")String code) {
        ResponseView response = new ResponseView();
        try {
            response.setResult(service.activation(code));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody AccountView user) {
        ResponseView response = new ResponseView();
        response.setResult(service.login(user));
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
