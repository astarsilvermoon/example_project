package ru.bellintegrator.practice.organizations.dao;

import ru.bellintegrator.practice.organizations.model.Organization;

import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
public interface OrganizationDAO {

    /**
     * Получить все организации
     * */
    List<Organization> getAll();

    /**
     * Получить организацию по id
     * */
    Organization getOrgById(Long id);

    /**
     * Изменить организацию
     * */
    void updateOrg(Organization org);

    /**
     * Сохранить организацию
     **/
    void saveOrg(Organization org);

}
