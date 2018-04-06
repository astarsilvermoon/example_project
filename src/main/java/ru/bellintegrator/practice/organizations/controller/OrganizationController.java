package ru.bellintegrator.practice.organizations.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.organizations.view.OrganizationFilterView;
import ru.bellintegrator.practice.organizations.view.OrganizationView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import java.util.List;

public interface OrganizationController {

    ResponseEntity list(@RequestBody OrganizationFilterView org);

    ResponseEntity getById(Long id);

    ResponseEntity update(@RequestBody OrganizationView org);

    ResponseEntity save(@RequestBody OrganizationView org);

    ResponseEntity delete(@RequestBody OrganizationView org);


}
