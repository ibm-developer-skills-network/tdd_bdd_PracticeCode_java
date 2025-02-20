package com.hello;


import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Factory class for creating fake Account instances for testing
 * 
 * This class uses the JavaFaker library to generate realistic test data
 */
public class AccountFactory {
    
    // Create a single instance of Faker to be reused
    private static final Faker faker = new Faker();
    
    // Counter for generating unique IDs
    private static final AtomicLong idGenerator = new AtomicLong();
    
    /**
     * Creates a fake Account with realistic randomly generated data
     * 
     * @return a new Account instance with fake data
     */
    public static Account createAccount() {
        Account account = new Account();
        
        // Generate a unique ID
        account.setId(idGenerator.incrementAndGet());
        
        // Generate a realistic name
        account.setName(faker.name().fullName());
        
        // Generate a realistic email address
        account.setEmail(faker.internet().emailAddress());
        
        // Generate a realistic phone number
        account.setPhoneNumber(faker.phoneNumber().phoneNumber());
        
        // Randomly set the disabled flag
        account.setDisabled(faker.bool().bool());
        
        // Generate a random date joined between Jan 1, 2008 and today
        LocalDate startDate = LocalDate.of(2008, 1, 1);
        LocalDate endDate = LocalDate.now();
        
        // Convert LocalDate to Date for Faker
        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        // Generate a random date between start and end
        Date randomDate = faker.date().between(start, end);
        
        // Convert back to LocalDate
        account.setDateJoined(randomDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        
        return account;
    }
    
    /**
     * Creates a fake Account with the specified name
     * 
     * @param name the name to use for the account
     * @return a new Account instance with the specified name and other fake data
     */
    public static Account createAccountWithName(String name) {
        Account account = createAccount();
        account.setName(name);
        return account;
    }
    
    /**
     * Creates a fake Account with the disabled flag set to the specified value
     * 
     * @param disabled the value to set for the disabled flag
     * @return a new Account instance with the specified disabled flag and other fake data
     */
    public static Account createAccountWithDisabledFlag(boolean disabled) {
        Account account = createAccount();
        account.setDisabled(disabled);
        return account;
    }
}