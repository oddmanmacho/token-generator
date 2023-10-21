package com.akirolabs.hometask.controller;

import com.akirolabs.hometask.constants.UrlConstants;
import com.akirolabs.hometask.service.GeneratorService;
import com.akirolabs.hometask.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeTaskController {
    private static final String NAME = "name";
    private static final String VERSION = "version";
    private static final String ENVIRONMENT = "environment";

    @Value("${api.name}")
    private String apiName;

    @Value("${api.version}")
    private String apiVersion;

    @Value("${api.environment}")
    private String apiEnvironment;

    @Autowired
    GeneratorService generatorService;

    @Autowired
    ValidatorService validatorService;

    /**
     * Provides the API Information.
     *
     * @return json consisting API information such as name and environment
     */
    @GetMapping(value = UrlConstants.INFO, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getApiInfo() {
        Map<String, String> response = new HashMap<>();
        response.put(NAME, apiName);
        response.put(VERSION, apiVersion);
        response.put(ENVIRONMENT, apiEnvironment);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = UrlConstants.GENERATE_TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateToken(@RequestParam(name = "digits") final String digitsAllowed) throws Exception {
        return ResponseEntity.ok(generatorService.generateToken(digitsAllowed));
    }

    @GetMapping(value = UrlConstants.VALIDATE_TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> validateToken(@RequestParam(name = "token") final String token) throws Exception {
        return ResponseEntity.ok(validatorService.validateToken(token));
    }
}
