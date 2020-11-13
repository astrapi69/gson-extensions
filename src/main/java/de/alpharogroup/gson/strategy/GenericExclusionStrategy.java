package de.alpharogroup.gson.strategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.lang.annotation.Annotation;
import java.util.Objects;

public class GenericExclusionStrategy<T extends Annotation> implements ExclusionStrategy
{
	private final Class<T> annotation;

	public GenericExclusionStrategy(Class<T> annotation)
	{
		Objects.nonNull(annotation);
		this.annotation = annotation;
	}

	@Override public boolean shouldSkipClass(Class<?> clazz)
	{
		return false;
	}

	@Override public boolean shouldSkipField(FieldAttributes field)
	{
		return field.getAnnotation(this.annotation) != null;
	}

}
