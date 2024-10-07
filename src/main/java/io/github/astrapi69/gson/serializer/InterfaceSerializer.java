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
package io.github.astrapi69.gson.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * The class {@code InterfaceSerializer} is a custom {@link JsonSerializer} that handles the
 * serialization of interface types by including the actual implementation class information in the
 * JSON structure
 *
 * @param <T>
 *            the type of the object to be serialized
 */
public class InterfaceSerializer<T> implements JsonSerializer<T>
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
	 * {@inheritDoc} This implementation serializes an object by adding the actual class name and
	 * the serialized data to the JSON structure
	 *
	 * @param src
	 *            the source object to serialize
	 * @param typeOfSrc
	 *            the actual type of the source object
	 * @param context
	 *            the serialization context
	 * @return the serialized {@link JsonElement} containing the type and data properties
	 */
	@Override
	public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context)
	{
		final JsonObject jsonObject = new JsonObject();
		String className = getClassName(src, typeOfSrc);
		jsonObject.addProperty(PROPERTY_TYPE, className);
		JsonElement serialize = context.serialize(src);
		jsonObject.add(PROPERTY_DATA, serialize);
		return jsonObject;
	}

	/**
	 * Retrieves the class name of the source object for inclusion in the JSON structure
	 *
	 * @param src
	 *            the source object to get the class name from
	 * @param typeOfSrc
	 *            the actual type of the source object
	 * @return the class name of the source object
	 */
	public String getClassName(T src, Type typeOfSrc)
	{
		return src.getClass().getName();
	}
}
