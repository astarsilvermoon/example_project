package ru.bellintegrator.practice.dictionaries.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dictionaries.dao.CountryCodeDAO;
import ru.bellintegrator.practice.dictionaries.model.CountryCode;
import ru.bellintegrator.practice.dictionaries.service.CountryCodeService;
import ru.bellintegrator.practice.dictionaries.view.CountryCodeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class CountryCodeServiceImpl implements CountryCodeService {
    private final Logger log = LoggerFactory.getLogger(CountryCodeServiceImpl.class);

    private final CountryCodeDAO dao;

    @Autowired
    public CountryCodeServiceImpl(CountryCodeDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<CountryCodeView> getAll() {
    List<CountryCode> list = dao.getAll();

        Function<CountryCode, CountryCodeView> mapCC = cc -> {
            CountryCodeView view = new CountryCodeView();
            view.id = cc.getId();
            view.name = cc.getName();
            view.code = cc.getCode();

            log.info("Get all of country codes dictionary");
            log.info(view.toString());
        return view;
    };
        return list.stream().map(mapCC).collect(Collectors.toList());
    }
}
