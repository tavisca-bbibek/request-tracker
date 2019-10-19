package com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.service;

public interface ValidationService {
    boolean validate(String payload, int transactionId);
}
