package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.exception.InvalidPayloadException;
import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationServiceImpl implements ValidationService {

    private static final String PATTERN_DEPARTMENT_NAME = "^[A-Z]+[0-9]*$";
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_DEPARTMENT_LENGTH = 10;
    private static final int MIN_DEPARTMENT_LENGTH = 3;

    public  boolean validate(String payload) throws InvalidPayloadException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> errors = new HashMap<>();
        try {
            Employee employee = objectMapper.readValue(payload, Employee.class);
            Map<String, String> fieldErrors = getFieldErrors(employee);
            errors.putAll(fieldErrors);
        } catch (JsonProcessingException e) {
            errors.put("format", "invalid employee format, " + e.getMessage());
        }

        if(errors.isEmpty())
            return true;

        throw new InvalidPayloadException(errors, payload);
    }

    private  Map<String, String> getFieldErrors(Employee e) {
        Map<String, String> fieldErrors = new HashMap<>();
        if (e.getFirstName() == null
                || emptyOrWhiteSpace(e.getFirstName()))
            fieldErrors.put("firstName", "empty or white space");
        else if (e.getFirstName().length() >= MAX_NAME_LENGTH
                || e.getFirstName().length() < 2)
            fieldErrors.put("firstName", "length must between " +
                    MIN_NAME_LENGTH + " to " + MAX_NAME_LENGTH);

        if (e.getLastName() == null
                || emptyOrWhiteSpace(e.getLastName()))
            fieldErrors.put("lastName", "empty or white space");
        else if (e.getLastName().length() >= MAX_NAME_LENGTH
                || e.getLastName().length() < MIN_NAME_LENGTH)
            fieldErrors.put("lastName", "length must between " +
                    MIN_NAME_LENGTH + " to " + MAX_NAME_LENGTH);

        if (e.getDepartment() == null
                || emptyOrWhiteSpace(e.getDepartment()))
            fieldErrors.put("department", "empty or white space");
        else if (e.getDepartment().length() < MIN_DEPARTMENT_LENGTH
                || e.getDepartment().length() > MAX_DEPARTMENT_LENGTH)
            fieldErrors.put("department", "length must between " +
                    MIN_DEPARTMENT_LENGTH + " to " + MAX_DEPARTMENT_LENGTH);
        else if (!e.getDepartment().matches(PATTERN_DEPARTMENT_NAME))
            fieldErrors.put("department", "department must match pattern <" +
                    PATTERN_DEPARTMENT_NAME + ">");

        if(e.getSalary() == 0)
            fieldErrors.put("salary", "salary must not be zero");

        return fieldErrors;
    }

    private  boolean emptyOrWhiteSpace(String value) {
        return value.trim().isEmpty();
    }
}
