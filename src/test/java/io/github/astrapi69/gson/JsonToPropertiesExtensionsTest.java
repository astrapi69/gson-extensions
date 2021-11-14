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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.collections.properties.SortedProperties;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;

/**
 * The unit test class for the class {@link JsonToPropertiesExtensions}
 */
public class JsonToPropertiesExtensionsTest
{

	File jsonDir;
	File jsonFile;
	File jsonLongFile;
	String jsonString;

	@BeforeMethod
	protected void setUp() throws IOException
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "en.json");
		jsonLongFile = new File(jsonDir, "en-long.json");
		jsonString = ReadFileExtensions.readFromFile(jsonFile);
	}

	/**
	 * Test method for {@link JsonToPropertiesExtensions#toProperties(File)}
	 *
	 * @throws FileNotFoundException
	 *             is thrown if the json file is not found
	 */
	@Test
	public void testToPropertiesFromJsonFile() throws FileNotFoundException
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
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("foo.title", "Translation foo");
		expected.put("foo.menu.new", "Translation new");
		expected.put("myapp.menu.popup.copy", "Copy");
		expected.put("myapp.title", "Translation app");
		expected.put("foo.menu.edit", "Translation edit");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		expected.put("foo.text", "Translation foo for test with ngx-translate");
		expected.put("foo.menu.popup.copy", "Copy");
		expected.put("myapp.menu.new", "Translation new");
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
	}

}