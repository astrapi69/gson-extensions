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
package de.alpharogroup.gson.type.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.gson.JsonFileToObjectExtensions;
import de.alpharogroup.gson.Signin;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

/**
 * The unit test class for the class {@link BigDecimalMoneyScaledAdapter}
 */
public class BigDecimalMoneyScaledAdapterTest
{

	File jsonDir;

	File jsonFile;

	Gson gson;

	@BeforeMethod protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "signin.json");

		gson = new GsonBuilder()
			.registerTypeAdapter(BigDecimal.class, new BigDecimalMoneyScaledAdapter()).create();
	}

	/**
	 * Test method for {@link BigDecimalMoneyScaledAdapter#write(JsonWriter, BigDecimal)}
	 *
	 * @throws IOException Signals that an I/O exception has occurred
	 */
	@Test public void testWrite() throws IOException
	{
		String actual;
		String expected;
		Signin signin;

		signin = JsonFileToObjectExtensions.toObject(jsonFile, Signin.class);
		actual = gson.toJson(signin);

		expected = "{\"password\":\"bar\",\"username\":\"foo\",\"points\":111.00}";
		assertEquals(actual, expected);

		signin.setPoints(null);
		actual = gson.toJson(signin);

		expected = "{\"password\":\"bar\",\"username\":\"foo\"}";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link BigDecimalMoneyScaledAdapter#read(JsonReader)}
	 */
	@Test public void testRead()
	{
		Signin actual;
		Signin expected;
		String jsonString;

		jsonString = "{\"password\":\"bar\",\"username\":\"foo\",\"points\":111}";
		actual = gson.fromJson(jsonString, Signin.class);
		expected = Signin.builder().password("bar").username("foo").points(new BigDecimal("111.00"))
			.build();
		assertEquals(actual, expected);

		jsonString = "{\"password\":\"bar\",\"username\":\"foo\",\"points\":null}";
		actual = gson.fromJson(jsonString, Signin.class);
		expected = Signin.builder().password("bar").username("foo").points(null).build();
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link BigDecimalMoneyScaledAdapter}
	 */
	@Test public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(BigDecimalMoneyScaledAdapter.class);
	}
}
