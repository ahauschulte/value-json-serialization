package com.github.ahauschulte.demo.valueserialization;

/**
 * Record class representing a Rectangle with dimensions a and b.
 *
 * @param a the width of the rectangle
 * @param b the height of the rectangle
 */
public record Rectangle(int a, int b) implements Shape {
}
