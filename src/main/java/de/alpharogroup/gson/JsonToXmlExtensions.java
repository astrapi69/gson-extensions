/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * The class {@link JsonToXmlExtensions} helps to transform a given json string to an xml string.
 */
public final class JsonToXmlExtensions
{

	private JsonToXmlExtensions()
	{
	}

	/**
	 * Transform the given json as {@link String} object to an xml as {@link String} object.
	 *
	 * @param jsonString
	 *            the json as {@link String} object
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key.
	 */
	public static String toXml(final String jsonString) throws JSONException
	{
		final JSONObject json = new JSONObject(jsonString);
		return XML.toString(json);
	}

}
