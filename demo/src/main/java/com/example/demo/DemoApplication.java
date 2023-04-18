package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@RestController
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @GetMapping("/chuck")
  public String chuck(@RequestParam(value = "quote", defaultValue = "I'm Chuck!") String quote) {

    try {
      
      RestTemplate restTemplate = new RestTemplate();
      String url = "https://api.chucknorris.io/jokes/random";
      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
      assertEquals(response.getStatusCode(), HttpStatus.OK);
      quote = response.getBody();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return quote;
    
  }
}