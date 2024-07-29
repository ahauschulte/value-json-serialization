package com.github.ahauschulte.demo.valueserialization;

/**
 * Sealed interface representing a Shape, implemented by Circle, Cylinder, and Rectangle.
 */
public sealed interface Shape permits Circle, Cylinder, Rectangle {
}
