package com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class LoggingServiceImpl implements LoggingService {

    @Value("${logger.url}")
    private  String loggerUrl;

    @Value("${service.name}")
    private  String serviceName;

    @Override
    public void log(String message) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("service-name", List.of(serviceName));

        RequestEntity<String> request = new RequestEntity<>(message, headers, HttpMethod.POST, URI.create(loggerUrl));
        restTemplate.exchange(request, Void.class);
    }
}
