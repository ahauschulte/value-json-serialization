package com.github.ahauschulte.demo.valueserialization;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * A custom type adapter for serializing and deserializing Cylinder objects with generic content.
 * Its main purpose is to prevent integers from being interpreted as doubles when used as a type
 * argument to parameterize Cylinder. This issue specifically arises during the deserialization
 * of JSON representations using Gson.
 */
public class CylinderTypeAdapter implements JsonSerializer<Cylinder<?>>, JsonDeserializer<Cylinder<?>> {

    private static final String RADIUS_PROPERTY_NAME = "radius";
    private static final String HEIGHT_PROPERTY_NAME = "height";
    private static final String PARAM_TYPE_PROPERTY_NAME = "paramType";
    private static final String CONTENT_PROPERTY_NAME = "content";

    @Override
    public JsonElement serialize(final Cylinder<?> src,
                                 final Type typeOfSrc,
                                 final JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(RADIUS_PROPERTY_NAME, src.radius());
        jsonObject.addProperty(HEIGHT_PROPERTY_NAME, src.height());
        if (src.content() != null) {

            // Serialize the type of the content along with the content itself
            jsonObject.addProperty(PARAM_TYPE_PROPERTY_NAME, src.content().getClass().getName());
            jsonObject.add(CONTENT_PROPERTY_NAME, context.serialize(src.content()));
        }
        return jsonObject;
    }

    @Override
    public Cylinder<?> deserialize(final JsonElement json,
                                   final Type typeOfT,
                                   final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final double radius = jsonObject.get(RADIUS_PROPERTY_NAME).getAsDouble();
        final double height = jsonObject.get(HEIGHT_PROPERTY_NAME).getAsDouble();
        final JsonElement contentElement = jsonObject.get(CONTENT_PROPERTY_NAME);
        final String paramType = jsonObject.get(PARAM_TYPE_PROPERTY_NAME).getAsString();

        try {

            // Deserialize the content based on its type
            final Class<?> contentClass = Class.forName(paramType);
            final Object content = context.deserialize(contentElement, contentClass);
            return new Cylinder<>(radius, height, content);
        } catch (final ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
