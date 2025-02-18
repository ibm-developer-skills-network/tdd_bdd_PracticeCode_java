package com.hello;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static List<Map<String, Object>> accountData;
    private static final Random random = new Random();
    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void setUpClass() throws IOException {
        // Initialize Jackson ObjectMapper with JavaTimeModule for LocalDate support
        mapper.registerModule(new JavaTimeModule());
        
        // Load test data
        accountData = mapper.readValue(
            new File("src/test/resources/account_data.json"),
            new TypeReference<List<Map<String, Object>>>() {}
        );

        // Initialize JPA
        emf = Persistence.createEntityManagerFactory("test-coverage-lab");
    }

    @AfterAll
    static void tearDownClass() {
        if (emf != null) {
            emf.close();
        }
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        // Clear database before each test
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Account").executeUpdate();
        em.getTransaction().commit();
    }

    @AfterEach
    void tearDown() {
        if (em != null) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    @Test
    void testCreateAllAccounts() {
        em.getTransaction().begin();
        for (Map<String, Object> data : accountData) {
            Account account = new Account();
            account.fromMap(data);
            account.create(em);
        }
        em.getTransaction().commit();
        
        assertEquals(accountData.size(), Account.all(em).size());
    }

    @Test
    void testCreateAnAccount() {
        Map<String, Object> data = accountData.get(random.nextInt(accountData.size()));
        Account account = new Account();
        account.fromMap(data);
        
        em.getTransaction().begin();
        account.create(em);
        em.getTransaction().commit();
        
        assertEquals(1, Account.all(em).size());
    }

    

    @Test
    void testAccountToMap() {
        // Create account with known data
        Account account = new Account();
        account.setName("Test Name");
        account.setEmail("test@example.com");
        account.setPhoneNumber("123-456-7890");
        account.setDisabled(true);
        
        Map<String, Object> map = account.toMap();
        assertEquals("Test Name", map.get("name"));
        assertEquals("test@example.com", map.get("email"));
        assertEquals("123-456-7890", map.get("phoneNumber"));
        assertEquals(true, map.get("disabled"));
    }

    @Test
    void testAccountFromMapWithAllFields() {
        Map<String, Object> data = Map.of(
            "name", "Test Name",
            "email", "test@example.com",
            "phoneNumber", "123-456-7890",
            "disabled", true
        );
        
        Account account = new Account();
        account.fromMap(data);
        
        assertEquals("Test Name", account.getName());
        assertEquals("test@example.com", account.getEmail());
        assertEquals("123-456-7890", account.getPhoneNumber());
        assertTrue(account.isDisabled());
    }

    @Test
    void testAccountFromMapWithMissingFields() {
        // Test with completely empty map
        Map<String, Object> emptyData = Map.of();
        Account account = new Account();
        account.setName("Original Name");
        account.setDisabled(true);
        
        account.fromMap(emptyData);
        
        // Values should remain unchanged when map is empty
        assertEquals("Original Name", account.getName());
        assertTrue(account.isDisabled());
        
        // Test with map containing only email (name and disabled missing)
        Map<String, Object> partialData = Map.of(
            "email", "test@example.com"
        );
        
        account.fromMap(partialData);
        
        // Original values should still remain for untouched fields
        assertEquals("Original Name", account.getName());
        assertTrue(account.isDisabled());
        assertEquals("test@example.com", account.getEmail());
    }

    

    @Test
    void testUpdateAccount() {
        Map<String, Object> data = accountData.get(random.nextInt(accountData.size()));
        Account account = new Account();
        account.fromMap(data);
        
        em.getTransaction().begin();
        account.create(em);
        em.getTransaction().commit();
        
        assertNotNull(account.getId());
        
        account.setName("Updated Name");
        em.getTransaction().begin();
        account.update(em);
        em.getTransaction().commit();
        
        Account found = Account.find(em, account.getId());
        assertEquals("Updated Name", found.getName());
    }

    @Test
    void testUpdateWithoutId() {
        Account account = new Account();
        account.setName("Test");
        
        em.getTransaction().begin();
        assertThrows(DataValidationException.class, () -> account.update(em));
        em.getTransaction().rollback();
    }

    @Test
    void testAccountToMapWithNullFields() {
        Account account = new Account();
        // Don't set any fields - they should all be null except disabled
        
        Map<String, Object> map = account.toMap();
        assertEquals("", map.get("name"));
        assertEquals("", map.get("email"));
        assertEquals("", map.get("phoneNumber"));
        assertFalse((Boolean) map.get("disabled"));
        
        // Now set some fields
        account.setName("Test");
        account.setEmail(null);
        account.setPhoneNumber("123");
        
        map = account.toMap();
        assertEquals("Test", map.get("name"));
        assertEquals("", map.get("email"));
        assertEquals("123", map.get("phoneNumber"));
    }

    @Test
    void testDeleteAttachedAndDetachedAccounts() {
        // Test deleting an attached entity
        Account attachedAccount = new Account();
        attachedAccount.setName("Attached");
        
        em.getTransaction().begin();
        attachedAccount.create(em);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        attachedAccount.delete(em);
        em.getTransaction().commit();
        
        assertNull(Account.find(em, attachedAccount.getId()));
        
        // Test deleting a detached entity
        Account detachedAccount = new Account();
        detachedAccount.setName("Detached");
        
        em.getTransaction().begin();
        detachedAccount.create(em);
        em.getTransaction().commit();
        
        em.clear(); // Detach all entities
        
        em.getTransaction().begin();
        detachedAccount.delete(em);
        em.getTransaction().commit();
        
        assertNull(Account.find(em, detachedAccount.getId()));
    }
}