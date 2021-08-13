package io.github.astrapi69.gson.instance.creators;

import java.lang.reflect.Type;
import java.security.PrivateKey;

public class PrivateKeyBoxInstanceCreator extends GenericInstanceCreator<PrivateKeyBox, PrivateKey> {


    public PrivateKeyBoxInstanceCreator(PrivateKey value) {
        super(value);
    }

    @Override
    public PrivateKeyBox createInstance(Type type) {
        return new PrivateKeyBox(value);
    }
}
