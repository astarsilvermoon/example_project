package ru.bellintegrator.practice.offices.dao;

import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.offices.view.OfficeFilterView;
import ru.bellintegrator.practice.offices.view.OfficeView;

import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
public interface OfficeDAO {

    /**
     * Получить список офисов по id организации
     *
     */
    List<Office> getOfficesByOrgId(OfficeFilterView office);

    /**
     * Получить офис по его id
     */
    Office getOfficeById(Long id);

    /**
     * Изменить офис
     */
    Integer updateOffice(Office office);

    /**
     * Сохранить офис
     * @param office
     */
    Boolean saveOffice(Office office);

    /***
     * Удалить офис
     */
    void deleteOffice(Long id);
}
