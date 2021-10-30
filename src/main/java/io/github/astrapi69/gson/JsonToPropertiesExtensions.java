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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * The class {@link JsonToPropertiesExtensions} converts json strings or json objects to java
 * properties object
 */
public class JsonToPropertiesExtensions
{

	/**
	 * Transforms the given json file into a java properties object
	 *
	 * @param jsonFile
	 *            the json file
	 * @return the generated java properties object
	 */
	public static Properties toProperties(final File jsonFile) throws FileNotFoundException
	{
		Objects.requireNonNull(jsonFile);
		Properties properties = new Properties();
		JsonObject root = JsonParser.parseReader(new FileReader(jsonFile)).getAsJsonObject();
		addPropertiesFromJsonObject(root, null, properties);
		return properties;
	}

	/**
	 * Transforms the given json string into a java properties object
	 *
	 * @param jsonString
	 *            the json string
	 * @return the generated java properties object
	 */
	public static Properties toProperties(final String jsonString)
	{
		Objects.requireNonNull(jsonString);
		Properties properties = new Properties();
		JsonObject root = JsonParser.parseString(jsonString).getAsJsonObject();
		addPropertiesFromJsonObject(root, null, properties);
		return properties;
	}

	private static void addPropertiesFromJsonObject(JsonObject jsonObject, String parentKey,
		Properties properties)
	{
		Set<String> jsonKeys = jsonObject.keySet();
		for (String key : jsonKeys)
		{
			Object val = jsonObject.get(key);
			String fullKey = parentKey == null || parentKey.length() == 0
				? key
				: parentKey + "." + key;
			if (val instanceof JsonArray)
			{
				JsonArray array = (JsonArray)val;
				addPropertiesFromJsonArray(array, fullKey, properties);
			}
			else if (val instanceof JsonObject)
			{
				JsonObject jsonOb = (JsonObject)val;
				addPropertiesFromJsonObject(jsonOb, fullKey, properties);
			}
			else
			{
				properties.put(fullKey, ((JsonElement)val).getAsString());
			}
		}
	}

	private static void addPropertiesFromJsonArray(JsonArray array, String parentKey,
		Properties properties)
	{
		if (array.size() == 0)
		{
			properties.put(parentKey, "");
		}
		else
		{
			for (int i = 0; i < array.size(); i++)
			{
				Object jsonElement = array.get(i);
				if (jsonElement instanceof JsonObject)
				{
					JsonObject jsonObject = (JsonObject)jsonElement;
					addPropertiesFromJsonObject(jsonObject, parentKey, properties);
				}
			}
		}
	}
}
