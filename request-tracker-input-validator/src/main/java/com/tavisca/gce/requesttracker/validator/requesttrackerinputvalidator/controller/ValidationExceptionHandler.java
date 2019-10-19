package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.controller;

import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @Autowired
    LoggingService loggingService;
    @ExceptionHandler(Exception.class)
    void handleException(Exception e) {
        loggingService.log(e.toString());
    }
}
