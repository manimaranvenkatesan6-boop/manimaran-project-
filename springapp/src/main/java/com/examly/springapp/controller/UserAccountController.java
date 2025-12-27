package com.examly.springapp.controller;



import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.Map;

import java.util.HashMap;

import java.util.List;

import java.util.ArrayList;



@RestController
@RequestMapping("/api/users")

public class UserAccountController {



    private Map<Long, Map<String, Object>> users = new HashMap<>();

    private Long idCounter = 1L;



    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, Object> body) {

        Map<String, Object> user = new HashMap<>();

        user.put("id", idCounter);

        user.put("username", body.get("username"));

        user.put("email", body.get("email"));

        user.put("passwordHash", body.get("passwordHash"));

        user.put("phone", body.get("phone"));

        user.put("status", body.get("status"));



        users.put(idCounter, user);

        idCounter++;



        return ResponseEntity.ok(user);

    }



    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllUsers() {

        return ResponseEntity.ok(new ArrayList<>(users.values()));

    }



    @GetMapping("/{id}")

    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {

        Map<String, Object> user = users.get(id);

        if (user != null) {

            return ResponseEntity.ok(user);

        }

        return ResponseEntity.notFound().build();

    }



    @PutMapping("/{id}")

    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody Map<String, Object> body) {

        Map<String, Object> user = users.get(id);

        if (user != null) {

            user.put("username", body.get("username"));

            user.put("email", body.get("email"));

            user.put("passwordHash", body.get("passwordHash"));

            user.put("phone", body.get("phone"));

            user.put("status", body.get("status"));

            return ResponseEntity.ok(user);

        }

        return ResponseEntity.notFound().build();

    }



    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        return ResponseEntity.ok("deleted");

    }



    @GetMapping("/page/{page}/{size}")

    public ResponseEntity<Map<String, Object>> getUsersPaginated(@PathVariable int page, @PathVariable int size) {

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> pageable = new HashMap<>();

        pageable.put("pageNumber", page);

        pageable.put("pageSize", size);

        Map<String, Object> sort = new HashMap<>();

        sort.put("sorted", false);

        pageable.put("sort", sort);

        response.put("pageable", pageable);

        response.put("content", new ArrayList<>(users.values()));

        response.put("totalElements", users.size());

        response.put("totalPages", (users.size() + size - 1) / size);

        return ResponseEntity.ok(response);

    }

    }

    