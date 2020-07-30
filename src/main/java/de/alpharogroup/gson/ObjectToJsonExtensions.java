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
import de.alpharogroup.gson.factory.GsonFactory;
import de.alpharogroup.gson.factory.TypeFactory;

import java.util.List;
import java.util.Objects;

/**
 * The class {@link ObjectToJsonExtensions} converts java objects to json string objects.
 */
public final class ObjectToJsonExtensions
{

	private ObjectToJsonExtensions()
	{
	}

	/**
	 * Creates from the given {@link List} a json string
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list to transform
	 * @return the json string
	 */
	public static <T> String toJson(final List<T> list)
	{
		Objects.requireNonNull(list);
		return toJson(list, GsonFactory.DEFAULT_GSON);
	}

	/**
	 * Creates from the given {@link List} a json string
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list to transform
	 * @param gson
	 *            the gson object
	 * @return the json string
	 */
	public static <T> String toJson(final List<T> list, final Gson gson)
	{
		Objects.requireNonNull(list);
		Objects.requireNonNull(gson);
		if (list.isEmpty())
		{
			return "";
		}
		return gson.toJson(list, TypeFactory.newListTypeToken(list.get(0).getClass()));
	}

	/**
	 * Creates a json {@link String} from the given argument object
	 *
	 * @param <T>
	 *            the generic type of the given argument object
	 * @param object
	 *            the object to transform
	 * @return the json string
	 */
	public static <T> String toJson(final T object)
	{
		Objects.requireNonNull(object);
		return toJson(object, GsonFactory.DEFAULT_GSON);
	}

	/**
	 * Creates a json {@link String} from the given argument object
	 *
	 * @param <T>
	 *            the generic type of the given argument object
	 * @param object
	 *            the object to transform
	 * @param gson
	 *            the gson object
	 * @return the json string
	 */
	public static <T> String toJson(final T object, final Gson gson)
	{
		Objects.requireNonNull(object);
		Objects.requireNonNull(gson);
		return gson.toJson(object, object.getClass());
	}

}
