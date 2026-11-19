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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

/**
 * The class {@code InterfaceDeserializer} is a custom {@link JsonDeserializer} that handles the
 * deserialization of interface types by extracting the actual implementation class from the JSON
 * data. The class named in the JSON is loaded without being initialized, and it is used only if it
 * is assignable to the type that is read; with the constructor that takes a set of allowed classes
 * only the classes of that set are used at all, and a name outside the set is refused without
 * loading a class
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
	 * The allowed classes by their names, or {@code null} if every class that is assignable to the
	 * type that is read may be used
	 */
	private final Map<String, Class<? extends T>> allowedClasses;

	/**
	 * Creates a new {@link InterfaceDeserializer} that uses every class that the JSON names and
	 * that is assignable to the type that is read
	 *
	 * @deprecated since 4.2, for removal: it lets the JSON choose among all classes of the
	 *             classpath that implement the type, use {@link #InterfaceDeserializer(Set)} with
	 *             the allowed classes instead
	 */
	@Deprecated(since = "4.2", forRemoval = true)
	public InterfaceDeserializer()
	{
		this.allowedClasses = null;
	}

	/**
	 * Creates a new {@link InterfaceDeserializer} that uses only the given classes: a class name in
	 * the JSON that is not the name of one of them is refused with a {@link JsonParseException}
	 * without loading a class
	 *
	 * @param allowedClasses
	 *            the classes that the JSON may name
	 * @throws NullPointerException
	 *             if the given set or one of its elements is {@code null}
	 */
	public InterfaceDeserializer(final Set<Class<? extends T>> allowedClasses)
	{
		Objects.requireNonNull(allowedClasses, "allowedClasses");
		final Map<String, Class<? extends T>> byName = new LinkedHashMap<>();
		for (final Class<? extends T> allowedClass : allowedClasses)
		{
			Objects.requireNonNull(allowedClass, "allowedClasses must not contain null");
			byName.put(allowedClass.getName(), allowedClass);
		}
		this.allowedClasses = Collections.unmodifiableMap(byName);
	}

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
		final Type actualType = typeForName(typeName, interfaceType);
		return context.deserialize(data, actualType);
	}

	/**
	 * Retrieves the class {@link Type} for the given type name in the JSON data; the class is
	 * loaded without being initialized, with the class loader of this class, and it is returned
	 * only if it is assignable to the raw type of the given target type
	 *
	 * @param jsonElement
	 *            the JSON element containing the type name
	 * @param targetType
	 *            the type that is read
	 * @return the {@link Type} corresponding to the type name
	 * @throws JsonParseException
	 *             if the type name is not a string, the class is not allowed, cannot be found or is
	 *             not assignable to the target type
	 */
	private Type typeForName(final JsonElement jsonElement, final Type targetType)
	{
		if (!jsonElement.isJsonPrimitive() || !((JsonPrimitive)jsonElement).isString())
		{
			throw new JsonParseException("the '" + PROPERTY_TYPE + "' member must be a string");
		}
		final String className = jsonElement.getAsString();
		final Class<?> targetRawType = TypeToken.get(targetType).getRawType();
		final Class<?> actualClass;
		if (allowedClasses != null)
		{
			actualClass = allowedClasses.get(className);
			if (actualClass == null)
			{
				throw new JsonParseException("the class '" + className + "' is not allowed");
			}
		}
		else
		{
			try
			{
				actualClass = Class.forName(className, false,
					InterfaceDeserializer.class.getClassLoader());
			}
			catch (ClassNotFoundException | LinkageError e)
			{
				throw new JsonParseException(e);
			}
		}
		if (!targetRawType.isAssignableFrom(actualClass))
		{
			throw new JsonParseException("the class '" + className + "' is not assignable to '"
				+ targetRawType.getName() + "'");
		}
		return actualClass;
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
