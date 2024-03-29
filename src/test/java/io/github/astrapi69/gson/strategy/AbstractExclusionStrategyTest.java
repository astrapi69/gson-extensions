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

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;

import io.github.astrapi69.gson.ObjectToJsonExtensions;
import io.github.astrapi69.gson.factory.GsonFactory;
import io.github.astrapi69.test.object.enumeration.Gender;

public class AbstractExclusionStrategyTest
{

	@Test
	public void testAbstractExclusionStrategy()
	{
		String expected;
		String actual;
		ExclusionStrategy exclusionStrategy;
		TestPerson testPerson;
		Gson gson;
		testPerson = new TestPerson();
		testPerson.setAbout("a woman");
		testPerson.setGender(Gender.FEMALE);
		testPerson.setMarried(false);
		testPerson.setName("Afrodite");
		testPerson.setNickname("gorgeous");
		exclusionStrategy = new FooAbstractExclusionStrategy();
		gson = GsonFactory.newGsonBuilder(exclusionStrategy);

		actual = ObjectToJsonExtensions.toJson(testPerson, gson);
		expected = "{\"gender\":\"FEMALE\",\"married\":false,\"name\":\"Afrodite\"}";
		assertEquals(actual, expected);
	}

	@Test
	public void testFooExclusionStrategy()
	{
		String expected;
		String actual;
		ExclusionStrategy exclusionStrategy;
		TestPerson testPerson;
		Gson gson;
		testPerson = new TestPerson();
		testPerson.setAbout("a woman");
		testPerson.setGender(Gender.FEMALE);
		testPerson.setMarried(false);
		testPerson.setName("Afrodite");
		testPerson.setNickname("gorgeous");
		exclusionStrategy = new FooExclusionStrategy();
		gson = GsonFactory.newGsonBuilder(exclusionStrategy);
		actual = ObjectToJsonExtensions.toJson(testPerson, gson);
		expected = "{\"gender\":\"FEMALE\",\"married\":false,\"name\":\"Afrodite\"}";
		assertEquals(actual, expected);
	}

}
