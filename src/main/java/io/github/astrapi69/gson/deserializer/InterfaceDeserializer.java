/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.gson.deserializer;

import java.lang.reflect.Type;

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
