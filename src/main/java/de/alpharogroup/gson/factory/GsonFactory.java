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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The factory class {@link GsonFactory} for creating {@link Gson} objects
 */
public class GsonFactory
{
	/**
	 * The default {@link Gson} object for transformations
	 */
	public static final Gson DEFAULT_GSON = GsonFactory.newGson();

	/**
	 * Factory method for create a new {@link GsonBuilder}
	 *
	 * @return the new {@link GsonBuilder} object
	 */
	public static GsonBuilder newGsonBuilder()
	{
		return new GsonBuilder();
	}

	/**
	 * Factory method for create a new {@link Gson}
	 *
	 * @return the new {@link Gson} object
	 */
	public static Gson newGson()
	{
		return newGsonBuilder().create();
	}

	/**
	 * Factory method for create a new {@link Gson}
	 *
	 * @param exclusionStrategy the exclusion strategy
	 * @param pattern           the date pattern
	 * @return the new {@link Gson} object
	 */
	public static Gson newGsonBuilder(ExclusionStrategy exclusionStrategy, String pattern)
	{
		return newGsonBuilder().addDeserializationExclusionStrategy(exclusionStrategy)
			.setDateFormat(pattern).create();
	}
}
