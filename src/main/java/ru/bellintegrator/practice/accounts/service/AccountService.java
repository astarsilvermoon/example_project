package ru.bellintegrator.practice.accounts.service;

import ru.bellintegrator.practice.accounts.view.AccountView;

/**
 * Created by Alena on 27.03.2018.
 */
public interface AccountService {

    boolean register(AccountView account);

    boolean activation(String code);

    boolean login(AccountView account);

    String getUuid();
}
