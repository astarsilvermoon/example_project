package ru.bellintegrator.practice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bellintegrator.practice.exceptions.exceptions.*;
import ru.bellintegrator.practice.universal.view.ResponseView;


@ControllerAdvice
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(ProjectExceptionHandler.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected @ResponseBody ResponseEntity<?> handleDaoExceptions(RuntimeException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseEntity<>(new ResponseView(e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }


        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid
        (MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
            log.error(e.getMessage(), e.getCause());
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : e.getBindingResult().getAllErrors()) {
                sb.append(error.getDefaultMessage());
                sb.append("  ");
            }
            return new ResponseEntity<>(new ResponseView("Ошибка валидации: " + sb.toString()), HttpStatus.BAD_REQUEST);
        }
    @ExceptionHandler({AccountException.class, OrganizationException.class, OfficeException.class, UserException.class, DictionaryException.class})
    protected @ResponseBody
    ResponseEntity<?> handleAllCustomExceptions(RuntimeException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseEntity<>(new ResponseView(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<?> handleAllExceptions(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ResponseView("Внутренняя ошибка сервиса"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
