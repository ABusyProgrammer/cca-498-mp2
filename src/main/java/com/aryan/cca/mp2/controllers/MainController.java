package com.aryan.cca.mp2.controllers;

import com.aryan.cca.mp2.mapping.InputMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    public static Integer num = 0;
    
    @GetMapping("/")
    public ResponseEntity<Integer> get() {
        return ResponseEntity.ok(num);
    }

    @PostMapping("/")
    public ResponseEntity<Integer> post(@RequestBody InputMapping input) {
        num = input.getNum();

        return ResponseEntity.ok(num);
    }
    
}
