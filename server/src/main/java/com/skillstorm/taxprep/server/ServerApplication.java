package com.skillstorm.taxprep.server;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ServerApplication {

	@RequestMapping("/")
	public ResponseEntity<?> home() {
		return ResponseEntity.noContent().build();
	}

  @GetMapping("/login")
  public ResponseEntity<?> login() {
    return ResponseEntity.noContent().build();
  }

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
