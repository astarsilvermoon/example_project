package ru.bellintegrator.practice.offices.controller.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
public class OfficeControllerImplIntegrTest {

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
        String url = "/api/office/list/1";
        String body = "{\"name\":\"Офис1\"}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":[{\"id\":1,\"name\":\"Офис1\",\"isActive\":true}]}";
        assertThat(result, is(expected));

    }

    @Test
    public void getById() {
        String url = "/api/office/1";
        HttpEntity entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":{\"id\":1,\"name\":\"Офис1\",\"phone\":\"1234444\",\"isActive\":true,\"address\":\"ул.Минская\"}}";
        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        String body = "{\"id\":1,\"name\":\"Офис11111\",\"phone\":\"1234444\",\"isActive\":true,\"address\":\"ул.Минская\"}";

        String url1 = "/api/office/update";
        HttpEntity entity1 = new HttpEntity<>(body, headers);
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.POST, entity1, String.class);
        String result1 = response1.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result1, is(expected1));


        String url = "/api/office/1";
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"id\":1,\"name\":\"Офис11111\",\"phone\":\"1234444\",\"isActive\":true,\"address\":\"ул.Минская\"}}";

        assertThat(result, is(expected));
    }

    @Test
    public void delete() {
        String url = "/api/office/delete";
        String body = "{\"id\":1}";
        HttpEntity entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result, is(expected1));
    }

    @Test
    public void save() {
        String url1 = "/api/office/save";
        String body = "{\"orgId\":\"1\",\"name\":\"Офис11111\",\"phone\":\"1234444\",\"isActive\":true,\"address\":\"ул.Минская\"}";
        HttpEntity entity1 = new HttpEntity<>(body, headers);
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.POST, entity1, String.class);
        String result1 = response1.getBody();
        String expected1= "{\"result\":true}";
        assertThat(result1, is(expected1));

        String url = "/api/office/list/1";
        String body1 = "{}";
        HttpEntity entity = new HttpEntity<>(body1, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":[{\"id\":2,\"name\":\"Офис2\",\"isActive\":true},{\"id\":1,\"name\":\"Офис1\",\"isActive\":true}," +
                "{\"id\":4,\"name\":\"Офис11111\",\"isActive\":true}]}";
        assertThat(result, is(expected));
    }
}