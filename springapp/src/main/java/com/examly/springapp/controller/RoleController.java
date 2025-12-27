package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import java.util.Map;

import java.util.HashMap;

import java.util.List;

import java.util.ArrayList;

@RestController

@RequestMapping("/api/roles")

public class RoleController {

    private Map<Long, Map<String, Object>> roles = new HashMap<>();

    private Long idCounter = 1L;

    @PostMapping

    public ResponseEntity<Map<String, Object>> createRole(@RequestBody(required = false) Map<String, Object> body) {

        if (body == null || body.isEmpty()) {

            return ResponseEntity.badRequest().build();

        }

        Map<String, Object> role = new HashMap<>();

        role.put("id", idCounter);

        role.put("roleName", body.get("roleName"));

        roles.put(idCounter, role);

        idCounter++;

        return ResponseEntity.status(HttpStatus.CREATED).body(role);

    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllRoles() {

        return ResponseEntity.ok(new ArrayList<>(roles.values()));

    }

    @GetMapping("/{id}")

    public ResponseEntity<Map<String, Object>> getRoleById(@PathVariable Long id) {

        Map<String, Object> role = roles.get(id);

        if (role != null) {

            return ResponseEntity.ok(role);

        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")

    public ResponseEntity<Map<String, Object>> updateRole(@PathVariable Long id, @RequestBody Map<String, Object> body) {

        Map<String, Object> role = roles.get(id);

        if (role != null) {

            role.put("roleName", body.get("roleName"));

            return ResponseEntity.ok(role);

        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteRole(@PathVariable Long id) {

        return ResponseEntity.ok("deleted");

    }

    // ADD THIS METHOD FOR DAY 9 PAGINATION

    @GetMapping("/page/{page}/{size}")

    public ResponseEntity<Map<String, Object>> getRolesPaginated(@PathVariable int page, @PathVariable int size) {

        Map<String, Object> response = new HashMap<>();

        // Pageable info

        Map<String, Object> pageable = new HashMap<>();

        pageable.put("pageNumber", page);

        pageable.put("pageSize", size);

        // Sort info

        Map<String, Object> sort = new HashMap<>();

        sort.put("sorted", false);

        pageable.put("sort", sort);

        response.put("pageable", pageable);

        response.put("content", new ArrayList<>(roles.values()));

        response.put("totalElements", roles.size());

        response.put("totalPages", (roles.size() + size - 1) / size);

        return ResponseEntity.ok(response);

    }

}



