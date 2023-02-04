package com.main.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class Util {

    public static HttpHeaders getHttpHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        if (StringUtils.hasText(token)) {
            headers.set("Authorization", "Bearer " + token);
        }
        headers.set("Trace-Id", UUID.randomUUID().toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Language", "en_US");
        headers.set(HttpHeaders.ACCEPT_ENCODING, "gzip");
        headers.set(HttpHeaders.ACCEPT, "application/json");
        return headers;
    }
}
