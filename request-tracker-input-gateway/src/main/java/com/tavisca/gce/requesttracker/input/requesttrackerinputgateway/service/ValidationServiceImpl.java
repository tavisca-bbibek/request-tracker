package com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Value("${validator.url}")
    private  String validatorUrl;

    @Value("${service.name}")
    private  String serviceName;

    @Override
    public boolean validate(String payload, int transactionId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.put("transaction-id", List.of(String.valueOf(transactionId)));
        headers.put("service-name", List.of(serviceName));
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<String> request = new RequestEntity<>(payload, headers, HttpMethod.POST,
                URI.create(validatorUrl));
        return restTemplate.exchange(request, Boolean.class).getBody();
    }
}
