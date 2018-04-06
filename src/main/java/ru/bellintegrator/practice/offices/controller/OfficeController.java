package ru.bellintegrator.practice.offices.controller;

import org.springframework.http.ResponseEntity;
import ru.bellintegrator.practice.offices.view.OfficeFilterView;
import ru.bellintegrator.practice.offices.view.OfficeView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import java.util.List;

public interface OfficeController {

    ResponseEntity list(Long orgId, OfficeFilterView view);

    ResponseEntity getById(Long id);

    ResponseEntity update(OfficeView office);

    ResponseEntity delete(OfficeView office);

    ResponseEntity save(OfficeView office);
}
