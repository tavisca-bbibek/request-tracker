package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.controller;

import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.exception.InvalidPayloadException;
import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.model.Transaction;
import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.service.*;
import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
public class ValidationController {

    @Value("${service.name}")
    private String serviceName;

    @Autowired
    PersistenceService persistenceService;
    @Autowired
    ValidationService validationService;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    LoggingService loggingService;

    @PostMapping("/validate/employee")
    boolean validateEmployee(@RequestBody String payload, @RequestHeader MultiValueMap<String, String> headers) {
        Optional<String> maybeTransactionId = headers.get("transaction-id").stream().findFirst();
        Optional<String> mayBeServiceName = headers.get("service-name").stream().findFirst();

        boolean isValid = false;
        try {
            isValid = validationService.validate(payload);
        } catch (InvalidPayloadException e) {
            loggingService.log(e.toString());
        }
        if (isValid)
            persistenceService.save(maybeTransactionId.get(), payload);

        transactionRepository.save(
                new Transaction(
                        Integer.parseInt(maybeTransactionId.get()), isValid, Instant.now(),
                        mayBeServiceName.get(), serviceName, payload
                )
        );
        return isValid;
    }
}
