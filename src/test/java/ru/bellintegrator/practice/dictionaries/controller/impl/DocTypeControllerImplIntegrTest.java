package ru.bellintegrator.practice.dictionaries.controller.impl;

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
public class DocTypeControllerImplIntegrTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @Before
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    }

    @Test
    public void getAll() {
        String url = "/api/docs";
        String body = "{}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String result = response.getBody();
        String expected = "{\"data\":[{\"id\":1,\"name\":\"Паспорт гражданина Российской Федерации\"," +
                "\"code\":\"21\"},{\"id\":2,\"name\":\"Военный билет\",\"code\":\"07\"}," +
                "{\"id\":3,\"name\":\"Иные документы\",\"code\":\"91\"}]}";
        assertThat(result, is(expected));
    }
}