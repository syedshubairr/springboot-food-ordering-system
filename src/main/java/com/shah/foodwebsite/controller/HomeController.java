package com.shah.foodwebsite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<String> page() {
        return new ResponseEntity<>("Welcome to new Project", HttpStatus.OK);
    }
}
