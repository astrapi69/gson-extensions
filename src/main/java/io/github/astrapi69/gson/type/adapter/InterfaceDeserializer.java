package io.github.astrapi69.gson.type.adapter;

import java.lang.reflect.Type;
import java.security.PrivateKey;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class InterfaceDeserializer<T> implements JsonDeserializer<T>
{

	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_DATA = "data";

	@Override
	public T deserialize(JsonElement jsonElement, Type interfaceType,
						 JsonDeserializationContext context) throws JsonParseException
	{
		final JsonObject jsonObject = (JsonObject)jsonElement;
		final JsonElement typeName = get(jsonObject, PROPERTY_TYPE);
		final JsonElement data = get(jsonObject, PROPERTY_DATA);
		final Type actualType = typeForName(typeName);
		return context.deserialize(data, actualType);
	}

	private Type typeForName(final JsonElement jsonElement)
	{
		try
		{
			return Class.forName(jsonElement.getAsString());
		}
		catch (ClassNotFoundException e)
		{
			throw new JsonParseException(e);
		}
	}

	private JsonElement get(final JsonObject jsonObject, String memberName)
	{
		final JsonElement jsonElement = jsonObject.get(memberName);
		if (jsonElement == null)
		{
			throw new JsonParseException("no '" + memberName
					+ "' member found in what was expected to be an interface wrapper");
		}
		return jsonElement;
	}
}
