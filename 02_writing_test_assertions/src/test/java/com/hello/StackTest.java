package com.hello;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for Stack
 */
class TestStack {
    private Stack<Object> stack;
    
    /**
     * Setup before each test
     */
    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }
    
    /**
     * Tear down after each test
     */
    @AfterEach
    void tearDown() {
        stack = null;
    }
    
    /**
     * Test pushing an item into the stack
     */
    @Test
    void testPush() {
        Assertions.fail("not implemented");
    }
    
    /**
     * Test popping an item off of the stack
     */
    @Test
    void testPop() {
        Assertions.fail("not implemented");
    }
    
    /**
     * Test peeking at the top of the stack
     */
    @Test
    void testPeek() {
        Assertions.fail("not implemented");
    }
    
    /**
     * Test if the stack is empty
     */
    @Test
    void testIsEmpty() {
        Assertions.fail("not implemented");
    }
}