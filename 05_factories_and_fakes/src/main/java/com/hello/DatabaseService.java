package com.hello;


import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple mock database service for testing purposes
 */
public class DatabaseService {
    private static final Logger logger = Logger.getLogger(DatabaseService.class.getName());

    /**
     * Clears all entities of a specified class
     * 
     * @param entityClass the class of entities to clear
     */
    public static void clearAll(Class<?> entityClass) {
        logger.info("Clearing all entities of type: " + entityClass.getSimpleName());
        
        try {
            // This simulates clearing a database table by calling the static all() method
            // and then deleting each item
            Method allMethod = entityClass.getMethod("all");
            Object result = allMethod.invoke(null);
            
            if (result instanceof Iterable<?>) {
                Iterable<?> entities = (Iterable<?>) result;
                
                // Create a copy to avoid ConcurrentModificationException
                java.util.List<Object> entitiesToDelete = new java.util.ArrayList<>();
                for (Object entity : entities) {
                    entitiesToDelete.add(entity);
                }
                
                // Delete each entity
                Method deleteMethod = entityClass.getMethod("delete");
                for (Object entity : entitiesToDelete) {
                    deleteMethod.invoke(entity);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error clearing entities", e);
            throw new RuntimeException("Failed to clear database", e);
        }
    }
}