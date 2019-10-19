package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.validation;

import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.exception.InvalidPayloadException;

public interface ValidationService {
    boolean validate(String payload) throws InvalidPayloadException;
}
