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
package de.alpharogroup.gson.factory;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The factory class {@link TypeFactory} for creating {@link Type} objects
 */
public final class TypeFactory
{
	/**
	 * Factory method for create a new {@link Type} for convert {@link List} objects
	 * @param listType the list type
	 * @return the new {@link Type} object
	 */
	public static <T> Type newListTypeToken(Class<T> listType)
	{
		return newCollectionTypeToken(List.class, listType);
	}

	/**
	 * Factory method for create a new {@link Type} for convert json objects from all derived {@link Collection} objects
	 * @param collectionClass the collection class
	 * @param elementClass the element class
	 *
	 * @return the new {@link Type} object
	 */
	public static <T extends Collection, E> Type newCollectionTypeToken(Class<T> collectionClass,
		final Class<E> elementClass)
	{
		return TypeToken.getParameterized(collectionClass, elementClass).getType();
	}

	/**
	 * Factory method for create a new {@link Type} for convert all derived {@link Collection} objects
	 * @param collectionType the collection type
	 * @return the new {@link Type} object
	 */
	public static <T> Type newCollectionTypeToken(Class<T> collectionType)
	{
		return newCollectionTypeToken(Collection.class, collectionType);
	}

	/**
	 * Factory method for create a new {@link Type} for convert json objects from all derived {@link Map} objects
	 * @param mapClass the map class
	 * @param keyType the key type class
	 * @param valueType the value type class
	 *
	 * @return the new {@link Type} object
	 */
	public static <M, K, V> Type newMapTypeToken(Class<M> mapClass, Class<K> keyType,
		Class<V> valueType)
	{
		return TypeToken.getParameterized(mapClass, keyType, valueType).getType();
	}

	/**
	 * Factory method for create a new {@link Type} for convert json objects from all derived {@link Map} objects
	 * @param keyType the key type class
	 * @param valueType the value type class
	 *
	 * @return the new {@link Type} object
	 */
	public static <K, V> Type newMapTypeToken(Class<K> keyType, Class<V> valueType)
	{
		return newMapTypeToken(Map.class, keyType, valueType);
	}
}
