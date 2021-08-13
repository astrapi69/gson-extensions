package io.github.astrapi69.gson.instance.creators;

import com.google.gson.InstanceCreator;

public abstract class GenericInstanceCreator<T, E> implements InstanceCreator<T> {

    E value;

    public GenericInstanceCreator(E value) {
        this.value = value;
    }
}
