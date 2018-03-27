package ru.bellintegrator.practice.universal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.universal.controller.UniversalController;
import ru.bellintegrator.practice.universal.service.UniversalService;
import ru.bellintegrator.practice.users.view.UserView;

/**
 * Created by Alena on 20.03.2018.
 */
@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UniversalControllerImpl implements UniversalController {
    private final UniversalService service;

    @Autowired
    public UniversalControllerImpl(UniversalService service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(UserView user) {
        service.register(user);
        return false;
    }

    @Override
    @RequestMapping(value = "/activation?code={code}", method = RequestMethod.GET) //api/activation?code=<код активации, который был отправлен пользователю>
    public void activation(@RequestParam("code")String code) {
        service.activation(code);
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(UserView user) {
        service.login(user);
        return false;
    }
}
