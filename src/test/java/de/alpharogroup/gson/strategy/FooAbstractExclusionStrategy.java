package de.alpharogroup.gson.strategy;

import java.lang.annotation.Annotation;

public class FooAbstractExclusionStrategy extends AbstractExclusionStrategy
{
	@Override public Class<? extends Annotation> getAnnotationClass()
	{
		return FooExclusion.class;
	}
}
