package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/logs")
public class AccountStatusLogController {

    private Map<Long, Map<String, Object>> logs = new HashMap<>();
    private Long idCounter = 1L;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createLog(@RequestBody Map<String, Object> body) {
        Map<String, Object> log = new HashMap<>();
        log.put("id", idCounter);
        log.put("status", body.get("status"));
        log.put("timestamp", body.get("timestamp"));
        logs.put(idCounter, log);
        idCounter++;
        return ResponseEntity.ok(log);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllLogs() {
        return ResponseEntity.ok(new ArrayList<>(logs.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getLogById(@PathVariable Long id) {
        Map<String, Object> log = logs.get(id);
        if (log != null) {
            return ResponseEntity.ok(log);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateLog(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Map<String, Object> log = logs.get(id);
        if (log != null) {
            log.put("status", body.get("status"));
            log.put("timestamp", body.get("timestamp"));
            return ResponseEntity.ok(log);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLog(@PathVariable Long id) {
        return ResponseEntity.ok("deleted");
    }
}
