package ru.bellintegrator.practice.organizations.dao;

import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.organizations.view.OrganizationView;

import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
public interface OrganizationDAO {

    /**
     * Получить все организации
     * */
    List<Organization> getAll(OrganizationView org);

    /**
     * Получить организацию по id
     * */
    Organization getOrgById(Long id) throws Exception;

    /**
     * Изменить организацию
     * */
    Integer updateOrg(OrganizationView org);

    /**
     * Сохранить организацию
     **/
    void saveOrg(OrganizationView org);

    /**
     * Удалить организацию
     *
     */
    Integer deleteOrg(Long id);

}
