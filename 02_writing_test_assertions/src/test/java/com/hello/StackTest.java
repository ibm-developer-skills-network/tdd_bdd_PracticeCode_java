package com.hello;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;

/**
 * Test cases for Stack
 */
class StackTest {
    private Stack<String> stack;
    
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
        stack.push("test");
        assertFalse(stack.isEmpty());
        assertEquals("test", stack.peek());
    }
    
    /**
     * Test popping an item off of the stack
     */
    @Test
    void testPop() {
        stack.push("first");
        stack.push("second");
        
        assertEquals("second", stack.pop());
        assertEquals("first", stack.pop());
        assertTrue(stack.isEmpty());
        
        // Test popping from empty stack
        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        });
    }
    
    /**
     * Test peeking at the top of the stack
     */
    @Test
    void testPeek() {
        stack.push("test");
        assertEquals("test", stack.peek());
        assertFalse(stack.isEmpty());  // Verify peek doesn't remove the item
        
        // Test peeking at empty stack
        Stack<String> emptyStack = new Stack<>();
        assertThrows(EmptyStackException.class, () -> {
            emptyStack.peek();
        });
    }
    
    /**
     * Test if the stack is empty
     */
    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty());
        
        stack.push("test");
        assertFalse(stack.isEmpty());
        
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}