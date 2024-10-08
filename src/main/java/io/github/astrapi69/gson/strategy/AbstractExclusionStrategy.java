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

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * The abstract class {@code AbstractExclusionStrategy} provides a base implementation of the
 * {@link ExclusionStrategy} interface, allowing for custom field exclusion strategies based on
 * annotations.
 */
public abstract class AbstractExclusionStrategy implements ExclusionStrategy
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean shouldSkipClass(Class<?> clazz)
	{
		return false;
	}

	/**
	 * {@inheritDoc} This implementation checks if the field has the annotation class returned by
	 * {@link #getAnnotationClass()}
	 *
	 * @param field
	 *            the field attributes to check
	 * @return true if the field has the specified annotation, otherwise false
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes field)
	{
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Annotation annotation = field.getAnnotation(annotationClass);
		return annotation != null;
	}

	/**
	 * Gets the annotation class used to determine if a field should be skipped
	 *
	 * @return the annotation class to look for
	 */
	public abstract Class<? extends Annotation> getAnnotationClass();
}
