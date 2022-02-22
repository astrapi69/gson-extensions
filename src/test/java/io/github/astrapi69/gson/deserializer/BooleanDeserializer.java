package io.github.astrapi69.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class BooleanDeserializer implements JsonDeserializer<Boolean>
{

	@Override
	public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		throws JsonParseException
	{
		return ((JsonPrimitive)json).isBoolean()
			? json.getAsBoolean()
			: ((JsonPrimitive)json).isString()
				? json.getAsString().equals("true")
				: json.getAsInt() != 0;
	}
}
