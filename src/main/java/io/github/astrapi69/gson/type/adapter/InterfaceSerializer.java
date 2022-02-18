package io.github.astrapi69.gson.type.adapter;

import java.lang.reflect.Type;
import java.security.PrivateKey;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class InterfaceSerializer<T> implements JsonSerializer<T> {
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_DATA = "data";

    @Override
    public JsonElement serialize(T object, Type interfaceType, JsonSerializationContext context)
    {
        final JsonObject jsonObject = new JsonObject();
        String className = object.getClass().getName();
        jsonObject.addProperty(PROPERTY_TYPE, className);
        JsonElement serialize = context.serialize(object);
        jsonObject.add(PROPERTY_DATA, serialize);
        return jsonObject;
    }
}
