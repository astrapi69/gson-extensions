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
package de.alpharogroup.gson;

import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.collections.set.SetFactory;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * The unit test class for the class {@link JsonStringToObjectExtensions}
 */
public class JsonStringToObjectExtensionsTest
{

	File jsonDir;

	File jsonFile;

	@BeforeMethod protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "signin.json");
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions#toMapObject(String, Class, Class)}
	 */
	@Test public void testToMapObjectStringTypeReference()
	{
		Map<Integer, Integer> actual;
		Map<Integer, Integer> expected;
		String jsonString;

		jsonString = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";

		// new scenario: try to convert json to integer map
		actual = JsonStringToObjectExtensions.toMapObject(jsonString, Integer.class, Integer.class);
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link JsonStringToObjectExtensions#toMapObject(String, Class, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test public void testToMapObjectStringTypeReferenceObjectMapper()
	{
		Map<Integer, Integer> actual;
		Map<Integer, Integer> expected;
		String jsonString;

		jsonString = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";

		// new scenario: try to convert json to integer map

		actual = JsonStringToObjectExtensions.toMapObject(jsonString, Integer.class, Integer.class);
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions#toObject(String, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test public void testToObject() throws IOException
	{
		Employee actual;
		Employee expected;
		String jsonString;

		expected = Employee.builder().person(
			Person.builder().gender(Gender.FEMALE).name("Anna").married(true).about("Ha ha ha...")
				.nickname("beast").build()).id("23").build();
		jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		actual = JsonStringToObjectExtensions.toObject(jsonString, Employee.class);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions#toObjectCollection(String, Class, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test public void testToObjectCollection() throws IOException
	{
		boolean actual;
		boolean expected;
		Set<Employee> jsonList;
		Set<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;
		String jsonString;

		jsonString = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}," + "{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"}," + "{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";

		jsonList = (Set<Employee>)JsonStringToObjectExtensions
			.toObjectCollection(jsonString, LinkedHashSet.class, Employee.class);
		firstExpected = Employee.builder().person(
			Person.builder().gender(Gender.FEMALE).name("Anna").nickname("beast").married(true)
				.about("Ha ha ha...").build()).id("23").build();
		secondExpected = Employee.builder().person(
			Person.builder().gender(Gender.MALE).name("Andreas").nickname("cute").married(false)
				.about("fine person").build()).id("24").build();
		thirdExpected = Employee.builder().person(
			Person.builder().gender(Gender.FEMALE).name("Tatjana").nickname("beautiful")
				.married(false).about("Im hot").build()).id("25").build();
		objectList = SetFactory.newLinkedHashSet(firstExpected, secondExpected, thirdExpected);

		actual = CollectionExtensions.isEqualCollection(jsonList, objectList);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions#toObject(String, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test public void testToObjectList() throws IOException
	{
		boolean actual;
		boolean expected;
		List<Employee> jsonList;
		List<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;
		String jsonString;

		jsonString = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}," + "{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"}," + "{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";

		jsonList = JsonStringToObjectExtensions.toObjectList(jsonString, Employee.class);
		firstExpected = Employee.builder().person(
			Person.builder().gender(Gender.FEMALE).name("Anna").nickname("beast").married(true)
				.about("Ha ha ha...").build()).id("23").build();
		secondExpected = Employee.builder().person(
			Person.builder().gender(Gender.MALE).name("Andreas").nickname("cute").married(false)
				.about("fine person").build()).id("24").build();
		thirdExpected = Employee.builder().person(
			Person.builder().gender(Gender.FEMALE).name("Tatjana").nickname("beautiful")
				.married(false).about("Im hot").build()).id("25").build();
		objectList = ListFactory.newArrayList(firstExpected, secondExpected, thirdExpected);

		actual = CollectionExtensions.isEqualCollection(jsonList, objectList);
		expected = true;
		assertEquals(expected, actual);

	}

	/**
	 * Test method for
	 * {@link JsonStringToObjectExtensions#toObject(String, Class)}
	 * This method shows also how to map a json string created from the org.json library.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test public void testToObjectWithModules() throws IOException
	{
		final Employee expected = Employee.builder().person(
			Person.builder().gender(Gender.FEMALE).name("Anna").married(true).about("Ha ha ha...")
				.nickname("beast").build()).id("23").build();
		final String jsonString = "{\"id\":\"23\",\"person\":{\"married\":true,\"nickname\":\"beast\",\"name\":\"Anna\",\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\"}}";
		final Employee actual = JsonStringToObjectExtensions.toObject(jsonString, Employee.class);
		assertEquals(expected, actual);
	}


	/**
	 * Test method for
	 * {@link JsonStringToObjectExtensions#toObject(String, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test public void testToObjectWithSignin() throws IOException
	{
		Signin actual;
		Signin expected;
		String jsonString;

		jsonString = "{\"username\":\"foo\",\"password\":\"bar\"}";
		actual = JsonStringToObjectExtensions.toObject(jsonString, Signin.class);
		expected = Signin.builder().username("foo").password("bar").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions}
	 */
	@Test public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JsonStringToObjectExtensions.class);
	}

}
