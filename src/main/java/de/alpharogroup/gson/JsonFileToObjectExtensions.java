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
package de.alpharogroup.gson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import de.alpharogroup.gson.factory.GsonFactory;
import de.alpharogroup.gson.factory.TypeFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * The class {@link JsonFileToObjectExtensions} converts json strings to java object and java
 * collections.
 */
public final class JsonFileToObjectExtensions
{
	private static final Gson DEFAULT_GSON = GsonFactory.newGson();

	/**
	 * Transforms the given json file into a java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonFile
	 *            the json file
	 * @param clazz
	 *            the clazz
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final File jsonFile, final Class<T> clazz) throws IOException
	{
		return DEFAULT_GSON.fromJson(new FileReader(jsonFile), clazz);
	}


	/**
	 * Transforms the given json file into a java List object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonListFile
	 *            the json file with an array
	 * @param clazz
	 *            the clazz
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> List<T> toObjectList(final File jsonListFile, final Class<T> clazz) throws IOException
	{
		Type listType = TypeFactory.newListTypeToken(clazz);

		JsonReader reader = new JsonReader(new FileReader(jsonListFile));
		return DEFAULT_GSON.fromJson(reader, listType);
	}

	private JsonFileToObjectExtensions()
	{
	}

}
