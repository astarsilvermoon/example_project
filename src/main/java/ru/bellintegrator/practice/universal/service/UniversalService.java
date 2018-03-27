package ru.bellintegrator.practice.universal.service;

import ru.bellintegrator.practice.users.view.UserView;

/**
 * Created by Alena on 27.03.2018.
 */
public interface UniversalService {

    boolean register(UserView user);

    void activation(String code);

    boolean login(UserView user);
}
