package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.exception;

import java.util.Map;
import java.util.stream.Collectors;

public class InvalidPayloadException extends Exception {
    private Map<String, String> errors;
    private String payload;

    public InvalidPayloadException(Map<String, String> errors, String payload) {
        super("payload contains " + errors.size() + " errors");
        this.errors = errors;
        this.payload = payload;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        String errorList = errors.keySet().stream()
                .map(field -> "'" + field + "': '" + errors.get(field) + "'")
                .collect(Collectors.joining(", ", "[", "]"));

        return "{ " +
                "'message': '" + getMessage() + "', " +
                "'payload': '" + getPayload() + "', " +
                "'errors': " + errorList +
                "}";

    }
}
