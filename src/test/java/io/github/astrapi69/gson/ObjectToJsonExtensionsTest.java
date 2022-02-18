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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.collections.map.MapFactory;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumtype.Gender;

/**
 * The unit test class for the class {@link ObjectToJsonExtensions}
 */
public class ObjectToJsonExtensionsTest
{

	public static <K> Map<K, Integer> newCounterMap(final Collection<K> elements)
	{
		Objects.requireNonNull(elements);
		Map<K, Integer> elementsCount = MapFactory.newHashMap();
		for (K element : elements)
		{
			if (elementsCount.containsKey(element))
			{
				elementsCount.merge(element, 1, Integer::sum);
				continue;
			}
			elementsCount.put(element, 0);
		}
		return elementsCount;
	}

	/**
	 * Test method for {@link ObjectToJsonExtensions#toJson(Object)}
	 */
	@Test
	public void testToJson()
	{
		String expected;
		String actual;
		final Employee employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").married(true).about("Ha ha ha...").nickname("beast").build()).id("23")
			.build();
		// new scenario: try to convert a Employee object to json
		expected = "{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}";
		actual = ObjectToJsonExtensions.toJson(employee);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		Map<Integer, Integer> numberCounterMap = newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		actual = ObjectToJsonExtensions.toJson(numberCounterMap);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectToJsonExtensions#toJson(Object)} with {@link Map}
	 */
	@Test
	public void testToJsonFromMap()
	{
		String expected;
		String actual;
		final Map<String, String> stringMap = new HashMap<>();
		stringMap.put("a", "ss");
		stringMap.put("b", "qq");

		expected = "{\"a\":\"ss\",\"b\":\"qq\"}";
		actual = ObjectToJsonExtensions.toJson(stringMap);
		assertEquals(actual, expected);


		final Employee employee1 = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").married(true).about("Ha ha ha...").nickname("beast").build()).id("23")
			.build();

		final Map<Integer, Employee> integerEmployeeMap = new HashMap<>();
		integerEmployeeMap.put(1, employee1);


		expected = "{\"1\":{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}}";
		actual = ObjectToJsonExtensions.toJson(integerEmployeeMap);
		assertEquals(actual, expected);

	}

	/**
	 * Test method for {@link ObjectToJsonExtensions#toJson(java.util.List)}
	 */
	@Test
	public void testToJsonList()
	{

		String expected;
		String actual;
		List<Employee> employees;

		employees = new ArrayList<>();
		employees
			.add(
				Employee
					.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
						.married(true).about("Ha ha ha...").nickname("beast").build())
					.id("23").build());
		employees
			.add(Employee
				.builder().person(Person.builder().gender(Gender.MALE).name("Andreas")
					.married(false).about("fine person").nickname("cute").build())
				.id("24").build());
		employees
			.add(Employee
				.builder().person(Person.builder().gender(Gender.FEMALE).name("Tatjana")
					.married(false).about("Im hot").nickname("beautiful").build())
				.id("25").build());
		actual = ObjectToJsonExtensions.toJson(employees);

		expected = "[{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}},{\"id\":\"24\",\"person\":{\"about\":\"fine person\",\"gender\":\"MALE\",\"married\":false,\"name\":\"Andreas\",\"nickname\":\"cute\"}},{\"id\":\"25\",\"person\":{\"about\":\"Im hot\",\"gender\":\"FEMALE\",\"married\":false,\"name\":\"Tatjana\",\"nickname\":\"beautiful\"}}]";
		assertEquals(expected, actual);

		employees = new ArrayList<>();
		actual = ObjectToJsonExtensions.toJson(employees);
		expected = "";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectToJsonExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ObjectToJsonExtensions.class);
	}

}
