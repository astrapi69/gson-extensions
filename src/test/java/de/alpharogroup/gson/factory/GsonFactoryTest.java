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