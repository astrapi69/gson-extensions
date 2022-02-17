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
package io.github.astrapi69.gson.instance.creators;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.astrapi69.crypto.algorithm.KeyPairGeneratorAlgorithm;
import io.github.astrapi69.crypto.factory.KeyPairFactory;
import io.github.astrapi69.crypto.key.KeySize;

public class GenericInstanceCreatorTest
{

	@Test
	void testGenericInstanceCreator() throws NoSuchAlgorithmException, NoSuchProviderException
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		KeyPair keyPair = KeyPairFactory.newKeyPair(KeyPairGeneratorAlgorithm.DIFFIE_HELLMAN,
			KeySize.KEYSIZE_2048);
		PrivateKeyBox box = new PrivateKeyBox(keyPair.getPrivate());
		gsonBuilder.registerTypeAdapter(PrivateKeyBox.class,
			new PrivateKeyBoxInstanceCreator(keyPair.getPrivate()));
		Gson customGson = gsonBuilder.create();
		String json = customGson.toJson(box);
		System.out.println(json);
		// PrivateKeyBox privateKeyBox = customGson.fromJson(json, PrivateKeyBox.class);
		// System.out.println(privateKeyBox);
	}

}
