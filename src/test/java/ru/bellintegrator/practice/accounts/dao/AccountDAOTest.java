package ru.bellintegrator.practice.accounts.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.accounts.model.Account;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class AccountDAOTest {
    @Autowired
    private AccountDAO dao;

    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setLogin("faraway_999@mail.ru");
        account.setPassword("NUU4ODQ4OThEQTI4MDQ3MTUxRDBFNTZGOERDNjI5Mjc3MzYwM0QwRDZBQUJCREQ2MkExMUVGNzIxRDE1NDJEOA=="); //password
        account.setActivationCode("5694D08A2E53FFCAE0C3103E5AD6F6076ABD960EB1F8A56577040BC1028F702B"); //code
        account.setActivated(false);

        boolean result= dao.saveAccount(account);
        Assert.assertTrue(result);

        List<Account> list = dao.findAccount(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.contains(account));
    }

    @Test
    public void findAccount() {
       List<Account> list =  dao.findAccount(null);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        Account account = new Account();
        account.setLogin("general@gmail.com");
        account.setPassword("NUU4ODQ4OThEQTI4MDQ3MTUxRDBFNTZGOERDNjI5Mjc3MzYwM0QwRDZBQUJCREQ2MkExMUVGNzIxRDE1NDJEOA=="); //password
        account.setActivationCode("8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92"); //code
        account.setActivated(true);
        List<Account> list1 = dao.findAccount(account);
        Assert.assertNotNull(list1);

    }

    @Test
    public void findActivationCode() {
        Account account = new Account();
        account.setLogin("general@gmail.com");
        account.setPassword("NUU4ODQ4OThEQTI4MDQ3MTUxRDBFNTZGOERDNjI5Mjc3MzYwM0QwRDZBQUJCREQ2MkExMUVGNzIxRDE1NDJEOA=="); //password
        account.setActivationCode("8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92"); //code
        account.setActivated(true);

        Account finded = dao.findActivationCode(account.getActivationCode());
        Assert.assertNotNull(finded);
    }

    @Test
    public void updateAccount() {
        Account account = new Account();
        account.setLogin("general@gmail.com");
        account.setPassword("NUU4ODQ4OThEQTI4MDQ3MTUxRDBFNTZGOERDNjI5Mjc3MzYwM0QwRDZBQUJCREQ2MkExMUVGNzIxRDE1NDJEOA=="); //password
        account.setActivationCode("8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92"); //code
        account.setActivated(true);
        List<Account> list = dao.findAccount(account);
        Assert.assertNotNull(list);
        Assert.assertNotNull(list.get(0));

        Account account1 = list.get(0);
        account.setLogin("generalTEst@test.ru");
        Assert.assertTrue( dao.updateAccount(account1));

    }
}