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

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * The class {@code BigDecimalMoneyScaledAdapter} is a custom {@link TypeAdapter} for serializing
 * and deserializing {@link BigDecimal} values with a specific scale (two decimal places) It ensures
 * that BigDecimal values are serialized with two digits of precision after the decimal point
 */
public class BigDecimalMoneyScaledAdapter extends TypeAdapter<BigDecimal>
{
	/**
	 * {@inheritDoc} This implementation serializes the {@link BigDecimal} value to JSON with a
	 * scale of two decimal places If the value is null, it writes a null JSON value
	 *
	 * @param out
	 *            the {@link JsonWriter} to write to
	 * @param value
	 *            the {@link BigDecimal} value to be serialized
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Override
	public void write(JsonWriter out, BigDecimal value) throws IOException
	{
		out.value(value == null ? null : value.setScale(2, RoundingMode.DOWN));
	}

	/**
	 * {@inheritDoc} This implementation deserializes a {@link BigDecimal} from a JSON string,
	 * ensuring it has two decimal places If the JSON token is null, it returns null
	 *
	 * @param in
	 *            the {@link JsonReader} to read from
	 * @return the deserialized {@link BigDecimal} object, or null if the JSON token is null
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Override
	public BigDecimal read(JsonReader in) throws IOException
	{
		if (in.peek() == JsonToken.NULL)
		{
			in.nextNull();
			return null;
		}
		return deserializeToBigDecimal(in.nextString());
	}

	/**
	 * Deserializes a string value into a {@link BigDecimal}, ensuring the value has two decimal
	 * places This method uses {@link NumberFormat} to ensure proper formatting with two decimal
	 * places
	 *
	 * @param json
	 *            the JSON string representation of the {@link BigDecimal} value
	 * @return the deserialized {@link BigDecimal} with two decimal places
	 */
	private synchronized BigDecimal deserializeToBigDecimal(String json)
	{
		BigDecimal bigDecimal = new BigDecimal(json);
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return new BigDecimal(nf.format(bigDecimal));
	}
}
