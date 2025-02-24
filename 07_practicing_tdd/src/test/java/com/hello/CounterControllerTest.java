package com.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Cases for Counter Web Service
 * These tests are intentionally left incomplete as per requirements
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateACounter() throws Exception {
        // It should create a counter
        MvcResult result = mockMvc.perform(post("/counters/foo"))
                .andExpect(status().is(HttpStatus.HTTP_201_CREATED))
                .andReturn();
                
        String content = result.getResponse().getContentAsString();
        // TODO: Complete assertions to verify the response JSON contains "foo": 0
        assertTrue(content.contains("foo"));
    }

    @Test
    public void testDuplicateCounter() throws Exception {
        // It should return an error for duplicates
        mockMvc.perform(post("/counters/bar"))
                .andExpect(status().is(HttpStatus.HTTP_201_CREATED));
                
        mockMvc.perform(post("/counters/bar"))
                .andExpect(status().is(HttpStatus.HTTP_409_CONFLICT));
        
        // TODO: Complete assertions to verify the response message
    }
}