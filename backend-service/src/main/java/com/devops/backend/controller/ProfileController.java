package com.devops.backend.controller;

import com.devops.backend.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> profile(
            Authentication authentication) {

        return ResponseEntity.ok(

                service.getProfile(authentication.getName())

        );

    }

}
