package com.javalife365.userapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard(){
        return ResponseEntity.status(200).body("Employee Dashboard");
    }
}
