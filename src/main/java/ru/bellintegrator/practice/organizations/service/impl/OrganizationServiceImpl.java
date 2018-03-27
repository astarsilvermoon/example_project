package ru.bellintegrator.practice.organizations.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organizations.dao.OrganizationDAO;
import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.organizations.service.OrganizationService;
import ru.bellintegrator.practice.organizations.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OrganizationServiceImpl implements OrganizationService {
    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationDAO dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<OrganizationView> getAllOrgs(OrganizationView view) {
      List<Organization> list =  dao.getAll(view);
        Function<Organization, OrganizationView> mapOrg = org -> {
            OrganizationView orgView = new OrganizationView();
            orgView.id = org.getId();
            orgView.inn = org.getInn();
            orgView.name = org.getName();
            orgView.isActive = org.getActive();

            log.info("Get all organizations");
            log.info(orgView.toString());
            return orgView;
        };
        return list.stream().map(mapOrg).collect(Collectors.toList());
    }

    @Override
    public OrganizationView getOrgById(Long id) {
        try{
          Organization org =   dao.getOrgById(id);

          OrganizationView view = new OrganizationView();
          if(org.getId()!= null)
              view.setId(org.getId());
          else {
              log.error("Organization id is NULL");
              throw new Exception("Organization id is NULL");
          }

          view.setName(org.getName()!= null ? org.getName() : "");
            view.setFullName(org.getFullName()!= null ? org.getFullName() : "");
            view.setInn(org.getInn()!= null ? org.getInn() : "");
            view.setKpp(org.getKpp()!= null ? org.getKpp() : "");
            view.setAddress(org.getAddress()!= null ? org.getAddress() : "");
            view.setPhone(org.getPhone()!= null ? org.getPhone() : "");
            view.setActive(org.getActive()!= null ? org.getActive() : false);

            log.info("Get organization by id");
            log.info(view.toString());

        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(OrganizationView view) {
        boolean res = checkRes(dao.updateOrg(view));
        if(!res)
            log.error("Update organization is false");
        else log.info("Update organization successed");
        return res;
    }

    //ToDo сделать save
    @Override
    public boolean save(OrganizationView view) {
        dao.saveOrg(view);
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(id != null) {
            boolean res = checkRes(dao.deleteOrg(id));
            if (!res) {
                log.error("Delete organization is false");
            }else log.info("Delete organization successed");
            return res;
        } else return false;
    }

    public boolean checkRes(int val){
        if(val > 0)
            return true;
        else return false;
    }
}
