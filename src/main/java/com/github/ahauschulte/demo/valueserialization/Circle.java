package com.github.ahauschulte.demo.valueserialization;

/**
 * Record class representing a Circle with a radius.
 *
 * @param radius the radius of the circle
 */
public record Circle(double radius) implements Shape {
}
