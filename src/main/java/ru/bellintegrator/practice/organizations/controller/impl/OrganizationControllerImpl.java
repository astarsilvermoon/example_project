package ru.bellintegrator.practice.organizations.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.organizations.controller.OrganizationController;
import ru.bellintegrator.practice.organizations.service.OrganizationService;
import ru.bellintegrator.practice.organizations.view.OrganizationView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alena on 20.03.2018.
 */
@RestController
@RequestMapping(value = "api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationControllerImpl implements OrganizationController {
    private final OrganizationService service;

    @Autowired
    public OrganizationControllerImpl(OrganizationService service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/list/", method = RequestMethod.POST)
    public ResponseView list(@Valid OrganizationView org) {
        ResponseView response = new ResponseView();
        try{
            List<OrganizationView>  orgs =  service.getAllOrgs(org);
            response.setData(orgs);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) //{id:.+}
    public ResponseView getById(@PathVariable("id") @Valid Long id) {
        ResponseView response = new ResponseView();
        try{
            OrganizationView org = service.getOrgById(id);
            response.setData(org);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseView update(@Valid OrganizationView org) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.update(org);
            response.setResult(res);
        }catch(Exception e){
            response.setError(e.getMessage());
        }
        return  response;
    }

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseView save(@Valid OrganizationView org) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.save(org);
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseView delete(@Valid Long id) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.delete(id);
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }
}
