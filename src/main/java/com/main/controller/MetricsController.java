package com.main.controller;

import com.main.service.MetricsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetricsController {


    private MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping(path = "/metrics",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getMetrics(@RequestHeader HttpHeaders headers) throws Exception {
        return this.metricsService.getMetrics();
    }

}
