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

import com.google.gson.stream.JsonReader;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.test.objects.Employee;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collection;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * The unit test class for the class {@link TypeFactory}
 */
public class TypeFactoryTest
{

	File jsonDir;
	File jsonFile;
	File jsonListFile;

	@BeforeMethod protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "person.json");
		jsonListFile = new File(jsonDir, "employees.json");
	}

	/**
	 * Test method for {@link TypeFactory#newCollectionTypeToken(Class)}
	 */
	@Test public void testNewCollectionTypeToken() throws FileNotFoundException
	{
		Collection<Employee> actual;
		Type type = TypeFactory.newCollectionTypeToken(Employee.class);
		JsonReader reader = new JsonReader(new FileReader(jsonListFile));
		actual = GsonFactory.newGson().fromJson(reader, type);
		assertNotNull(actual);
		assertTrue(actual.size() == 3);
	}

	/**
	 * Test method for {@link TypeFactory}
	 */
	@Test public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(TypeFactory.class);
	}

}