package com.hello;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for Account model
 */
public class AccountTest {
    
    private List<Map<String, Object>> jsonData;
    private int rand;
    private static final ObjectMapper mapper = new ObjectMapper();
    
    @BeforeAll
    public static void setUpClass() {
        // Configure ObjectMapper to handle Java 8 date/time types
        mapper.registerModule(new JavaTimeModule());
    }
    
    @AfterAll
    public static void tearDownClass() {
        // Clean up resources
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        // Load test data from JSON file
        jsonData = mapper.readValue(
            new File("src/test/resources/account_data.json"),
            new TypeReference<List<Map<String, Object>>>() {}
        );
        rand = new Random().nextInt(jsonData.size());
        
        // Clear the database before each test
        DatabaseService.clearAll(Account.class);
    }
    
    // TEST CASES
    
    @Test
    public void testCreateAllAccounts() {
        // Test creating multiple Accounts
        for (Map<String, Object> data : jsonData) {
            Account account = new Account();
            account.fromMap(data);
            account.create();
        }
        assertEquals(jsonData.size(), Account.all().size());
    }
    
    @Test
    public void testCreateAnAccount() {
        // Test Account creation using known data
        Map<String, Object> data = jsonData.get(rand);
        Account account = new Account();
        account.fromMap(data);
        account.create();
        assertEquals(1, Account.all().size());
    }
    
    @Test
    public void testToString() {
        // Test the string representation of an account
        Account account = new Account();
        account.setName("Foo");
        assertEquals("<Account 'Foo'>", account.toString());
    }
    
    @Test
    public void testToMap() {
        // Test account to map
        Map<String, Object> data = jsonData.get(rand);
        Account account = new Account();
        account.fromMap(data);
        Map<String, Object> result = account.toMap();
        assertEquals(account.getName(), result.get("name"));
        assertEquals(account.getEmail(), result.get("email"));
        assertEquals(account.getPhoneNumber(), result.get("phoneNumber"));
        assertEquals(account.isDisabled(), result.get("disabled"));
    }
    
    @Test
    public void testFromMap() {
        // Test account from map
        Map<String, Object> data = jsonData.get(rand);
        Account account = new Account();
        account.fromMap(data);
        assertEquals(data.get("name"), account.getName());
        assertEquals(data.get("email"), account.getEmail());
        assertEquals(data.get("phoneNumber"), account.getPhoneNumber());
        assertEquals(data.get("disabled"), account.isDisabled());
    }
    
    @Test
    public void testUpdateAnAccount() {
        // Test Account update using known data
        Map<String, Object> data = jsonData.get(rand);
        Account account = new Account();
        account.fromMap(data);
        account.setId(1L); // Set ID for update
        account.create();
        assertNotNull(account.getId());
        
        account.setName("Rumpelstiltskin");
        account.update();
        
        Account found = Account.find(account.getId());
        assertEquals(account.getName(), found.getName());
    }
    
    @Test
    public void testInvalidIdOnUpdate() {
        // Test invalid ID update
        Map<String, Object> data = jsonData.get(rand);
        Account account = new Account();
        account.fromMap(data);
        account.setId(null);
        
        Exception exception = assertThrows(DataValidationException.class, () -> {
            account.update();
        });
        
        assertTrue(exception.getMessage().contains("empty ID field"));
    }
    
    @Test
    public void testDeleteAnAccount() {
        // Test Account delete using known data
        Map<String, Object> data = jsonData.get(rand);
        Account account = new Account();
        account.fromMap(data);
        account.create();
        assertEquals(1, Account.all().size());
        
        account.delete();
        assertEquals(0, Account.all().size());
    }
}