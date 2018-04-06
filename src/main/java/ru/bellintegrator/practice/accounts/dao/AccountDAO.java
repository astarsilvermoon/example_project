package ru.bellintegrator.practice.accounts.dao;

import ru.bellintegrator.practice.accounts.model.Account;
import ru.bellintegrator.practice.accounts.view.AccountView;

import java.util.List;

public interface AccountDAO {

    Boolean saveAccount(Account account);
    List<Account> findAccount(Account account);
    Account findActivationCode(String code);
    Boolean updateAccount(Account account);

}
