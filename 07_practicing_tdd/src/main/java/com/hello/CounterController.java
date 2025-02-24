package com.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for Counter Service
 */
@RestController
@RequestMapping("/counters")
public class CounterController {

    private static final Logger logger = LoggerFactory.getLogger(CounterController.class);
    
    // In-memory storage for counters, similar to the COUNTERS dictionary in Python
    private static final Map<String, Integer> COUNTERS = new HashMap<>();

    /**
     * Creates a counter
     * 
     * @param name the name of the counter to create
     * @return ResponseEntity with the counter data or conflict message
     */
    @PostMapping("/{name}")
    public ResponseEntity<Map<String, Object>> createCounter(@PathVariable String name) {
        logger.info("Request to create counter: {}", name);
        
        // Check if counter already exists
        if (COUNTERS.containsKey(name)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", String.format("Counter %s already exists", name));
            return ResponseEntity.status(HttpStatus.HTTP_409_CONFLICT).body(response);
        }
        
        // Create new counter
        COUNTERS.put(name, 0);
        
        // Return the counter data
        Map<String, Object> response = new HashMap<>();
        response.put(name, COUNTERS.get(name));
        return ResponseEntity.status(HttpStatus.HTTP_201_CREATED).body(response);
    }
}