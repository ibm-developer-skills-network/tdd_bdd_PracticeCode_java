package com.hello;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    private final Triangle triangle = new Triangle();

    @Test
    @DisplayName("Calculate area with valid inputs")
    void testValidArea() {
        assertEquals(50.0, triangle.areaOfTriangle(10.0, 10.0));
        assertEquals(0.0, triangle.areaOfTriangle(0.0, 10.0));
    }

    @Test
    @DisplayName("Reject negative base")
    void testNegativeBase() {
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> triangle.areaOfTriangle(-1.0, 10.0));
        assertEquals("Base must be a positive number", exception.getMessage());
    }

    @Test
    @DisplayName("Reject negative height")
    void testNegativeHeight() {
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> triangle.areaOfTriangle(10.0, -1.0));
        assertEquals("Height must be a positive number", exception.getMessage());
    }

    @Test
    @DisplayName("Reject invalid numbers")
    void testInvalidNumbers() {
        assertThrows(IllegalArgumentException.class, 
            () -> triangle.areaOfTriangle(Double.NaN, 10.0));
        assertThrows(IllegalArgumentException.class, 
            () -> triangle.areaOfTriangle(10.0, Double.POSITIVE_INFINITY));
    }
}
