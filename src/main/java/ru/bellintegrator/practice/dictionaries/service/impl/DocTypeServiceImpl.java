package ru.bellintegrator.practice.dictionaries.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dictionaries.dao.DocTypeDAO;
import ru.bellintegrator.practice.dictionaries.model.DocType;
import ru.bellintegrator.practice.dictionaries.service.DocTypeService;
import ru.bellintegrator.practice.dictionaries.view.DocTypeView;
import ru.bellintegrator.practice.exceptions.exceptions.DictionaryException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class DocTypeServiceImpl implements DocTypeService {

    private final Logger log = LoggerFactory.getLogger(CountryCodeServiceImpl.class);

    private final DocTypeDAO dao;

    @Autowired
    public DocTypeServiceImpl(DocTypeDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<DocTypeView> getAll() {
        List<DocType> list = dao.getAll();
        if (list != null) {

            Function<DocType, DocTypeView> mapDT = dt -> {
                DocTypeView view = new DocTypeView();
                view.id = dt.getId();
                view.name = dt.getName();
                view.code = dt.getCode();

                log.info("Get all of doc types dictionary");
                log.info(view.toString());
                return view;
            };
            return list.stream().map(mapDT).collect(Collectors.toList());
        } else throw new DictionaryException("Не удалось получитбь значения справочника DocType");
    }
}
