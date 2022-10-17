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
package io.github.astrapi69.gson.strategy;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.github.astrapi69.collection.set.SetFactory;
import io.github.astrapi69.gson.ObjectToJsonExtensions;
import io.github.astrapi69.gson.factory.GsonFactory;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumtype.Gender;

public class GenericClassFieldsExclusionStrategyTest
{

	@Test
	public void testGenericClassFieldsExclusionStrategy()
	{

		String expected;
		String actual;
		Person person;
		Gson gson;

		person = Person.builder().about("will be excluded").gender(Gender.FEMALE).married(false)
			.name("Afrodite").nickname("gorgeous will be excluded").build();

		GenericClassFieldsExclusionStrategy exclusionStrategy = new GenericClassFieldsExclusionStrategy<>(
			Person.class, SetFactory.newHashSet("about", "nickname"));
		gson = GsonFactory.newGsonBuilder(exclusionStrategy);
		actual = ObjectToJsonExtensions.toJson(person, gson);
		expected = "{\"gender\":\"FEMALE\",\"married\":false,\"name\":\"Afrodite\"}";
		assertEquals(actual, expected);
	}
}
