package ru.bellintegrator.practice.offices.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.offices.service.OfficeService;
import ru.bellintegrator.practice.offices.view.OfficeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService{
    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private final OfficeDAO dao;

    @Autowired
    public OfficeServiceImpl(OfficeDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<OfficeView> getOfficeByOrgId(Long orgId, OfficeView office) {
        List<Office> list = null;
        try {
         list =  dao.getOfficesByOrgId(orgId, office);

            Function<Office, OfficeView> mapOf = of -> {
                OfficeView view = new OfficeView();
                view.id = of.getId();
                view.name = of.getName();
                view.isActive = of.getActive();

                log.info("Get office by organization id");
                log.info(view.toString());
                return view;
            };
            return list.stream().map(mapOf).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OfficeView getById(Long id) {
        try {
        Office office =  dao.getOfficeById(id);

        OfficeView view = new OfficeView();
        if(office.getId()!= null)
        view.setId(office.getId());
        else{
            log.error("Office id is NULL");
            throw new Exception("Office id is NULL");
        }

        view.setName(office.getName()!= null ? office.getName() : "");
        view.setAddress(office.getAddress()!= null ? office.getAddress() : "");
        view.setPhone(office.getPhone()!= null ? office.getPhone() : "");
        view.setActive(office.getActive()!=null ? office.getActive() : false);

        log.info("Get office by id");
        log.info(view.toString());
        return view;

        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(OfficeView office) {
        boolean res = checkRes(dao.updateOffice(office));
        if(!res) {
            log.error("Update office is false");
        }else log.info("Update office successed");
        return res;
    }

    @Override
    public boolean delete(Long id) {
        if(id != null){
            boolean res = checkRes(dao.deleteOffice(id));
            if(!res)
                log.error("Delete office is false");
            else  log.info("Delete office successed");

        return res;
        }
        else return false;
    }

    //ToDo сделать save
    @Override
    public boolean save(OfficeView office) {
        dao.saveOffice(office);
        return false;
    }

    public boolean checkRes(int val){
        if(val > 0)
            return true;
        else return false;
    }

}
