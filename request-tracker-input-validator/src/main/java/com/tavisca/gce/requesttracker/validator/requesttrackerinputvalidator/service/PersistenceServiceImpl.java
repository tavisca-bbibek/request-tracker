package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersistenceServiceImpl implements PersistenceService {

    @Value("${service.name}")
    private String serviceName;

    @Value("${persister.url}")
    private  String persisterUrl;

    @Override
    public boolean save(String transactionId, String payload) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.put("transaction-id", List.of(String.valueOf(transactionId)));
        headers.put("service-name", List.of(serviceName));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(payload, headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(persisterUrl,
                HttpMethod.POST, request, Boolean.class);
        boolean persisted;
        try {
            persisted = response.getBody();
        } catch (NullPointerException e) {
            persisted = false;
        }
        return persisted;
    }
}
