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

    public Account() {
    }

    public Account(String name, String email, String phoneNumber, boolean disabled) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.disabled = disabled;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public boolean isDisabled() { return disabled; }
    public void setDisabled(boolean disabled) { this.disabled = disabled; }
    public LocalDate getDateJoined() { return dateJoined; }
    public void setDateJoined(LocalDate dateJoined) { this.dateJoined = dateJoined; }

    @Override
    public String toString() {
        return "Account{" + name + '}';
    }

    // Database operations
    public void create(EntityManager em) {
        logger.info("Creating account: {}", name);
        em.getTransaction().begin();
        em.persist(this);
        em.getTransaction().commit();
    }

    public void update(EntityManager em) {
        logger.info("Updating account: {}", name);
        if (id == null) {
            throw new DataValidationException("Update called with empty ID field");
        }
        em.getTransaction().begin();
        em.merge(this);
        em.getTransaction().commit();
    }

    public void delete(EntityManager em) {
        logger.info("Deleting account: {}", name);
        em.getTransaction().begin();
        em.remove(this);
        em.getTransaction().commit();
    }

    // Static methods
    public static List<Account> all(EntityManager em) {
        logger.info("Retrieving all accounts");
        return em.createQuery("SELECT a FROM Account a", Account.class)
                .getResultList();
    }

    public static Account find(EntityManager em, Long id) {
        logger.info("Finding account with id: {}", id);
        return em.find(Account.class, id);
    }

    public static void deleteAll(EntityManager em) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Account").executeUpdate();
        em.getTransaction().commit();
    }
}