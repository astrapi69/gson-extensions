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

import java.lang.annotation.Annotation;
import java.util.Objects;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * The class {@code GenericAnnotationClassExclusionStrategy} provides a custom
 * {@link ExclusionStrategy} that excludes fields from Gson serialization or deserialization based
 * on the presence of a specific annotation
 *
 * @param <T>
 *            the type of the annotation
 */
public class GenericAnnotationClassExclusionStrategy<T extends Annotation>
	implements
		ExclusionStrategy
{
	private final Class<T> annotationClass;

	/**
	 * Instantiates a new {@code GenericAnnotationClassExclusionStrategy} with the provided
	 * annotation class
	 *
	 * @param annotationClass
	 *            the class of the annotation that marks fields to be excluded
	 * @throws NullPointerException
	 *             if {@code annotationClass} is null
	 */
	public GenericAnnotationClassExclusionStrategy(Class<T> annotationClass)
	{
		Objects.nonNull(annotationClass);
		this.annotationClass = annotationClass;
	}

	/**
	 * {@inheritDoc} This implementation always returns false, indicating that no classes should be
	 * skipped
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

	/**
	 * {@inheritDoc} This implementation skips fields that are annotated with the specified
	 * annotation
	 *
	 * @param field
	 *            the {@link FieldAttributes} object representing the field
	 * @return true if the field is annotated with the specified annotation, otherwise false
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes field)
	{
		return field.getAnnotation(this.annotationClass) != null;
	}
}
