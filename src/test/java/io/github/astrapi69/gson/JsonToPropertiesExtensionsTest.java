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

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.collection.properties.PropertiesExtensions;
import io.github.astrapi69.collection.properties.SortedProperties;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;

/**
 * The unit test class for the class {@link JsonToPropertiesExtensions}
 */
public class JsonToPropertiesExtensionsTest
{

	File jsonDir;
	File propertiesDir;
	File jsonFile;
	File jsonLongFile;
	File jsonListFile;
	File jsonApplicationFile;
	File applicationPropertiesFile;
	String jsonString;
	String jsonLongString;

	@BeforeMethod
	protected void setUp() throws IOException
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		propertiesDir = new File(PathFinder.getSrcTestResourcesDir(), "properties");
		jsonFile = new File(jsonDir, "en.json");
		jsonLongFile = new File(jsonDir, "en-long.json");
		jsonString = ReadFileExtensions.fromFile(jsonFile);
		jsonLongString = ReadFileExtensions.fromFile(jsonLongFile);
		jsonListFile = new File(jsonDir, "employees.json");
		jsonApplicationFile = new File(jsonDir, "application.json");
		applicationPropertiesFile = new File(propertiesDir, "application.properties");
	}

	/**
	 * Test method for {@link JsonToPropertiesExtensions#toProperties(File)}
	 *
	 * @throws FileNotFoundException
	 *             is thrown if the json file is not found
	 */
	@Test
	public void testToPropertiesFromJsonFile() throws IOException
	{
		SortedProperties actual;
		SortedProperties expected;
		// new scenario...
		expected = new SortedProperties();
		expected.put("myapp.menu.new", "Translation new");
		expected.put("myapp.title", "Translation app");
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		actual = JsonToPropertiesExtensions.toProperties(jsonFile);
		assertEquals(actual, expected);

		actual = JsonToPropertiesExtensions.toProperties(jsonLongFile);

		expected = new SortedProperties();
		expected.put("myapp.title", "Translation app");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		expected.put("myapp.menu.new", "Translation new");
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("myapp.menu.popup.copy", "Copy");
		expected.put("foo.title", "Translation foo");
		expected.put("foo.text", "Translation foo for test with ngx-translate");
		expected.put("foo.menu.new", "Translation new");
		expected.put("foo.menu.edit", "Translation edit");
		expected.put("foo.menu.popup.copy", "Copy");
		assertEquals(actual, expected);

	}


	/**
	 * Test method for {@link JsonToPropertiesExtensions#toProperties(File)}
	 *
	 * @throws FileNotFoundException
	 *             is thrown if the json file is not found
	 */
	@Test
	public void testToPropertiesFromJsonFileWithArray() throws IOException
	{
		Properties actual;
		Properties expected;
		// new scenario...
		actual = JsonToPropertiesExtensions.toProperties(jsonApplicationFile);
		expected = PropertiesExtensions.loadProperties(applicationPropertiesFile);

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link JsonToPropertiesExtensions#toProperties(String)}
	 */
	@Test
	public void testToPropertiesFromJsonString()
	{
		SortedProperties actual;
		SortedProperties expected;
		// new scenario...
		expected = new SortedProperties();
		expected.put("myapp.menu.new", "Translation new");
		expected.put("myapp.title", "Translation app");
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		actual = JsonToPropertiesExtensions.toProperties(jsonString);
		assertEquals(actual, expected);
		// new scenario...
		expected = new SortedProperties();
		expected.put("myapp.title", "Translation app");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		expected.put("myapp.menu.new", "Translation new");
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("myapp.menu.popup.copy", "Copy");
		expected.put("foo.title", "Translation foo");
		expected.put("foo.text", "Translation foo for test with ngx-translate");
		expected.put("foo.menu.new", "Translation new");
		expected.put("foo.menu.edit", "Translation edit");
		expected.put("foo.menu.popup.copy", "Copy");
		actual = JsonToPropertiesExtensions.toProperties(jsonLongString);
		assertEquals(actual, expected);
	}

}
