package com.demo.employee.controller;
/*
 * @author shalshe  created on 12/1/2025
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/employees")
public class EmployeeServiceRestController {

    @Autowired
    private RestTemplate restTemplate;

    private boolean isUnauthorized(String header) {
        return header == null || !header.equals("Bearer valid-token");
    }

    @GetMapping("")
    public ResponseEntity<?> getEmployees(
            @RequestHeader(value="Authorization", required=false) String authHeader) {

        if (isUnauthorized(authHeader)) {
            System.out.println("Unauthorized access to Employee service");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        // ---------------------------
        // Resource-level: call Department service
        // ---------------------------
        try {
            String departments = restTemplate.getForObject(
                    "http://department-service:8080/departments",
                    String.class);

            return ResponseEntity.ok("Employees work in departments: " + departments);

        } catch (RestClientException ex) {
            System.out.println("Department service unreachable!");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Department service down");
        }
    }
    @GetMapping("/whoami")
public ResponseEntity<String> whoami() {
    return ResponseEntity.ok("employee-service");
}
@GetMapping("/departments/slow")
public ResponseEntity<?> callSlowDept(
        @RequestHeader(value="Authorization", required=false) String authHeader) {

    if (isUnauthorized(authHeader)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    return ResponseEntity.ok(
        restTemplate.getForObject(
            "http://department-service:8080/departments/slow",
            String.class
        )
    );
}

@GetMapping("/departments/error")
public ResponseEntity<?> callErrorDept(
        @RequestHeader(value="Authorization", required=false) String authHeader) {

    if (isUnauthorized(authHeader)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    return ResponseEntity.ok(
        restTemplate.getForObject(
            "http://department-service:8080/departments/error",
            String.class
        )
    );
}
@GetMapping("/departments/slow")
public ResponseEntity<?> callSlowDept(
        @RequestHeader(value="Authorization", required=false) String authHeader) {

    if (isUnauthorized(authHeader)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    return ResponseEntity.ok(
        restTemplate.getForObject(
            "http://department-service:8080/departments/slow",
            String.class
        )
    );
}

@GetMapping("/departments/error")
public ResponseEntity<?> callErrorDept(
        @RequestHeader(value="Authorization", required=false) String authHeader) {

    if (isUnauthorized(authHeader)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    return ResponseEntity.ok(
        restTemplate.getForObject(
            "http://department-service:8080/departments/error",
            String.class
        )
    );
}

    
}
