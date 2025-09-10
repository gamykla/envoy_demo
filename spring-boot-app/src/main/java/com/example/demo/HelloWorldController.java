package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RestController
public class HelloWorldController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World from Spring Boot!";
    }

    // Example endpoint that makes downstream calls
    // This demonstrates how Envoy will handle outgoing requests
    @GetMapping("/call-downstream")
    public String callDownstream() {
        try {
            // This call will go through Envoy proxy
            // Envoy will inject the appropriate access token
            String response = restTemplate.getForObject("http://localhost:8080/api/v1/downstream-service", String.class);
            return "Response from downstream: " + response;
        } catch (Exception e) {
            return "Error calling downstream service: " + e.getMessage();
        }
    }
}

@Configuration
class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
