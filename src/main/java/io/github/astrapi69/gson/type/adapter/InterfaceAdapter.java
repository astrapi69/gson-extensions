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
package io.github.astrapi69.gson.type.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import io.github.astrapi69.gson.deserializer.InterfaceDeserializer;
import io.github.astrapi69.gson.serializer.InterfaceSerializer;

/**
 * An adapter class for serializing and deserializing objects that implement a specific interface
 * using Gson. This class leverages separate {@link InterfaceSerializer} and
 * {@link InterfaceDeserializer} instances for handling serialization and deserialization tasks
 * respectively.
 *
 * @param <T>
 *            The type of the object to be serialized and deserialized. This type should be an
 *            interface.
 */
public final class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T>
{
	private final InterfaceSerializer<T> serializer = new InterfaceSerializer<>();
	private final InterfaceDeserializer<T> deserializer = new InterfaceDeserializer<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement serialize(T object, Type interfaceType, JsonSerializationContext context)
	{
		return serializer.serialize(object, interfaceType, context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T deserialize(JsonElement jsonElement, Type interfaceType,
		JsonDeserializationContext context) throws JsonParseException
	{
		return deserializer.deserialize(jsonElement, interfaceType, context);
	}

}
