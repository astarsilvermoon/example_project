package ru.bellintegrator.practice.dictionaries.controller.impl;

/**
 * Created by Alena on 20.03.2018.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dictionaries.controller.DocTypeController;
import ru.bellintegrator.practice.dictionaries.model.DocType;
import ru.bellintegrator.practice.dictionaries.service.DocTypeService;
import ru.bellintegrator.practice.dictionaries.view.DocTypeView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import java.util.List;

@RestController
@RequestMapping(value = "/api/docs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocTypeControllerImpl implements DocTypeController {
    private final DocTypeService service;

    @Autowired
    public  DocTypeControllerImpl(DocTypeService service){
       this.service = service;
   }

    @Override
    @PostMapping
    public ResponseEntity getAll() {
        ResponseView response = new ResponseView();
        try {
            List<DocTypeView> docs = service.getAll();
            response.setData(docs);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
