package de.alpharogroup.gson.strategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class FooExclusionStrategy implements ExclusionStrategy
{

	@Override public boolean shouldSkipClass(Class<?> clazz)
	{
		return false;
	}

	@Override public boolean shouldSkipField(FieldAttributes field)
	{
		return field.getAnnotation(FooExclusion.class) != null;
	}
}
