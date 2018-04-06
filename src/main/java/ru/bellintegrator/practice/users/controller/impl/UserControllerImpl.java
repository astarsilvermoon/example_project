package ru.bellintegrator.practice.users.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.exceptions.exceptions.UserException;
import ru.bellintegrator.practice.universal.view.ResponseView;
import ru.bellintegrator.practice.users.controller.UserController;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.service.UserService;
import ru.bellintegrator.practice.users.view.UserFilterView;
import ru.bellintegrator.practice.users.view.UserView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Autowired
    public UserControllerImpl(UserService service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity  list(@RequestBody UserFilterView user) {
        ResponseView response = null;
        try {
            List<UserView> view = service.getUsersByOfficeId(user.getOfficeId(), user);
            response = new ResponseView(view);
        }catch (UserException e ){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) //{id:.+}
    public  ResponseEntity getById(@PathVariable("id") Long id) {
        ResponseView response = new ResponseView();
        try {
            UserView view = service.getById(id);
            response.setData(view);
        }catch(UserException e){
            response.setError(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


    @Override
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public  ResponseEntity update(@RequestBody UserView user) {
        ResponseView response = new ResponseView();
        try {
            boolean res = service.update(user);
            response.setResult(res);
        }catch(UserException e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody UserView view) {
        ResponseView response = new ResponseView();
        try {
            boolean res = service.delete(view.getId());
            response.setResult(res);
        }catch (UserException e){
            response.setError(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public  ResponseEntity save(@RequestBody UserView user) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.save(user);
            response.setResult(res);
        }catch(UserException e){
            response.setError(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
