package de.alpharogroup.gson.factory;

import com.google.gson.stream.JsonReader;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.test.objects.Employee;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collection;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * The unit test class for the class {@link TypeFactory}
 */
public class TypeFactoryTest
{

	File jsonDir;
	File jsonFile;
	File jsonListFile;

	@BeforeMethod protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "person.json");
		jsonListFile = new File(jsonDir, "employees.json");
	}

	/**
	 * Test method for {@link TypeFactory#newCollectionTypeToken(Class)}
	 */
	@Test public void testNewCollectionTypeToken() throws FileNotFoundException
	{
		Collection<Employee> actual;
		Type type = TypeFactory.newCollectionTypeToken(Employee.class);
		JsonReader reader = new JsonReader(new FileReader(jsonListFile));
		actual = GsonFactory.newGson().fromJson(reader, type);
		assertNotNull(actual);
		assertTrue(actual.size() == 3);
	}
}