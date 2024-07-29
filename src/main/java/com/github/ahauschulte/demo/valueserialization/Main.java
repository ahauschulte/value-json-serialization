package com.github.ahauschulte.demo.valueserialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.util.List;

/**
 * Main class demonstrating the serialization and deserialization of immutable value objects
 * (Shapes: Circle, Cylinder, Rectangle) using Gson.
 */
public class Main {

    private static final String SHAPE_TYPE_PROPERTY_NAME = "shapeType";

    /**
     * The main method demonstrating the serialization and deserialization of immutable value objects
     * (Shapes: Circle, Cylinder, Rectangle) using Gson.
     *
     * @param args command line arguments (not used)
     */
    public static void main(final String[] args) {

        // Register shape types for polymorphic serialization
        final RuntimeTypeAdapterFactory<Shape> shapeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Shape.class, SHAPE_TYPE_PROPERTY_NAME)
                .registerSubtype(Circle.class, Circle.class.getName())
                .registerSubtype(Cylinder.class, Cylinder.class.getName())
                .registerSubtype(Rectangle.class, Rectangle.class.getName());

        // Create Gson instance with custom adapters
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Cylinder.class, new CylinderTypeAdapter())
                .registerTypeAdapterFactory(shapeAdapterFactory)
                .create();

        final Shape circle = new Circle(4.2);
        final Shape cylinderWithString = new Cylinder<>(4.4, 2.2, "Some content");
        final Shape cylinderWithInt = new Cylinder<>(12.3, 3.4, 42);
        final Shape cylinderWithDouble = new Cylinder<>(18.9, 5.2, 42.24);
        final Shape rectangle = new Rectangle(10, 20);

        final List<Shape> originalShapes = List.of(
                circle,
                cylinderWithString,
                cylinderWithInt,
                cylinderWithDouble,
                rectangle);

        System.out.println("Original shapes:");
        originalShapes.forEach(System.out::println);

        // Serialize shapes to JSON
        final List<String> serializedShapes = originalShapes.stream()
                .map(shape -> gson.toJson(shape, Shape.class))
                .toList();

        System.out.println("Serialized shapes:");
        serializedShapes.forEach(System.out::println);

        // Deserialize shapes from JSON
        final List<Shape> deserializedShapes = serializedShapes.stream()
                .map(serializedShape -> gson.fromJson(serializedShape, Shape.class))
                .toList();

        System.out.println("Deserialized shapes:");
        deserializedShapes.forEach(System.out::println);
    }
}