package ru.bellintegrator.practice.offices.service;

import ru.bellintegrator.practice.offices.view.OfficeFilterView;
import ru.bellintegrator.practice.offices.view.OfficeView;

import java.util.List;

public interface OfficeService {

    List<OfficeFilterView> getOfficeByOrgId(OfficeFilterView office);

    OfficeView getById(Long id);

    boolean update(OfficeView office);

    boolean delete(Long id);

    boolean save(OfficeView office);
}
