package ru.bellintegrator.practice.universal.controller;

import ru.bellintegrator.practice.users.view.UserView;

/**
 * Created by Alena on 20.03.2018.
 */
public interface UniversalController {

    boolean register(UserView user);

    void activation(String code);

    boolean login(UserView user);

}
