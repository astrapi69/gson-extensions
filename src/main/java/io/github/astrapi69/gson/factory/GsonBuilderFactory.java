package io.github.astrapi69.gson.factory;

import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;

public class GsonBuilderFactory
{

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
	 * Factory method for create a new {@link GsonBuilder}
	 *
	 * @param exclusionStrategy
	 *            the exclusion strategy
	 * @param serialization
	 *            the serialization flag
	 *
	 * @return the new {@link GsonBuilder} object
	 */
	public static GsonBuilder newGsonBuilder(ExclusionStrategy exclusionStrategy,
		boolean serialization)
	{
		if (serialization)
		{
			return newGsonBuilder().addSerializationExclusionStrategy(exclusionStrategy);
		}
		return newGsonBuilder().addDeserializationExclusionStrategy(exclusionStrategy);
	}

	/**
	 * Factory method for create a new {@link GsonBuilder}
	 *
	 * @param exclusionStrategy
	 *            the exclusion strategy
	 * @param datePattern
	 *            the date pattern
	 * @return the new {@link GsonBuilder} object
	 */
	public static GsonBuilder newGsonBuilder(ExclusionStrategy exclusionStrategy,
		String datePattern)
	{
		return newGsonBuilder().addSerializationExclusionStrategy(exclusionStrategy)
			.setDateFormat(datePattern);
	}
}
