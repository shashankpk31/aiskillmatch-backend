package com.shashankpk31.aiskillmatch_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/public")
public class VisitorController {
    @GetMapping("/home/")
    public String getHome() {
        return "Hello world!";
    }
    
}
