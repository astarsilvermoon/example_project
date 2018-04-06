package ru.bellintegrator.practice.offices.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.offices.controller.OfficeController;
import ru.bellintegrator.practice.offices.service.OfficeService;
import ru.bellintegrator.practice.offices.view.OfficeFilterView;
import ru.bellintegrator.practice.offices.view.OfficeView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alena on 20.03.2018.
 */
@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeControllerImpl implements OfficeController{

    private final OfficeService service;

    @Autowired
    public OfficeControllerImpl(OfficeService service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/list/{orgId}", method = RequestMethod.POST) //{orgId:.+}
    public ResponseEntity list(@PathVariable("orgId") Long orgId, @RequestBody OfficeFilterView view) {
        ResponseView response = new ResponseView();
        try {
            view.setOrgId(orgId);
            List<OfficeFilterView> offices = service.getOfficeByOrgId(view);
            response.setData(offices);
        }catch(Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) //{id:.+}
    public ResponseEntity getById(@PathVariable("id") Long id) {
        ResponseView response = new ResponseView();
        try{
            OfficeView office =  service.getById(id);
            response.setData(office);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody OfficeView office) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.update(office);
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody OfficeView office) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.delete(office.getId());
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody OfficeView office) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.save(office);
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
