package ru.bellintegrator.practice.offices.service;

import ru.bellintegrator.practice.offices.view.OfficeView;

import java.util.List;

public interface OfficeService {

    List<OfficeView> getOfficeByOrgId(Long orgId, OfficeView office);

    OfficeView getById(Long id);

    boolean update(OfficeView office);

    boolean delete(Long id);

    boolean save(OfficeView office);
}
