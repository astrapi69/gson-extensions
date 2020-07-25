/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.gson;

import com.google.gson.Gson;
import de.alpharogroup.gson.factory.TypeFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The class {@link JsonStringToObjectExtensions} converts json strings to java object and java
 * collections.
 */
public final class JsonStringToObjectExtensions
{

	private static final Gson DEFAULT_GSON = new Gson();

	private JsonStringToObjectExtensions()
	{
	}

	/**
	 * Transforms the given json string into a java map object
	 *
	 * @param <K>
	 *            the generic type of keys
	 * @param <V>
	 *            the generic type of values
	 * @param jsonString
	 *            the json string
	 * @return the map
	 */
	public static <K, V> Map<K, V> toMapObject(final String jsonString, Class<K> keyType,
		Class<V> valueType)
	{
		Objects.requireNonNull(jsonString);
		Objects.requireNonNull(keyType);
		Objects.requireNonNull(valueType);
		return DEFAULT_GSON.fromJson(jsonString, TypeFactory.newMapTypeToken(keyType, valueType));
	}

	/**
	 * Transforms the given json string into a java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param clazz
	 *            the clazz of the generic type
	 * @return the object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T toObject(final String jsonString, final Class<T> clazz)
	{
		Objects.requireNonNull(jsonString);
		Objects.requireNonNull(clazz);
		return DEFAULT_GSON.fromJson(jsonString, clazz);
	}

	/**
	 * Transforms the given json string into a java object {@link Collection}
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param collectionClass
	 *            the collection class
	 * @param elementClass
	 *            the element class
	 * @return the list with the java objects.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> Collection<T> toObjectCollection(final String jsonString,
		@SuppressWarnings("rawtypes") final Class<? extends Collection> collectionClass,
		final Class<T> elementClass)
	{
		Objects.requireNonNull(jsonString);
		Objects.requireNonNull(collectionClass);
		Objects.requireNonNull(elementClass);
		return DEFAULT_GSON.fromJson(jsonString,
			TypeFactory.newCollectionTypeToken(collectionClass, elementClass));
	}

	/**
	 * Transforms the given json string into a java object {@link List}
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param elementClass
	 *            the element class of the generic type
	 * @return the list with the java objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> List<T> toObjectList(final String jsonString, final Class<T> elementClass)
		throws IOException
	{
		Objects.requireNonNull(jsonString);
		Objects.requireNonNull(elementClass);
		return (List<T>)toObjectCollection(jsonString, List.class, elementClass);
	}

}
