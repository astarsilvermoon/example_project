package ru.bellintegrator.practice.dictionaries.dao;

import ru.bellintegrator.practice.dictionaries.model.CountryCode;

import java.util.List;

/**
 * Created by Alena on 12.03.2018.
 */

public interface CountryCodeDAO {
    List<CountryCode> getAll();

    CountryCode getByNameAndCode(String name, String code);
}
