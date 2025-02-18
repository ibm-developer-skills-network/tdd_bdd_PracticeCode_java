package com.hello;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "accounts")
public class Account {
    private static final Logger logger = LoggerFactory.getLogger(Account.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 64)
    private String email;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    private boolean disabled = false;

    @Column(name = "date_joined")
    private LocalDate dateJoined = LocalDate.now();

    // Default constructor required by JPA
    public Account() {
    }

    // Constructor with all fields
    public Account(String name, String email, String phoneNumber, boolean disabled) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.disabled = disabled;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public String toString() {
        return "<Account '" + name + "'>";
    }

    // Database operations
    public void create(EntityManager em) {
        logger.info("Creating {}", name);
        em.persist(this);
    }

    public void update(EntityManager em) {
        logger.info("Saving {}", name);
        if (id == null) {
            throw new DataValidationException("Update called with empty ID field");
        }
        em.merge(this);
    }

    public void delete(EntityManager em) {
        logger.info("Deleting {}", name);
        em.remove(em.contains(this) ? this : em.merge(this));
    }

    // Static methods
    public static List<Account> all(EntityManager em) {
        logger.info("Processing all Accounts");
        return em.createQuery("SELECT a FROM Account a", Account.class)
                .getResultList();
    }

    public static Account find(EntityManager em, Long id) {
        logger.info("Processing lookup for id {} ...", id);
        return em.find(Account.class, id);
    }

    // Conversion methods
    public Map<String, Object> toMap() {
        return Map.of(
            "name", name != null ? name : "",
            "email", email != null ? email : "",
            "phoneNumber", phoneNumber != null ? phoneNumber : "",
            "disabled", disabled
        );
    }

    public void fromMap(Map<String, Object> data) {
        if (data.containsKey("name")) this.name = (String) data.get("name");
        if (data.containsKey("email")) this.email = (String) data.get("email");
        if (data.containsKey("phoneNumber")) this.phoneNumber = (String) data.get("phoneNumber");
        if (data.containsKey("disabled")) this.disabled = Boolean.TRUE.equals(data.get("disabled"));
    }
}