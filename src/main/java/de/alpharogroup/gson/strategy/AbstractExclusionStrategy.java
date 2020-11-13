package de.alpharogroup.gson.strategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.lang.annotation.Annotation;

public abstract class AbstractExclusionStrategy implements ExclusionStrategy
{

	@Override public boolean shouldSkipClass(Class<?> clazz)
	{
		return false;
	}

	@Override public boolean shouldSkipField(FieldAttributes field)
	{
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Annotation annotation = field.getAnnotation(annotationClass);
		boolean isAnnotated = annotation != null;
		return isAnnotated;
	}

	public abstract Class<? extends Annotation> getAnnotationClass();
}
