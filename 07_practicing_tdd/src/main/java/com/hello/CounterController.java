package com.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/")
public class CounterController {
    
    private static final Logger logger = LoggerFactory.getLogger(CounterController.class);
    private final ConcurrentHashMap<String, Integer> COUNTER = new ConcurrentHashMap<>();



    @PostMapping("/counters/{name}")
    public ResponseEntity<Map<String, Object>> createCounter(@PathVariable String name) {
        logger.info("Request to Create counter: {}...", name);
        
        if (COUNTER.containsKey(name)) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("error", String.format("Counter %s already exists", name)));
        }

        COUNTER.put(name, 0);
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .buildAndExpand(name)
            .toUri();

        return ResponseEntity
            .created(location)
            .body(Map.of("name", name, "counter", 0));
    }

   

    public void resetCounters() {
        COUNTER.clear();
    }
}