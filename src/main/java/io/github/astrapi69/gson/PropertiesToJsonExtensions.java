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
package io.github.astrapi69.gson;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.astrapi69.gson.factory.GsonBuilderFactory;
import io.github.astrapi69.gson.factory.TypeFactory;
import io.github.astrapi69.gson.serializer.PropertiesSerializer;

/**
 * The class {@link PropertiesToJsonExtensions} converts java properties object to json strings or
 * json objects
 */
public class PropertiesToJsonExtensions
{

	/**
	 * Creates from the given {@link Properties} a json string
	 *
	 * @param properties
	 *            the properties to transform
	 * @return the json string
	 */
	public static String toJson(final Properties properties)
	{
		Objects.requireNonNull(properties);
		final Type propertiesType = TypeFactory.newType(Properties.class);
		GsonBuilder gsonBuilder = GsonBuilderFactory.newGsonBuilder();
		gsonBuilder.registerTypeAdapter(propertiesType, new PropertiesSerializer());
		Gson gson = gsonBuilder.create();
		return gson.toJson(properties, propertiesType);
	}
}
