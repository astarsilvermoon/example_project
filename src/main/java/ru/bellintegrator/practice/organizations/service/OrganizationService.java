package ru.bellintegrator.practice.organizations.service;

import ru.bellintegrator.practice.organizations.view.OrganizationFilterView;
import ru.bellintegrator.practice.organizations.view.OrganizationView;

import java.util.List;

public interface OrganizationService {

    List<OrganizationView> getAllOrgs(OrganizationFilterView view);

    OrganizationView getOrgById(Long id);

    boolean update(OrganizationView view);

    boolean save(OrganizationView view);

    boolean delete(Long id);
}
