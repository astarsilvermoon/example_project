package ru.bellintegrator.practice.accounts.controller.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.accounts.dao.AccountDAO;
import ru.bellintegrator.practice.accounts.model.Account;
import ru.bellintegrator.practice.accounts.service.AccountService;
import ru.bellintegrator.practice.accounts.service.GenerateHashService;
import ru.bellintegrator.practice.accounts.view.AccountView;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
public class AccountControllerImplIntegrTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;
    @Autowired
    private AccountDAO dao;
    @Autowired
    private AccountService service;
    @Autowired
    private GenerateHashService hashService;

    @Before
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    }
    @Test
    public void register() {
        AccountView account = new AccountView();
        account.setName("NAME");
        account.setLogin("mail@mail.ru");
        account.setPassword("PASSWORD");
        Account account1 = new Account();
        account1.setLogin("mail@mail.ru");

        HttpEntity<AccountView> entity = new HttpEntity<>(account, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/register", HttpMethod.POST, entity, String.class);

        String expected = "{\"result\":true}";
        assertThat(response.getBody(), is(expected));
        assertNotNull(dao.findAccount(account1));
    }

    @Test
    public void activation() {
        Account account = new Account();
        account.setName("NAME");
        account.setLogin("mail@mail.ru");
        account.setPassword("PASSWORD");

        register();
        List<Account> list = dao.findAccount(account);
        assertNotNull(list);
        assertTrue(list.size()>0);
        assertFalse(list.get(0).getActivated()); // аккаунт не активирован

        String code = hashService.getUuid();
        String url = "/api/activation?code=" + code;
        HttpEntity entity = new HttpEntity(headers);

         restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        List<Account> list1 = dao.findAccount(account);
        assertNotNull(list1);
        assertTrue(list1.size()>0);
        assertTrue(list1.get(0).getActivated()); ; // активирован
    }



    @Test
    public void login() {
        activation();
        String url = "/api/login";

        String body = "{\"login\":\"mail@mail.ru\",\"password\":\"PASSWORD\"}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"result\":true}";
        assertThat(result, is(expected));

    }
}