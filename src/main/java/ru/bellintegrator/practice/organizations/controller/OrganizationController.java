package ru.bellintegrator.practice.organizations.controller;

import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.organizations.view.OrganizationView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import java.util.List;

public interface OrganizationController {

    ResponseView list(OrganizationView org); // либо входные параметры name, inn, isActive

    ResponseView getById(Long id);

    ResponseView update(OrganizationView org);

    ResponseView save(OrganizationView org);

    ResponseView delete(Long id);

}
