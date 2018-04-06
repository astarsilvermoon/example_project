package ru.bellintegrator.practice.dictionaries.controller;

import org.springframework.http.ResponseEntity;
import ru.bellintegrator.practice.dictionaries.view.DocTypeView;
import ru.bellintegrator.practice.universal.view.ResponseView;

import java.util.List;

public interface DocTypeController {

    ResponseEntity getAll();
}
