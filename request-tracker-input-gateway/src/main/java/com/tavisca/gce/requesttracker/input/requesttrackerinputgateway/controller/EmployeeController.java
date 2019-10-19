package com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.controller;

import com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.model.Transaction;
import com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.service.TransactionRepository;
import com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class EmployeeController {

    @Value("${service.name}")
    private String serviceName;

    @Autowired
    ValidationService validationService;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/employee")
    String saveEmployee(@RequestBody(required = false) String payload, @RequestHeader MultiValueMap<String, String> headers) {
        boolean isAvailable = payload != null && !payload.isEmpty();

        int transactionId;
        transactionId = transactionRepository.save(
                new Transaction(isAvailable, Instant.now(),
                        headers.get("user-agent").toString(), serviceName, payload)
        ).getId();

        if (isAvailable) {
            boolean isValid = validationService.validate(payload, transactionId);
            return isValid ? "Success: valid payload" : "Failure: invalid payload";
        } else
            return "Error: empty payload";
    }
}
