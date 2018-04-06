package ru.bellintegrator.practice.users.controller.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dictionaries.dao.CountryCodeDAO;
import ru.bellintegrator.practice.dictionaries.dao.DocTypeDAO;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;
import ru.bellintegrator.practice.dictionaries.model.DocType;
import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.userdocs.model.UserDoc;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
public class UserControllerImplIntegrTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserDAO dao;
    @Autowired
    private OfficeDAO officeDAO;
    @Autowired
    private DocTypeDAO docTypeDAO;
    @Autowired
    private CountryCodeDAO countryCodeDAO;

    private HttpHeaders headers;

    @Before
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    }
    @Test
    public void list() {
        String url = "/api/user/list";
        String body = "{\"officeId\" : 1}";
        User user = insertUser();
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":" +
                "[{\"id\":" + "4" + "," +
                "\"firstName\":\"Иванов\"," +
                "\"lastName\":\"Иван\"," +
                "\"middleName\":\"Иванович\"," +
                "\"position\":\"ген.дир\"}," +
                "{\"id\":2," +
                "\"firstName\":\"Петров\","+
                "\"lastName\":\"Иван\","+
                "\"middleName\":\"Петрович\","+
                "\"position\":\"Менеджер\"}," +
                "{\"id\":1," +
                "\"firstName\":\"Иванов\"," +
                "\"lastName\":\"Иван\"," +
                "\"middleName\":\"Иванович\"," +
                "\"position\":\"Генеральный директор\"}]}";
        assertThat(result, is(expected));
    }

    @Test
    public void getById() {
        String url = "/api/user/1";
        String param = "1";
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"id\":1,\"firstName\":\"Иванов\",\"lastName\":\"Иван\",\"middleName\":\"Иванович\"," +
                "\"position\":\"Генеральный директор\",\"citizenshipCode\":\"643\",\"phone\":\"88002223331\",\"docName\":" +
                "\"Паспорт гражданина Российской Федерации\",\"docNumber\":\"1234 123456\",\"docDate\":\"1990-02-20\",\"citizenshipName\":\"РОССИЯ\"}}";

        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        String body = "{\"id\":1,\"firstName\":\"Качан\",\"lastName\":\"Иван\",\"middleName\":\"Иванович\"," +
                "\"position\":\"Директор\",\"citizenshipCode\":\"643\",\"phone\":\"88002223331\",\"docName\":" +
                "\"Военный билет\",\"docNumber\":\"123456\",\"docDate\":\"2008-03-20\",\"citizenshipName\":\"РОССИЯ\"}";

        String url1 = "/api/user/update";
        init();
        HttpEntity entity1 = new HttpEntity<>(body, headers);
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.POST, entity1, String.class);
        String result1 = response1.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result1, is(expected1));


        String url = "/api/user/1";
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"id\":1,\"firstName\":\"Качан\",\"lastName\":\"Иван\",\"middleName\":\"Иванович\"," +
                "\"position\":\"Директор\",\"citizenshipCode\":\"643\",\"phone\":\"88002223331\",\"docName\":" +
                "\"Паспорт гражданина Российской Федерации\",\"docNumber\":\"1234 123456\",\"docDate\":\"1990-02-20\",\"citizenshipName\":\"РОССИЯ\"}}";

        assertThat(result, is(expected));
    }

    @Test
    public void delete() {
        String url = "/api/user/delete";
        String body = "{\"id\":1}";
        HttpEntity entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result, is(expected1));
    }

    @Test
    public void save() {
        String url1 = "/api/user/save";
        String body = "{\"firstName\":\"Качан\",\"lastName\":\"Иван\",\"middleName\":\"Иванович\"," +
                "\"position\":\"Директор\",\"citizenshipCode\":\"643\",\"phone\":\"88002223331\",\"docName\":" +
                "\"Паспорт гражданина Российской Федерации\",\"docNumber\":\"1234 123456\",\"docDate\":\"1990-02-20\",\"citizenshipName\":\"РОССИЯ\"," +
                "\"officeId\":1}}";
        HttpEntity entity1 = new HttpEntity<>(body, headers);
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.POST, entity1, String.class);
        String result1 = response1.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result1, is(expected1));

        String url = "/api/user/list";
        String body1 = "{\"officeId\" : 1,\"firstName\":\"Качан\",\"lastName\":\"Иван\"}";
        User user = insertUser();
        HttpEntity entity = new HttpEntity<>(body1, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":[{\"id\":4,\"firstName\":\"Качан\",\"lastName\":\"Иван\",\"middleName\":\"Иванович\",\"position\":\"Директор\"}]}";
        assertThat(result, is(expected));
    }


    public User insertUser(){
        User user = new User();
        user.setFirstName("Иванов");
        user.setLastName("Иван");
        user.setMiddleName("Иванович");
        user.setPosition("ген.дир");
        user.setPhone("11111111");
        user.setIdentified(true);


        Office office = officeDAO.getOfficeById(1l);
        user.setOffice(office);

        UserDoc doc = new UserDoc();
        doc.setUser(user);
        doc.setDocNumber("11122");
        try {
            doc.setDocDate(new SimpleDateFormat("dd-MM-yyyy").parse("04-04-2000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CountryCode code = countryCodeDAO.getByNameAndCode("РОССИЯ", "643");
        doc.setCountryCode(code);
        DocType docType = docTypeDAO.getByNameAndCode("Паспорт гражданина Российской Федерации","21");
        doc.setDocType(docType);

        user.setUserDoc(doc);

        dao.saveUser(user);
        return user;
    }
}