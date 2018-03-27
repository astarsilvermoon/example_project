package ru.bellintegrator.practice.users.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.universal.view.ResponseView;
import ru.bellintegrator.practice.users.controller.UserController;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.service.UserService;
import ru.bellintegrator.practice.users.view.UserView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Autowired
    public UserControllerImpl(UserService service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseView list(@Valid UserView user) {
        ResponseView response = new ResponseView();
        try {
            List<UserView> view = service.getUsersByOfficeId(user.getOfficeId(), user);
            response.setData(view);
        }catch (Exception e ){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) //{id:.+}
    public ResponseView getById(@PathVariable("id") @Valid Long id) {
        ResponseView response = new ResponseView();
        try {
            UserView view = service.getById(id);
            response.setData(view);
        }catch(Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }


    @Override
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseView update(@Valid UserView user) {
        ResponseView response = new ResponseView();
        try {
            boolean res = service.update(user);
            response.setResult(res);
        }catch(Exception e){
            response.setError(e.getMessage());
        }
        return  response;
    }


    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseView delete(@Valid Long id) {
        ResponseView response = new ResponseView();
        try {
            boolean res = service.delete(id);
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseView save(@Valid UserView user) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.save(user);
            response.setResult(res);
        }catch(Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }


}
