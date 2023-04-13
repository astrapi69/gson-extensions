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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Properties;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class PropertiesSerializer implements JsonSerializer<Properties>
{

	public static JsonObject newFromPropertyKey(final JsonObject jsonObject, final String key,
		final String value) throws IOException
	{
		if (!key.contains("."))
		{
			jsonObject.addProperty(key, value);
			return jsonObject;
		}

		final String keyPrefix = getKeyPrefix(key);
		if (keyPrefix != null)
		{
			int keyPrefixLength = keyPrefix.length() + 1;
			final String keySuffix = key.substring(keyPrefixLength, key.length());
			final JsonObject childJson = getJsonObject(jsonObject, keyPrefix);

			jsonObject.add(keyPrefix, newFromPropertyKey(childJson, keySuffix, value));
		}
		return jsonObject;
	}

	private static String getKeyPrefix(final String fullKey)
	{
		final String[] keyArray = fullKey.split("\\.");
		return (keyArray.length != 0) ? keyArray[0] : fullKey;
	}

	private static JsonObject getJsonObject(final JsonObject parent, final String key)
	{
		if (parent == null)
		{
			return null;
		}

		if (parent.get(key) != null && !(parent.get(key) instanceof JsonObject))
		{
			throw new IllegalArgumentException("key:" + key + "\n" + "parent: " + parent + "\n"
				+ "Given key can't be at the same time json object and property or array");
		}

		if (parent.getAsJsonObject(key) != null)
		{
			return parent.getAsJsonObject(key);
		}
		else
		{
			return new JsonObject();
		}
	}

	@Override
	public JsonElement serialize(final Properties properties, final Type typeOfSrc,
		final JsonSerializationContext context)
	{
		final JsonObject resultJson = new JsonObject();
		Set<Object> keySet = properties.keySet();
		for (final Object key : keySet)
		{
			String propertiesKey = (String)key;
			try
			{
				newFromPropertyKey(resultJson, propertiesKey,
					properties.getProperty(propertiesKey));
			}
			catch (final IOException e)
			{
				System.out.println("Bundle map serialization exception: " + e);
			}
		}
		return resultJson;
	}

}