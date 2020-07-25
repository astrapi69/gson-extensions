package de.alpharogroup.gson.factory;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * The unit test class for the class {@link GsonFactory}
 */
public class GsonFactoryTest
{

	/**
	 * Test method for {@link GsonFactory#newGson()}
	 */
	@Test public void testNewGson()
	{
		Gson gson = GsonFactory.newGson();
		assertNotNull(gson);
	}

	/**
	 * Test method for {@link GsonFactory#newGson(ExclusionStrategy, String)}
	 */
	@Test public void testTestNewGson()
	{
		Gson gson = GsonFactory.newGson(new ExclusionStrategy()
		{
			@Override public boolean shouldSkipField(FieldAttributes f)
			{
				return false;
			}

			@Override public boolean shouldSkipClass(Class<?> clazz)
			{
				return false;
			}
		}, "yyyy-MM-dd");
		assertNotNull(gson);
	}

}