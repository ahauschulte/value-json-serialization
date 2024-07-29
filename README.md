# Value JSON Serialization Demo

This project demonstrates the serialization and deserialization of immutable value objects using the Gson library. The
implementation focuses on correctly handling immutable objects without requiring modifications or annotations in their
source code. It includes a custom adapter to manage the serialization and deserialization of a generic class, ensuring
that parameterised types are interpreted accurately, such as correctly distinguishing between integers and doubles
within these objects.

## Overview

The project showcases:

- Polymorphic serialization and deserialization using Gson.
- A custom type adapter to prevent integers from being interpreted as doubles when dealing with generic types.

## Serialization/Deserialization Example Use Case

To illustrate the functionality, the application creates several shape objects (a circle, cylinders with different content types, and a rectangle), serializes them into JSON format, and then deserializes them back into their original shape objects.

1. Original shapes:
   ```plaintext
   Original shapes:
   Circle[radius=4.2]
   Cylinder[radius=4.4, height=2.2, content=Some content]
   Cylinder[radius=12.3, height=3.4, content=42]
   Cylinder[radius=18.9, height=5.2, content=42.24]
   Rectangle[a=10, b=20]
   ```

2. Serialized shapes:
   ```plaintext
   Serialized shapes:
   {"shapeType":"com.github.ahauschulte.demo.valueserialization.Circle","radius":4.2}
   {"shapeType":"com.github.ahauschulte.demo.valueserialization.Cylinder","radius":4.4,"height":2.2,"paramType":"java.lang.String","content":"Some content"}
   {"shapeType":"com.github.ahauschulte.demo.valueserialization.Cylinder","radius":12.3,"height":3.4,"paramType":"java.lang.Integer","content":42}
   {"shapeType":"com.github.ahauschulte.demo.valueserialization.Cylinder","radius":18.9,"height":5.2,"paramType":"java.lang.Double","content":42.24}
   {"shapeType":"com.github.ahauschulte.demo.valueserialization.Rectangle","a":10,"b":20}
   ```

3. Deserialized shapes:
   ```plaintext
   Deserialized shapes:
   Circle[radius=4.2]
   Cylinder[radius=4.4, height=2.2, content=Some content]
   Cylinder[radius=12.3, height=3.4, content=42]
   Cylinder[radius=18.9, height=5.2, content=42.24]
   Rectangle[a=10, b=20]
   ```
   
## Project Structure

- **Main.java**: The entry point of the application. It creates shape instances, serializes them to JSON, and
  deserializes them back to shape objects.
- **Shape.java**: A sealed interface that all shape types (Circle, Cylinder, Rectangle) implement.
- **Circle.java**: A record representing a circle with a radius.
- **Rectangle.java**: A record representing a rectangle with width and height.
- **Cylinder.java**: A record representing a cylinder with a radius, height, and generic content.
- **CylinderTypeAdapter.java**: A custom type adapter for serializing and deserializing Cylinder objects.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java 21 or higher
- Maven

### Building the Project

1. Clone the repository:
   ```sh
   git clone https://github.com/ahauschulte/value-json-serialization.git
   cd value-json-serialization
   ```

2. Build the project using Maven:
   ```sh
   mvn clean package
   ```

### Running the Application

Run the `Main` class to see the serialization and deserialization in action:

```sh
mvn exec:java -Dexec.mainClass="com.github.ahauschulte.demo.valueserialization.Main"
```

## Code Explanation

### Main.java

The `Main` class demonstrates the core functionality:

- **Shape Type Registration**: Registers the shape types for polymorphic serialization
  using `RuntimeTypeAdapterFactory`.
- **Gson Configuration**: Configures Gson with the custom `CylinderTypeAdapter` and the `RuntimeTypeAdapterFactory`.
- **Shape Creation**: Creates instances of Circle, Cylinder (with string, integer, and double content), and Rectangle.
- **Serialization**: Serializes the shape objects to JSON.
- **Deserialization**: Deserializes the JSON strings back to shape objects.

### CylinderTypeAdapter.java

A custom type adapter to handle the serialization and deserialization of `Cylinder` objects with generic content:

- **Serialization**: Adds the type of the content along with the content itself to the JSON representation.
- **Deserialization**: Uses the content type to correctly deserialize the content element, preventing type mismatches.

## AI Tools Used

This project utilises AI tools, specifically ChatGPT by OpenAI, to assist with documentation and provide guidance on the
effective use of the Gson library. All AI-generated content has been reviewed and validated by human contributors to
ensure accuracy and quality.