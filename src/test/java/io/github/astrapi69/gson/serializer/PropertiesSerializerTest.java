package io.github.astrapi69.gson.serializer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Properties;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.astrapi69.collection.properties.PropertiesExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.gson.factory.TypeFactory;

public class PropertiesSerializerTest
{

	File propertiesDir;
	File applicationPropertiesFile;

	@Test
	public void testSerialize() throws IOException
	{
		propertiesDir = new File(PathFinder.getSrcTestResourcesDir(), "properties");
		applicationPropertiesFile = new File(propertiesDir, "application.properties");
		Properties properties = PropertiesExtensions.loadProperties(applicationPropertiesFile);

		final Type propertiesType = TypeFactory.newType(Properties.class);
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(propertiesType, new PropertiesSerializer()).create();

		String json = gson.toJson(properties, propertiesType);
		System.out.println(json);
	}
}