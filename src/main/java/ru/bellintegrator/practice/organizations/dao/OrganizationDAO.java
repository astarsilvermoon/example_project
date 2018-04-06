package ru.bellintegrator.practice.organizations.dao;

import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.organizations.view.OrganizationFilterView;
import ru.bellintegrator.practice.organizations.view.OrganizationView;

import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
public interface OrganizationDAO {

    /**
     * Получить все организации
     * */
    List<Organization> getAll(OrganizationFilterView org);

    /**
     * Получить организацию по id
     * */
    Organization getOrgById(Long id);

    /**
     * Изменить организацию
     * */
    Integer updateOrg(Organization org);

    /**
     * Сохранить организацию
     **/
    Boolean saveOrg(Organization org);

    /**
     * Удалить организацию
     *
     */
    void deleteOrg(Long id);

}
