package ru.bellintegrator.practice.dictionaries.dao;

import ru.bellintegrator.practice.dictionaries.model.DocType;

import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */
public interface DocTypeDAO {
    List<DocType> getAll();

    DocType getByNameAndCode(String name, String code);
}
