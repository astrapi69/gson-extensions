package io.github.astrapi69.gson.instance.creators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.astrapi69.crypto.algorithm.KeyPairGeneratorAlgorithm;
import io.github.astrapi69.crypto.factories.KeyPairFactory;
import io.github.astrapi69.crypto.key.KeySize;
import org.testng.annotations.Test;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.testng.Assert.*;

public class GenericInstanceCreatorTest {

    @Test
    void testGenericInstanceCreator() throws NoSuchAlgorithmException, NoSuchProviderException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        KeyPair keyPair = KeyPairFactory.newKeyPair(KeyPairGeneratorAlgorithm.DIFFIE_HELLMAN,
                KeySize.KEYSIZE_2048);
        PrivateKeyBox box = new PrivateKeyBox(keyPair.getPrivate());
        gsonBuilder.registerTypeAdapter(
                PrivateKeyBox.class,
                new PrivateKeyBoxInstanceCreator(keyPair.getPrivate())
        );
        Gson customGson = gsonBuilder.create();
        String json = customGson.toJson(box);
        System.out.println(json);
       // PrivateKeyBox privateKeyBox = customGson.fromJson(json, PrivateKeyBox.class);
        //System.out.println(privateKeyBox);
    }

}
