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

/**
 * The class {@code InterfaceDeserializer} is a custom {@link JsonDeserializer} that handles the
 * deserialization of interface types by extracting the actual implementation class from the JSON
 * data
 *
 * @param <T>
 *            the type of the object to be deserialized
 */
public class InterfaceDeserializer<T> implements JsonDeserializer<T>
{
	/**
	 * The constant {@code PROPERTY_TYPE} represents the key used to store the type of an object in
	 * the serialized JSON structure
	 */
	public static final String PROPERTY_TYPE = "type";

	/**
	 * The constant {@code PROPERTY_DATA} represents the key used to store the data of an object in
	 * the serialized JSON structure
	 */
	public static final String PROPERTY_DATA = "data";


	/**
	 * {@inheritDoc} This implementation deserializes an object based on the provided interface
	 * type, determining the actual implementation class from the JSON structure
	 *
	 * @param jsonElement
	 *            the JSON element to deserialize
	 * @param interfaceType
	 *            the interface type to deserialize to
	 * @param context
	 *            the deserialization context
	 * @return the deserialized object of the actual implementation type
	 * @throws JsonParseException
	 *             if there is an error during deserialization
	 */
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

	/**
	 * Retrieves the class {@link Type} for the given type name in the JSON data
	 *
	 * @param jsonElement
	 *            the JSON element containing the type name
	 * @return the {@link Type} corresponding to the type name
	 * @throws JsonParseException
	 *             if the class cannot be found
	 */
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

	/**
	 * Retrieves a JSON element by member name from the given JSON object
	 *
	 * @param jsonObject
	 *            the JSON object to retrieve from
	 * @param memberName
	 *            the name of the member to retrieve
	 * @return the {@link JsonElement} associated with the member name
	 * @throws JsonParseException
	 *             if the member is not found
	 */
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
