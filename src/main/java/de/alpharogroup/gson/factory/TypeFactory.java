package de.alpharogroup.gson.factory;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class TypeFactory
{
	public static<T> Type newListTypeToken(Class<T> listType){
		return newCollectionTypeToken(List.class, listType);
	}

	public static<T extends Collection, E> Type newCollectionTypeToken(Class<T> collectionClass, final Class<E> elementClass){
		return TypeToken.getParameterized(collectionClass, elementClass).getType();
	}

	public static<T> Type newCollectionTypeToken(Class<T> collectionType) {
		return newCollectionTypeToken(Collection.class, collectionType);
	}

	public static<M, K, V> Type newMapTypeToken(Class<M> mapClass, Class<K> keyType, Class<V> valueType){
		return TypeToken.getParameterized(mapClass, keyType, valueType).getType();
	}

	public static<K, V> Type newMapTypeToken(Class<K> keyType, Class<V> valueType){
		return newMapTypeToken(Map.class, keyType, valueType);
	}
}
