package com.devops.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health(){

        return "Backend Service Running";

    }

}
