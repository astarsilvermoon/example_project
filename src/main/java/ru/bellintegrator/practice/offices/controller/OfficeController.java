package ru.bellintegrator.practice.offices.controller;

import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.offices.view.OfficeView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import java.util.List;

public interface OfficeController {

    ResponseView list(Long orgId, OfficeView view);

    ResponseView getById(Long id);

    ResponseView update(OfficeView office);

    ResponseView delete(Long id);

    ResponseView save(OfficeView office);
}
