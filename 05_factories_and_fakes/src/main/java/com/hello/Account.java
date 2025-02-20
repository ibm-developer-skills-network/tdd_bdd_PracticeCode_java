package com.hello;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Class that represents an Account
 */
public class Account {
    private static final Logger logger = Logger.getLogger(Account.class.getName());
    private static final List<Account> accounts = new ArrayList<>();
    
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean disabled;
    private LocalDate dateJoined;

    public Account() {
        this.disabled = false;
        this.dateJoined = LocalDate.now();
    }

    @Override
    public String toString() {
        return "<Account '" + name + "'>";
    }

    /**
     * Converts the account to a Map
     * 
     * @return a Map representation of the account
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("email", email);
        map.put("phoneNumber", phoneNumber);
        map.put("disabled", disabled);
        map.put("dateJoined", dateJoined);
        return map;
    }

    /**
     * Sets attributes from a Map
     * 
     * @param data the Map containing account data
     */
    public void fromMap(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            switch (key) {
                case "id":
                    if (value != null) {
                        this.id = value instanceof Long ? (Long) value : Long.valueOf(value.toString());
                    }
                    break;
                case "name":
                    this.name = (String) value;
                    break;
                case "email":
                    this.email = (String) value;
                    break;
                case "phoneNumber":
                    this.phoneNumber = (String) value;
                    break;
                case "disabled":
                    this.disabled = (Boolean) value;
                    break;
                case "dateJoined":
                    if (value instanceof LocalDate) {
                        this.dateJoined = (LocalDate) value;
                    }
                    break;
            }
        }
    }

    /**
     * Creates an Account in the database
     */
    public void create() {
        logger.info("Creating " + this.name);
        accounts.add(this);
    }

    /**
     * Updates an Account in the database
     */
    public void update() {
        logger.info("Saving " + this.name);
        if (this.id == null) {
            throw new DataValidationException("Update called with empty ID field");
        }
        // In a real application, this would update the database
    }

    /**
     * Removes an Account from the database
     */
    public void delete() {
        logger.info("Deleting " + this.name);
        accounts.remove(this);
    }

    // CLASS METHODS
    
    /**
     * Returns all of the Accounts in the database
     * 
     * @return a list of all accounts
     */
    public static List<Account> all() {
        logger.info("Processing all Accounts");
        return new ArrayList<>(accounts);
    }

    /**
     * Finds an Account by its ID
     * 
     * @param accountId the id of the Account to find
     * @return an instance with the accountId, or null if not found
     */
    public static Account find(Long accountId) {
        logger.info("Processing lookup for id " + accountId);
        return accounts.stream()
                .filter(account -> accountId.equals(account.getId()))
                .findFirst()
                .orElse(null);
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
}