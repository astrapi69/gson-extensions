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

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import io.github.astrapi69.collection.properties.PropertiesExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.gson.PropertiesToJsonExtensions;

public class PropertiesSerializerTest
{

	File propertiesDir;
	File applicationPropertiesFile;

	@Test
	public void testSerialize() throws IOException
	{
		String expected;
		String actual;
		propertiesDir = new File(PathFinder.getSrcTestResourcesDir(), "properties");
		applicationPropertiesFile = new File(propertiesDir, "application.properties");
		Properties properties = PropertiesExtensions.loadProperties(applicationPropertiesFile);

		actual = PropertiesToJsonExtensions.toJson(properties);
		expected = "{\"app\":{\"public-paths\":\"[/v1/jwt/authenticate, /v1/jwt/ispublic, /v1/auth/signin]\",\"db-name\":\"bundles\",\"db-port\":\"5432\",\"db-host\":\"localhost\",\"db-url-prefix\":\"jdbc:postgresql://\",\"db-password\":\"postgres\",\"db-username\":\"postgres\",\"dir\":\"${user.home}/.${app.name}\",\"name\":\"xml-extensions\"},\"spring\":{\"jpa\":{\"properties\":{\"hibernate\":{\"jdbc\":{\"lob\":{\"non_contextual_creation\":\"true\"}}}}},\"datasource\":{\"password\":\"postgres\",\"username\":\"postgres\",\"url\":\"${app.db-url-prefix}${app.db-host}:${app.db-port}/${app.db-name}\"}},\"environment\":\"dev\",\"name\":\"yaml-to-prop-to-yaml\"}";
		assertEquals(expected, actual);
	}
}