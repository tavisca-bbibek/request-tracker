package com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.model.Employee;
import com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.model.Transaction;
import com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.service.EmployeeRepository;
import com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.service.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Value("${service.name}")
    private String serviceName;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/employee")
    boolean save(@RequestBody Employee employee, @RequestHeader MultiValueMap<String, String> headers) throws JsonProcessingException {
        Optional<String> maybeTransactionId = headers.get("transaction-id").stream().findFirst();
        Optional<String> mayBeServiceName = headers.get("service-name").stream().findFirst();

        boolean isSaved = employeeRepository.save(employee) != null;
        transactionRepository.save(
                new Transaction(
                        Integer.parseInt(maybeTransactionId.get()), isSaved, Instant.now(),
                        mayBeServiceName.get(), serviceName, new ObjectMapper().writeValueAsString(employee)
                )
        );

        return isSaved;
    }

    @GetMapping("/employee")
    Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
