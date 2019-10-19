package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.service;

public interface PersistenceService {
    boolean save(String transactionId, String payload);
}
