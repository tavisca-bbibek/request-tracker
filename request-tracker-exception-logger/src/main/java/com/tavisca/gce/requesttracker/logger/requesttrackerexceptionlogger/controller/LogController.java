package com.tavisca.gce.requesttracker.logger.requesttrackerexceptionlogger.controller;

import com.tavisca.gce.requesttracker.logger.requesttrackerexceptionlogger.model.Log;
import com.tavisca.gce.requesttracker.logger.requesttrackerexceptionlogger.service.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LogController {

    @Autowired
    LogRepository logRepository;

    @PostMapping("/log/exception")
    void logException(@RequestHeader MultiValueMap<String, String> headers, @RequestBody String exception) {
        Optional<String> maybeServiceName = headers.get("service-name").stream().findFirst();
        System.out.print("Service Name: " + maybeServiceName.get() + "Error: ");
        System.out.println(exception);
        logRepository.save(new Log(maybeServiceName.get(), exception));
    }
}
