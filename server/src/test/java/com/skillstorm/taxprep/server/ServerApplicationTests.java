package com.skillstorm.taxprep.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerApplicationTests {

  @LocalServerPort
  private int port;

  private final TestRestTemplate restTemplate = new TestRestTemplate();

  @Test
  void homeEndpointReturnsHelloWorld() {
      String response = restTemplate.getForObject("http://localhost:" + port + "/", String.class);
      assertEquals("Hello World!", response);
  }
}
