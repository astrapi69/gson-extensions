package io.github.astrapi69.gson.serializer;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializer implements JsonSerializer<Date>
{
	@Override
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
	{
		return src != null ? new JsonPrimitive(src.getTime()) : null;
	}
}
