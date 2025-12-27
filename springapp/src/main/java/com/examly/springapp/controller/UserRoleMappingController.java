package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/userRoleMappings")
public class UserRoleMappingController {

    private static Map<Long, Map<String, Object>> mappings = new HashMap<>();
    private static Long idCounter = 1L;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createMapping(@RequestBody Map<String, Object> body) {
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("id", idCounter);
        
        // Handle nested user and role objects
        Map<String, Object> user = (Map<String, Object>) body.get("user");
        Map<String, Object> role = (Map<String, Object>) body.get("role");
        
        mapping.put("user", user);
        mapping.put("role", role);
        
        mappings.put(idCounter, mapping);
        idCounter++;
        
        return ResponseEntity.status(HttpStatus.CREATED).body(mapping);
    }



    @GetMapping

    public ResponseEntity<List<Map<String, Object>>> getAllMappings() {

        return ResponseEntity.ok(new ArrayList<>(mappings.values()));

    }



    @GetMapping("/{id}")

    public ResponseEntity<Map<String, Object>> getMappingById(@PathVariable Long id) {

        Map<String, Object> mapping = mappings.get(id);

        if (mapping != null) {

            return ResponseEntity.ok(mapping);

        }

        return ResponseEntity.notFound().build();

    }



    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMapping(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Map<String, Object> mapping = mappings.get(id);
        if (mapping != null) {
            // Handle nested user and role objects
            Map<String, Object> user = (Map<String, Object>) body.get("user");
            Map<String, Object> role = (Map<String, Object>) body.get("role");
            
            mapping.put("user", user);
            mapping.put("role", role);
            
            return ResponseEntity.ok(mapping);
        }
        return ResponseEntity.notFound().build();
    }



    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteMapping(@PathVariable Long id) {

        return ResponseEntity.ok("deleted");

    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getMappingsByUserId(@PathVariable Long userId) {
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (Map<String, Object> mapping : mappings.values()) {
            Map<String, Object> user = (Map<String, Object>) mapping.get("user");
            
            if (user != null) {
                Long mappingUserId = Long.valueOf(user.get("id").toString());
                if (userId.equals(mappingUserId)) {
                    result.add(mapping);
                }
            }
        }
        
        // Return 204 No Content if no mappings found
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(result);
    }



    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<Map<String, Object>> getMappingByUserIdAndRoleId(@PathVariable Long userId, @PathVariable Long roleId) {
        for (Map<String, Object> mapping : mappings.values()) {
            Map<String, Object> user = (Map<String, Object>) mapping.get("user");
            Map<String, Object> role = (Map<String, Object>) mapping.get("role");
            
            if (user != null && role != null) {
                Long mappingUserId = Long.valueOf(user.get("id").toString());
                Long mappingRoleId = Long.valueOf(role.get("id").toString());
                
                if (userId.equals(mappingUserId) && roleId.equals(mappingRoleId)) {
                    return ResponseEntity.ok(mapping);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

}

