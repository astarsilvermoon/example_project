package ru.bellintegrator.practice.offices.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.exceptions.exceptions.OfficeException;
import ru.bellintegrator.practice.offices.dao.OfficeDAO;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.offices.service.OfficeService;
import ru.bellintegrator.practice.offices.view.*;
import ru.bellintegrator.practice.organizations.dao.OrganizationDAO;
import ru.bellintegrator.practice.organizations.model.Organization;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService{
    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private final OfficeDAO dao;
    @Autowired
    private  final OrganizationDAO organizationDAO;

    @Autowired
    public OfficeServiceImpl(OfficeDAO dao, OrganizationDAO organizationDAO) {
        this.dao = dao;
        this.organizationDAO = organizationDAO;
    }

    @Override
    public List<OfficeFilterView> getOfficeByOrgId(OfficeFilterView office) {
        List<Office> list = null;
        if(office!=null) {
            if (office.getOrgId() != null) {
                list = dao.getOfficesByOrgId(office);
                if (list != null) {
                    Function<Office, OfficeFilterView> mapOf = of -> {
                        OfficeFilterView view = new OfficeFilterView();
                        view.id = of.getId();
                        view.name = of.getName();
                        view.isActive = of.getIsActive();

                        log.info("Get office by organization id");
                        log.info(view.toString());
                        return view;
                    };
                    return list.stream().map(mapOf).collect(Collectors.toList());
                } else throw new OfficeException("Офисы с идентификатором организации = " + office.getOrgId() + "не найдены");
            } else throw new OfficeException("Не указан обязательный параметр: идентификатор организации");
        }else throw new OfficeException("Введенные данные об офисе пусты");
    }

    @Override
    public OfficeView getById(Long id) {
        OfficeView view =null;
        if (id != null) {
            Office office = dao.getOfficeById(id);
            if(office!=null) {
                 view = setViewFromOffice(office);
                 log.info("Получение офиса с идентификатором =" + id);
             }else throw new OfficeException("Офис с идентификатором = " + id + " не найден");
        }else throw new OfficeException("Идентификатор офиса = " + id + " NULL");
        return view;
    }

    @Override
    public boolean update(OfficeView office) {
        boolean res = false;
        Office finded = dao.getOfficeById(office.getId());
        if(office!=null && finded!=null) {
            Office of =setOfficeFromView(finded, office);
            res = checkRes(dao.updateOffice(of));
            if (!res) {
                throw new OfficeException("Не удалось обновить данные офиса");
            } else {
                log.info("Обновление данных фоиса прошщло успешно");
                return res;
            }
        }else throw new OfficeException("Введенные данные офиса пусты");
    }

    @Override
    public boolean delete(Long id) {
        if(id != null){
            Office finded = dao.getOfficeById(id);
            if(finded!=null) {
                dao.deleteOffice(id);
                log.info("Удаление офиса прошло успешно");
            }else throw  new OfficeException ("Не удалось найти офис с id=" + id);
        }else  throw new OfficeException("Идентификатор офиса = " + id + " NULL");

        return true;
    }

    @Override
    public boolean save(OfficeView view) {
        if(view!=null) {
            if (view.getOrgId() != null) {
                Office office = setOfficeFromView(new Office(), view);
                if (office != null) {
                    dao.saveOffice(office);
                    log.info("Сохранение офиса прошло успешно");
                } else throw new OfficeException("Не удалось сохранить данные офиса");
            } else throw new OfficeException("Введенные данные об офисе пусты");
        }else throw new OfficeException("Введенные данные об офисе пусты");
        return true;
    }

    public boolean checkRes(int val){
        if(val > 0)
            return true;
        else return false;
    }

    public Office setOfficeFromView( Office office, OfficeView view){
        office.setName(view.getName());
        office.setPhone(view.getPhone());
        office.setAddress(view.getAddress());
        office.setIsActive(true);

        Organization org = organizationDAO.getOrgById(view.getOrgId());
        office.setOrganizationId(org);
        return office;
    }

    public OfficeView setViewFromOffice(Office office) {
        OfficeView view = new OfficeView();
        if(office.getId()!= null)
            view.setId(office.getId());

        view.setName(office.getName()!= null ? office.getName() : "");
        view.setAddress(office.getAddress()!= null ? office.getAddress() : "");
        view.setPhone(office.getPhone()!= null ? office.getPhone() : "");
        view.setIsActive(office.getIsActive()!=null ? office.getIsActive() : false);

        log.info("Get office by id");
        log.info(view.toString());

        return view;
    }

}
