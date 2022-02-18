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

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumtype.Gender;

/**
 * The unit test class for the class {@link JsonFileToObjectExtensions}
 */
public class JsonFileToObjectExtensionsTest
{

	File jsonDir;
	File jsonFile;
	File jsonListFile;

	@BeforeMethod
	protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "person.json");
		jsonListFile = new File(jsonDir, "employees.json");
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions#toObject(File, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectFileClassOfT() throws IOException
	{
		Employee actual;
		Employee expected;
		actual = JsonFileToObjectExtensions.toObject(jsonFile, Employee.class);
		expected = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.nickname("beast").married(true).about("Ha ha ha...").build()).id("23").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions#toObjectList(File, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectList() throws IOException
	{
		List<Employee> actual;
		List<Employee> expected;

		actual = JsonFileToObjectExtensions.toObjectList(jsonListFile, Employee.class);
		expected = ListFactory.newArrayList();

		expected
			.add(Employee
				.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
					.nickname("beast").married(true).about("Ha ha ha...").build())
				.id("23").build());

		expected
			.add(Employee
				.builder().person(Person.builder().gender(Gender.MALE).name("Andreas")
					.nickname("cute").married(false).about("fine person").build())
				.id("24").build());

		expected
			.add(Employee
				.builder().person(Person.builder().gender(Gender.FEMALE).name("Tatjana")
					.nickname("beautiful").married(false).about("Im hot").build())
				.id("25").build());

		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JsonFileToObjectExtensions.class);
	}

}
