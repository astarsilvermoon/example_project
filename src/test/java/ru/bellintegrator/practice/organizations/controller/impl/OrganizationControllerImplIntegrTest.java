package ru.bellintegrator.practice.organizations.controller.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.organizations.dao.OrganizationDAO;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
public class OrganizationControllerImplIntegrTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @Before
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    }

    @Test
    public void list() {
        String url = "/api/organization/list";
        String body = "{\"id\" : 1, \"name\":\"Ромашка\"}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":[{\"id\":1,\"name\":\"Ромашка\",\"inn\":\"581234567891\",\"isActive\":true}]}";
        assertThat(result, is(expected));
    }

    @Test
    public void getById() {
        String url = "/api/organization/1";
        HttpEntity entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":{\"id\":1,\"name\":\"Ромашка\",\"inn\":\"581234567891\",\"isActive\":true,\"fullName\"" +
                ":\"Общество с ограниченной общественностью Ромашка\",\"kpp\":\"123456789\",\"address\":\"ул.Кирова,17\",\"phone\"" +
                ":\"502050\"}}";
        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        String body = "{\"id\":1,\"name\":\"Ромашка11111\",\"inn\":\"581234567891\",\"isActive\":true,\"fullName\"" +
                ":\"Общество с ограниченной общественностью Ромашка\",\"kpp\":\"123456789\",\"address\":\"ул.Кирова,17\",\"phone\"" +
                ":\"502050\"}";

        String url1 = "/api/organization/update";
        HttpEntity entity1 = new HttpEntity<>(body, headers);
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.POST, entity1, String.class);
        String result1 = response1.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result1, is(expected1));


        String url = "/api/organization/1";
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"id\":1,\"name\":\"Ромашка11111\",\"inn\":\"581234567891\",\"isActive\":true,\"fullName\""+
                ":\"Общество с ограниченной общественностью Ромашка\",\"kpp\":\"123456789\",\"address\":\"ул.Кирова,17\",\"phone\"" +
                ":\"502050\"}}";

        assertThat(result, is(expected));
    }

    @Test
    public void save() {
        String url1 = "/api/organization/save";
        String body = "{\"name\":\"Ромашка11111\",\"inn\":\"581234567891\",\"isActive\":true,\"fullName\"" +
                ":\"Общество с ограниченной общественностью Ромашка1111111\",\"kpp\":\"123456781\",\"address\":\"ул.Кирова,10\",\"phone\"" +
                ":\"502050\"}";
        HttpEntity entity1 = new HttpEntity<>(body, headers);
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.POST, entity1, String.class);
        String result1 = response1.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result1, is(expected1));

        String url = "/api/organization/list";
        String body1 = "{\"name\":\"Ромашка11111\"}";
        HttpEntity entity = new HttpEntity<>(body1, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":[{\"id\":4,\"name\":\"Ромашка11111\",\"inn\":\"581234567891\",\"isActive\":true}]}";
        assertThat(result, is(expected));
    }

    @Test
    public void delete() {
        String url = "/api/organization/delete";
        String body = "{\"id\":1}";
        HttpEntity entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result, is(expected1));
    }
}