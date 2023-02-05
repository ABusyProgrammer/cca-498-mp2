package com.aryan.cca.mp2.controllers;

import com.aryan.cca.mp2.clients.DdbClient;
import com.aryan.cca.mp2.mapping.InputMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {   
    
   DdbClient dynamoDb = new DdbClient();

    @GetMapping("/")
    public ResponseEntity<String> get() {
       return ResponseEntity.ok(dynamoDb.getNumber());
    }

    @PostMapping("/")
    public ResponseEntity<String> post(@RequestBody InputMapping input) {
       dynamoDb.putNumber(String.valueOf(input.getNum()));

        return ResponseEntity.ok(dynamoDb.getNumber());
    }
    
}
