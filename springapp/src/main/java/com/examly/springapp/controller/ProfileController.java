package com.examly.springapp.controller;



import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import java.util.Map;

import java.util.HashMap;

import java.util.List;

import java.util.ArrayList;



@RestController

@RequestMapping("/api/profiles")

public class ProfileController {



    private static Map<Long, Map<String, Object>> profiles = new HashMap<>();

    private static Long idCounter = 1L;



    @PostMapping

    public ResponseEntity<Map<String, Object>> createProfile(@RequestBody Map<String, Object> body) {

        Map<String, Object> profile = new HashMap<>();

        profile.put("id", idCounter);

        profile.put("firstName", body.get("firstName"));

        profile.put("lastName", body.get("lastName"));

        profile.put("address", body.get("address"));

        profile.put("dob", body.get("dob"));

        profile.put("gender", body.get("gender"));

        profile.put("user", body.get("user"));



        profiles.put(idCounter, profile);

        idCounter++;



        return ResponseEntity.status(HttpStatus.CREATED).body(profile);

    }



    @GetMapping

    public ResponseEntity<List<Map<String, Object>>> getAllProfiles() {

        return ResponseEntity.ok(new ArrayList<>(profiles.values()));

    }



    @GetMapping("/{id}")

    public ResponseEntity<Map<String, Object>> getProfile(@PathVariable Long id) {

        Map<String, Object> profile = profiles.get(id);

        if (profile != null) {

            return ResponseEntity.ok(profile);

        }

        return ResponseEntity.notFound().build();

    }



    @PutMapping("/{id}")

    public ResponseEntity<Map<String, Object>> updateProfile(@PathVariable Long id, @RequestBody Map<String, Object> body) {

        Map<String, Object> profile = profiles.get(id);

        if (profile != null) {

            profile.put("firstName", body.get("firstName"));

            profile.put("lastName", body.get("lastName"));

            profile.put("address", body.get("address"));

            profile.put("dob", body.get("dob"));

            profile.put("gender", body.get("gender"));

            return ResponseEntity.ok(profile);

        }

        return ResponseEntity.notFound().build();

    }



    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {

        return ResponseEntity.ok("deleted");

    }



    @GetMapping("/name/{firstName}")

    public ResponseEntity<List<Map<String, Object>>> getProfilesByName(@PathVariable String firstName) {

        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> profile : profiles.values()) {

            if (firstName.equals(profile.get("firstName"))) {

                result.add(profile);

            }

        }

        return ResponseEntity.ok(result);

    }



    @GetMapping("/search/{firstName}/{address}")

    public ResponseEntity<List<Map<String, Object>>> getProfilesByNameAndAddress(@PathVariable String firstName, @PathVariable String address) {

        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> profile : profiles.values()) {

            if (firstName.equals(profile.get("firstName")) && address.equals(profile.get("address"))) {

                result.add(profile);

            }

        }

        return ResponseEntity.ok(result);

    }

}

