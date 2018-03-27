package ru.bellintegrator.practice.offices.dao;

import ru.bellintegrator.practice.offices.model.Office;
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
    List<Office> getOfficesByOrgId(Long orgId, OfficeView office) throws Exception;

    /**
     * Получить офис по его id
     */
     Office getOfficeById(Long id) throws Exception;

    /**
     * Изменить офис
     */
    Integer updateOffice(OfficeView office);

    /**
     * Сохранить офис
     * @param office
     */
    void saveOffice(OfficeView office);

    /***
     * Удалить офис
     */
    Integer deleteOffice(Long id);
}
