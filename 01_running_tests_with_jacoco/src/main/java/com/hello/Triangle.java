package com.hello;

public class Triangle {
    public double areaOfTriangle(double base, double height) {
        if (!isNumber(base)) {
            throw new IllegalArgumentException("Base must be a number");
        }
        if (!isNumber(height)) {
            throw new IllegalArgumentException("Height must be a number");
        }
        if (base < 0) {
            throw new IllegalArgumentException("Base must be a positive number");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Height must be a positive number");
        }
        return (base / 2) * height;
    }

    private boolean isNumber(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }
}
