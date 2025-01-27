package com.hello;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Implements a Stack data structure
 * @param <T> the type of elements in the stack
 */
public class Stack<T> {
    /** Storage for stack elements */
    private ArrayList<T> items;
    
    /**
     * Constructor
     */
    public Stack() {
        items = new ArrayList<>();
    }
    
    /**
     * Places an item onto the stack
     * @param data the item to be pushed onto the stack
     */
    public void push(T data) {
        items.add(data);
    }
    
    /**
     * Removes an item from the stack and returns it
     * @return the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return items.remove(items.size() - 1);
    }
    
    /**
     * Returns the item at the top of the stack without removing it
     * @return the item at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return items.get(items.size() - 1);
    }
    
    /**
     * Returns true if the stack is empty, otherwise returns false
     * @return true if the stack has no elements
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
