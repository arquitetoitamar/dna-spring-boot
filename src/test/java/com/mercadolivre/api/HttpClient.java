package com.mercadolivre.api;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class HttpClient {
    private final String SERVER_URL = "http://localhost:8080";
    private final String ENDPOINT = "/simian";

    private final RestTemplate restTemplate = new RestTemplate();

    private String getEndpoint() {
        return SERVER_URL + ENDPOINT;
    }

    public int post(final String something) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(something ,headers);
        return restTemplate.postForEntity(getEndpoint(), entity, Void.class).getStatusCodeValue();
    }
}
