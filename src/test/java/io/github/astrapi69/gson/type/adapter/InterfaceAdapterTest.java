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
package io.github.astrapi69.gson.type.adapter;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import io.github.astrapi69.gson.factory.GsonBuilderFactory;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.github.astrapi69.gson.factory.GsonFactory;
import io.github.astrapi69.test.object.type.hierarchy.animal.Animal;
import io.github.astrapi69.test.object.type.hierarchy.animal.Dog;
import io.github.astrapi69.test.object.type.hierarchy.animal.Forbidden;
import io.github.astrapi69.test.object.type.hierarchy.shape.Circle;
import io.github.astrapi69.test.object.type.hierarchy.shape.Rectangle;
import io.github.astrapi69.test.object.type.hierarchy.shape.Shape;
import io.github.astrapi69.test.object.type.hierarchy.shape.ShapeStore;

public class InterfaceAdapterTest
{

	@Test(expectedExceptions = { RuntimeException.class })
	public void testWithoutInterfaceAdapterWithAnimal()
	{
		Forbidden forbidden;
		String json;
		Gson gson;
		forbidden = Forbidden.builder().animal(Dog.builder().build()).build();
		gson = GsonFactory.newGson();
		json = gson.toJson(forbidden);
		gson.fromJson(json, Forbidden.class);
	}

	@Test(expectedExceptions = { RuntimeException.class })
	public void testWithoutInterfaceAdapterWithShape()
	{
		String json;
		Gson gson;
		ShapeStore shapeStore;

		shapeStore = ShapeStore.builder().shape(Circle.builder().radius(0.23D).build())
			.shape(Rectangle.builder().height(2.d).width(2.d).build()).build();
		gson = GsonFactory.newGson();
		json = gson.toJson(shapeStore);
		gson.fromJson(json, ShapeStore.class);
	}

	@Test
	public void testWithInterfaceAdapterWithAnimal()
	{
		Forbidden actual;
		Forbidden expected;
		String json;
		Gson gson;
		gson = GsonBuilderFactory.newGsonBuilder()
			.registerTypeAdapter(Animal.class, new InterfaceAdapter<Animal>()).create();

		expected = Forbidden.builder().animal(Dog.builder().build()).build();
		json = gson.toJson(expected);
		actual = gson.fromJson(json, Forbidden.class);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testWithInterfaceAdapterWithShape()
	{
		ShapeStore actual;
		ShapeStore expected;
		String json;
		Gson gson;

		expected = ShapeStore.builder().shape(Circle.builder().radius(0.23D).build())
			.shape(Rectangle.builder().height(2.d).width(2.d).build()).build();
		gson = GsonBuilderFactory.newGsonBuilder()
			.registerTypeAdapter(Shape.class, new InterfaceAdapter<Shape>()).create();
		json = gson.toJson(expected);
		gson.fromJson(json, ShapeStore.class);
		actual = gson.fromJson(json, ShapeStore.class);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
