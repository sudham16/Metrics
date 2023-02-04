package com.main.service;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetricsService {

    private OauthClient oauthClient;

    @Value("#{'${ccUrls}'.split(',')}")
    private List<String> ccUrls;

    public MetricsService(OauthClient oauthClient){
        this.oauthClient =oauthClient;
    }

    public List<String> getMetrics() throws OAuthProblemException, OAuthSystemException {
        List<String> metricResults = new ArrayList<>();
       String token= this.oauthClient.getOauthToken();
        HttpHeaders headers = Util.getHttpHeaders(token);
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        ccUrls.forEach(url -> {
           HttpEntity<String> request = new HttpEntity<>("", headers);
           ResponseEntity<String> result = restTemplate.postForEntity(url, request, String.class);
           System.out.println(result.getBody());
            metricResults.add(result.getBody());
       });
        return metricResults;


    }

}
