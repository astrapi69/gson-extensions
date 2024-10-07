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

import java.util.Objects;
import java.util.Set;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * The class {@code GenericClassFieldsExclusionStrategy} provides a custom {@link ExclusionStrategy}
 * for excluding specific fields of a given class from Gson serialization or deserialization based
 * on field names
 *
 * @param <T>
 *            the type of the bean class
 */
public class GenericClassFieldsExclusionStrategy<T> implements ExclusionStrategy
{
	private final Class<T> beanClass;
	private final Set<String> excludeFieldNames;

	/**
	 * Instantiates a new {@code GenericClassFieldsExclusionStrategy} with the provided bean class
	 * and field names to exclude
	 *
	 * @param beanClass
	 *            the class of the bean containing the fields
	 * @param excludeFieldNames
	 *            the set of field names to exclude
	 * @throws NullPointerException
	 *             if {@code beanClass} or {@code excludeFieldNames} are null
	 */
	public GenericClassFieldsExclusionStrategy(final Class<T> beanClass,
		final Set<String> excludeFieldNames)
	{
		Objects.nonNull(beanClass);
		Objects.nonNull(excludeFieldNames);
		this.beanClass = beanClass;
		this.excludeFieldNames = excludeFieldNames;
	}

	/**
	 * {@inheritDoc} This implementation uses the {@link ExclusionStrategyExtensions#shouldSkip}
	 * method to determine if a field should be excluded
	 *
	 * @param field
	 *            the {@link FieldAttributes} object representing the field
	 * @return true if the field should be excluded, otherwise false
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes field)
	{
		return ExclusionStrategyExtensions.shouldSkip(field, beanClass, excludeFieldNames);
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
