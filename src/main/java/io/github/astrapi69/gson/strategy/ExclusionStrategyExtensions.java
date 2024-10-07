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

import java.util.Set;

import com.google.gson.FieldAttributes;

/**
 * The class {@code ExclusionStrategyExtensions} provides utility methods for determining whether
 * fields should be excluded from Gson serialization or deserialization based on field names and
 * their declaring class
 */
public final class ExclusionStrategyExtensions
{

	/**
	 * Private constructor to prevent instantiation
	 */
	private ExclusionStrategyExtensions()
	{
	}

	/**
	 * Determines if a field should be excluded from Gson serialization or deserialization based on
	 * the provided field attributes, the class to which the field belongs, and a set of field names
	 * to exclude
	 *
	 * @param <T>
	 *            the type of the bean class
	 * @param field
	 *            the {@link FieldAttributes} object representing the field
	 * @param beanClass
	 *            the class of the bean containing the field
	 * @param excludeFieldNames
	 *            the set of field names to exclude
	 * @return true if the field should be excluded, otherwise false
	 */
	public static <T> boolean shouldSkip(FieldAttributes field, Class<T> beanClass,
		Set<String> excludeFieldNames)
	{
		for (String excludeFieldName : excludeFieldNames)
		{
			String fieldName = field.getName();
			Class<?> declaringClass = field.getDeclaringClass();
			boolean sameClass = declaringClass.equals(beanClass);
			if (sameClass && fieldName.equals(excludeFieldName))
			{
				return true;
			}
		}
		return false;
	}
}
