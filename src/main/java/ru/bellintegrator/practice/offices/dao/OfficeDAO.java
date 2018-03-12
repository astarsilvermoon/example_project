package ru.bellintegrator.practice.offices.dao;

import ru.bellintegrator.practice.offices.model.Office;

import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
public interface OfficeDAO {

    /**
     * Получить список офисов по id организации
     *
     */
    List<Office> getOfficesByOrgId(Long id);

    /**
     * Получить офис по его id
     */
     Office getOfficeById(Long id);

    /**
     * Изменить офис
     */
    void updateOffice(Office office);

    /**
     * Сохранить офис
     * @param office
     */
    void saveOffice(Office office);

}
