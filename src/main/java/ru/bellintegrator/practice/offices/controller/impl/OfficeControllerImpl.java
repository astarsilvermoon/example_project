package ru.bellintegrator.practice.offices.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.offices.controller.OfficeController;
import ru.bellintegrator.practice.offices.service.OfficeService;
import ru.bellintegrator.practice.offices.view.OfficeView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alena on 20.03.2018.
 */
@RestController
@RequestMapping(value = "api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeControllerImpl implements OfficeController{

    private final OfficeService service;

    @Autowired
    public OfficeControllerImpl(OfficeService service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/list/{orgId}", method = RequestMethod.POST) //{orgId:.+}
    public ResponseView list(@PathVariable("orgId") @Valid Long orgId, @Valid OfficeView view) {
        ResponseView response = new ResponseView();
        try {
            List<OfficeView> offices = service.getOfficeByOrgId(orgId, view);
            response.setData(offices);
        }catch(Exception e){
            response.setError(e.getMessage());
        }
            return response;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) //{id:.+}
    public ResponseView getById(@PathVariable("id") @Valid  Long id) {
        ResponseView response = new ResponseView();
        try{
            OfficeView office =  service.getById(id);
            response.setData(office);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseView update(@Valid OfficeView office) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.update(office);
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return  response;
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

    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseView save(@Valid OfficeView office) {
        ResponseView response = new ResponseView();
        try{
            boolean res = service.save(office);
            response.setResult(res);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }
}
