package com.devops.backend.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProfileService {

    public Map<String, Object> getProfile(String username) {

        Map<String, Object> profile = new HashMap<>();

        profile.put("username", username);
        profile.put("fullName", "DevOps User");
        profile.put("email", username);
        profile.put("role", "ROLE_USER");
        profile.put("status", "ACTIVE");

        return profile;
    }

}
