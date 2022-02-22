package io.github.astrapi69.gson.serializer;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
 
public class BooleanSerializer implements JsonSerializer<Boolean> {
 
  public JsonElement serialize(Boolean src, Type typeOfSrc,
    JsonSerializationContext context)
  {
    return src != null && src ? new JsonPrimitive(1) : new JsonPrimitive(0);
  }
}
