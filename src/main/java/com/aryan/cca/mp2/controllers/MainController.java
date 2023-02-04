package com.aryan.cca.mp2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.cca.mp2.mapping.InputMapping;

@RestController
@RequestMapping("hello-world/")
public class MainController {
    public static int num = 0;
    
    @GetMapping("/")
    public ResponseEntity<Integer> get() {
        return ResponseEntity.ok(Integer.valueOf(num));
    }

    @PostMapping("/")
    public ResponseEntity<Integer> post(@RequestBody InputMapping input) {
        num = input.getNum();

        return ResponseEntity.ok(Integer.valueOf(num));
    }
    
}
