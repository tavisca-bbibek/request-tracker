package com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.controller;

import com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @Autowired
    LoggingService loggingService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void handleException(Exception e) {
        loggingService.log(e.toString());
    }
}
