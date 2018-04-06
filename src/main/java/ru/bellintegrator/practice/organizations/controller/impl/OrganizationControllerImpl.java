package ru.bellintegrator.practice.organizations.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organizations.controller.OrganizationController;
import ru.bellintegrator.practice.organizations.service.OrganizationService;
import ru.bellintegrator.practice.organizations.view.OrganizationFilterView;
import ru.bellintegrator.practice.organizations.view.OrganizationView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alena on 20.03.2018.
 */
@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationControllerImpl implements OrganizationController {
    private final OrganizationService service;

    @Autowired
    public OrganizationControllerImpl(OrganizationService service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity list(@RequestBody OrganizationFilterView org) {
        ResponseView response = new ResponseView();
        try{
            List<OrganizationView>  orgs =  service.getAllOrgs(org);
            response.setData(orgs);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) //{id:.+}
    public ResponseEntity getById(@PathVariable("id")Long id) {
        ResponseView response = new ResponseView();
        try{
            OrganizationView org = service.getOrgById(id);
            response.setData(org);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody OrganizationView org) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.update(org);
            response.setResult(res);
        }catch(Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody OrganizationView org) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.save(org);
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody OrganizationView view) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.delete(view.getId());
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
