package de.alpharogroup.gson.strategy;

import com.google.gson.Gson;
import de.alpharogroup.gson.ObjectToJsonExtensions;
import de.alpharogroup.gson.factory.GsonFactory;
import de.alpharogroup.test.objects.enums.Gender;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GenericExclusionStrategyTest
{

	@Test public void testGenericExclusionStrategy()
	{
		String expected;
		String actual;
		GenericExclusionStrategy<FooExclusion> exclusionStrategy;
		exclusionStrategy = new GenericExclusionStrategy<FooExclusion>(FooExclusion.class);
		Gson gson = GsonFactory.newGsonBuilder(exclusionStrategy);
		TestPerson testPerson = new TestPerson();
		testPerson.setAbout("a woman");
		testPerson.setGender(Gender.FEMALE);
		testPerson.setMarried(false);
		testPerson.setName("Afrodite");
		testPerson.setNickname("gorgeous");
		actual = ObjectToJsonExtensions.toJson(testPerson, gson);
		expected = "{\"gender\":\"FEMALE\",\"married\":false,\"name\":\"Afrodite\"}";
		assertEquals(actual, expected);
	}
}
