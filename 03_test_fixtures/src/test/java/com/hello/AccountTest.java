package com.hello;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;
import com.hello.Account;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private static List<Map<String, Object>> ACCOUNT_DATA;

    @BeforeAll
    void setUpClass() throws IOException {
        // Create EntityManagerFactory
        
        // Load test data
       
    }

    @AfterAll
    void tearDownClass() {
       
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        
    }

    @AfterEach
    void tearDown() {
       
    }

   
}
