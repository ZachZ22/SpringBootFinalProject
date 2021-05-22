package com.promineotech.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerTests {
    @LocalServerPort
    private int serverPort;

    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

    protected String getBaseUri() {
        return (String.format("http://localhost:%d", serverPort));
    }
}
