package ru.bellintegrator.practice.dictionaries.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dictionaries.controller.CountryCodeController;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;
import ru.bellintegrator.practice.dictionaries.service.CountryCodeService;
import ru.bellintegrator.practice.dictionaries.view.CountryCodeView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import java.util.List;

/**
 * Created by Alena on 20.03.2018.
 */
@RestController
@RequestMapping(value = "/api/countries", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryCodeControllerImpl implements CountryCodeController {
    private final CountryCodeService service;

    @Autowired
    public CountryCodeControllerImpl(CountryCodeService service){
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity getAll() {
        ResponseView response = new ResponseView();
        try {
            List<CountryCodeView> countries = service.getAll();
            response.setData(countries);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
