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

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * The class {@code GenericMapClassFieldsExclusionStrategy} provides a custom
 * {@link ExclusionStrategy} that excludes fields from Gson serialization or deserialization based
 * on a map of class types and field names
 *
 * The map defines which classes and their corresponding field names should be excluded during
 * serialization or deserialization
 */
public class GenericMapClassFieldsExclusionStrategy implements ExclusionStrategy
{
	Map<Class<?>, Set<String>> excludeFieldsDefinition;

	/**
	 * Instantiates a new {@code GenericMapClassFieldsExclusionStrategy} with the provided map of
	 * class types and field names
	 *
	 * @param excludeFieldsDefinition
	 *            a map where the key is a class and the value is a set of field names to exclude
	 * @throws NullPointerException
	 *             if {@code excludeFieldsDefinition} is null
	 */
	public GenericMapClassFieldsExclusionStrategy(
		Map<Class<?>, Set<String>> excludeFieldsDefinition)
	{
		Objects.nonNull(excludeFieldsDefinition);
		this.excludeFieldsDefinition = excludeFieldsDefinition;
	}

	/**
	 * {@inheritDoc} This implementation iterates over the map to check if the given field belongs
	 * to a class and matches one of the field names to be excluded
	 *
	 * @param field
	 *            the {@link FieldAttributes} object representing the field
	 * @return true if the field should be excluded, otherwise false
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes field)
	{
		for (Map.Entry<Class<?>, Set<String>> entry : excludeFieldsDefinition.entrySet())
		{
			Class<?> beanClass = entry.getKey();
			if (beanClass == null)
			{
				continue;
			}
			Set<String> excludeFieldNames = entry.getValue();
			if (excludeFieldNames == null || excludeFieldNames.isEmpty())
			{
				continue;
			}
			if (ExclusionStrategyExtensions.shouldSkip(field, beanClass, excludeFieldNames))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc} This implementation does not exclude any classes, so it always returns false
	 *
	 * @param clazz
	 *            the class to check for exclusion
	 * @return false, indicating no class is excluded
	 */
	@Override
	public boolean shouldSkipClass(Class<?> clazz)
	{
		return false;
	}
}
