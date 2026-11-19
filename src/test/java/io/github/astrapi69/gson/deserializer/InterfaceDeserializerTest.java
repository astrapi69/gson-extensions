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
package io.github.astrapi69.gson.deserializer;

import java.lang.reflect.Type;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

/**
 * Test class for {@link InterfaceDeserializer}
 */
public class InterfaceDeserializerTest
{

	/** A type to be read through the deserializer */
	public interface Marker
	{
	}

	/** A class that implements {@link Marker} */
	public static class MarkerImpl implements Marker
	{
		/** A value */
		private String value;
	}

	/**
	 * The flag that the static initializer of {@link UnrelatedWithStaticInit} sets; it lives in the
	 * outer class, because reading a static field of a class would initialize that class
	 */
	private static volatile boolean unrelatedInitialized = false;

	/**
	 * A class that does not implement {@link Marker}; its static initializer sets a flag, so that a
	 * test can see whether the class was initialized
	 */
	public static class UnrelatedWithStaticInit
	{
		static
		{
			unrelatedInitialized = true;
		}
		/** A value */
		private String value;
	}

	/** The flag that the static initializer of {@link OtherMarkerImpl} sets */
	private static volatile boolean otherInitialized = false;

	/** A second class that implements {@link Marker}, not part of the allowlist of the tests */
	public static class OtherMarkerImpl implements Marker
	{
		static
		{
			otherInitialized = true;
		}
		/** A value */
		private String value;
	}

	@SuppressWarnings("removal")
	private static Gson newGson()
	{
		return new GsonBuilder().registerTypeAdapter(Marker.class, new InterfaceDeserializer<>())
			.create();
	}

	private static Gson newGson(Set<Class<? extends Marker>> allowedClasses)
	{
		return new GsonBuilder()
			.registerTypeAdapter(Marker.class, new InterfaceDeserializer<>(allowedClasses))
			.create();
	}

	private static Object read(Gson gson, String json)
	{
		return gson.fromJson(json, (Type)Marker.class);
	}

	private static String json(Class<?> type)
	{
		return "{\"" + InterfaceDeserializer.PROPERTY_TYPE + "\":\"" + type.getName() + "\",\""
			+ InterfaceDeserializer.PROPERTY_DATA + "\":{\"value\":\"x\"}}";
	}

	@Test
	public void testDeserialize()
	{
		Object actual = read(newGson(), json(MarkerImpl.class));
		Assert.assertTrue(actual instanceof MarkerImpl);
	}

	/**
	 * A class named in the JSON that does not implement the target type is refused, and it is not
	 * initialized
	 */
	@Test
	public void testDeserializeRefusesClassThatDoesNotImplementTheTargetType()
	{
		Assert.assertFalse(unrelatedInitialized);
		Assert.assertThrows(JsonParseException.class,
			() -> read(newGson(), json(UnrelatedWithStaticInit.class)));
		Assert.assertFalse(unrelatedInitialized);
	}

	@Test
	public void testDeserializeUnknownClass()
	{
		Assert.assertThrows(JsonParseException.class,
			() -> read(newGson(), "{\"type\":\"no.such.Clazz\",\"data\":{}}"));
	}

	@Test
	public void testDeserializeTypeMemberMissing()
	{
		Assert.assertThrows(JsonParseException.class, () -> read(newGson(), "{\"data\":{}}"));
	}

	@Test
	public void testDeserializeTypeMemberNull()
	{
		Assert.assertThrows(JsonParseException.class,
			() -> read(newGson(), "{\"type\":null,\"data\":{}}"));
	}

	@Test
	public void testDeserializeTypeMemberNotAString()
	{
		Assert.assertThrows(JsonParseException.class,
			() -> read(newGson(), "{\"type\":42,\"data\":{}}"));
		Assert.assertThrows(JsonParseException.class,
			() -> read(newGson(), "{\"type\":{\"a\":1},\"data\":{}}"));
	}

	@Test
	public void testDeserializeWithAllowlistAcceptsAllowedClass()
	{
		Object actual = read(newGson(Set.of(MarkerImpl.class)), json(MarkerImpl.class));
		Assert.assertTrue(actual instanceof MarkerImpl);
	}

	/**
	 * A class outside the allowlist is refused, although it implements the target type, and it is
	 * not initialized
	 */
	@Test
	public void testDeserializeWithAllowlistRefusesClassOutsideTheList()
	{
		Assert.assertFalse(otherInitialized);
		JsonParseException e = Assert.expectThrows(JsonParseException.class,
			() -> read(newGson(Set.of(MarkerImpl.class)), json(OtherMarkerImpl.class)));
		Assert.assertTrue(e.getMessage().contains("not allowed"));
		Assert.assertFalse(otherInitialized);
	}

	@Test
	public void testDeserializeWithAllowlistRefusesUnknownName()
	{
		Assert.assertThrows(JsonParseException.class, () -> read(newGson(Set.of(MarkerImpl.class)),
			"{\"type\":\"no.such.Clazz\",\"data\":{}}"));
	}

	@Test
	public void testConstructorWithNullSet()
	{
		Assert.assertThrows(NullPointerException.class,
			() -> new InterfaceDeserializer<Marker>(null));
	}
}
