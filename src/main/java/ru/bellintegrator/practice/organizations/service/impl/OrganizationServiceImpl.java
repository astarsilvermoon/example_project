package ru.bellintegrator.practice.organizations.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.exceptions.exceptions.OrganizationException;
import ru.bellintegrator.practice.organizations.dao.OrganizationDAO;
import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.organizations.service.OrganizationService;
import ru.bellintegrator.practice.organizations.view.OrganizationFilterView;
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
    public List<OrganizationView> getAllOrgs(OrganizationFilterView view) {
        if(view!=null) {
            if (view.getName() != null) {
                List<Organization> list = dao.getAll(view);
                if (list != null) {
                    Function<Organization, OrganizationView> mapOrg = org -> {
                        OrganizationView orgView = new OrganizationView();
                        orgView.id = org.getId();
                        orgView.inn = org.getInn();
                        orgView.name = org.getName();
                        orgView.isActive = org.getIsActive();

                        log.info("Получение списка организаций");
                        log.info(orgView.toString());
                        return orgView;
                    };
                    return list.stream().map(mapOrg).collect(Collectors.toList());
                } else throw new OrganizationException("Данные об организации не найдены");
            } else throw new OrganizationException("Имя организации обязательный параметр");
        }else throw new OrganizationException("Введенные данные об организации пусты");
    }

    @Override
    public OrganizationView getOrgById(Long id) {
            OrganizationView view = null;
            if(id!=null) {
                Organization org = dao.getOrgById(id);
                if (org != null) {
                    view = setViewFromOrg(org);
                    log.info("Поиск организации с id=" + id);
                    log.info(view.toString());
                } else throw new OrganizationException("Организация не найдена");
            }else throw new OrganizationException("Идентификатор организации NULL");

        return view;
    }

    @Override
    public boolean update(OrganizationView view) {
        boolean res = false;
        Organization finded = dao.getOrgById(view.getId());
        if(view!=null && finded!=null) {
            Organization org = setOrgFromView(finded, view);
            if(org!=null) {
                 res = checkRes(dao.updateOrg(org));
                if (!res)
                    throw  new OrganizationException("Введенные данные об организации пусты");
               else {
                    log.info("Обновление данных об организации прошло успешно");
                    return res;
                }
            } else return res;
        }else throw  new OrganizationException("Введенные данные об организации пусты");
    }

    @Override
    public boolean save(OrganizationView view) {
        if(view!=null) {
            Organization organization = setOrgFromView(new Organization(), view);
            if(organization!=null)
                    dao.saveOrg(organization);
                    log.info("Сохранение данных об организации прошло успешно");
            return true; //проверка
        } else throw  new OrganizationException ("Введенные данные об организации пусты");
    }

    @Override
    public boolean delete(Long id) {
        if(id != null) {
            Organization finded = dao.getOrgById(id);
            if(finded!=null) {
                dao.deleteOrg(id);
                log.info("Удаление организации с id=" + id + "прошло успешно");
            }else throw  new OrganizationException ("Не удалось найти организацию с id=" + id);
        }else throw  new OrganizationException ("Идентификатор организации NULL");
        return true;
    }

    public boolean checkRes(int val){
        if(val > 0)
            return true;
        else return false;
    }

    public Organization setOrgFromView(Organization organization, OrganizationView view){
        if(view!=null) {
            if(view.getName()!=null)
            organization.setName(view.getName());
            organization.setFullName(view.getFullName());
            organization.setInn(view.getInn());
            organization.setKpp(view.getKpp());
            organization.setAddress(view.getAddress());
            organization.setPhone(view.getPhone());
            organization.setIsActive(true);
        }
        return organization;
    }

    public  OrganizationView setViewFromOrg(Organization org) {
        OrganizationView view = new OrganizationView();
        if(org.getId()!= null)
            view.setId(org.getId());
        else return view;
        view.setName(org.getName()!= null ? org.getName() : "");
        view.setFullName(org.getFullName()!= null ? org.getFullName() : "");
        view.setInn(org.getInn()!= null ? org.getInn() : "");
        view.setKpp(org.getKpp()!= null ? org.getKpp() : "");
        view.setAddress(org.getAddress()!= null ? org.getAddress() : "");
        view.setPhone(org.getPhone()!= null ? org.getPhone() : "");
        view.setIsActive(org.getIsActive()!= null ? org.getIsActive() : false);

        return view;
    }
}
