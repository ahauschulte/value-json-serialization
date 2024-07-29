package com.github.ahauschulte.demo.valueserialization;

/**
 * Record class representing a Cylinder with radius, height, and generic content.
 *
 * @param radius  the radius of the cylinder
 * @param height  the height of the cylinder
 * @param content the generic content associated with the cylinder
 * @param <T>     the type of the content.
 */
public record Cylinder<T>(double radius, double height, T content) implements Shape {
}
