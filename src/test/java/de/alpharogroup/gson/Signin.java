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

import java.math.BigDecimal;
import java.util.Objects;

public class Signin
{
	private String password;
	private String username;
	private BigDecimal points;

	public Signin()
	{
	}

	public Signin(String password, String username)
	{
		this.password = password;
		this.username = username;
	}

	public Signin(String password, String username, BigDecimal points)
	{
		this.password = password;
		this.username = username;
		this.points = points;
	}

	public static SigninBuilder builder()
	{
		return new SigninBuilder();
	}

	public BigDecimal getPoints()
	{
		return points;
	}

	public void setPoints(BigDecimal points)
	{
		this.points = points;
	}

	protected boolean canEqual(final Object other)
	{
		return other instanceof Signin;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Override public String toString()
	{
		return "Signin(password=" + this.getPassword() + ", username=" + this
			.getUsername() + ", points=" + this.points + ")";
	}

	@Override public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Signin signin = (Signin)o;
		return Objects.equals(password, signin.password) && Objects
			.equals(username, signin.username) && Objects.equals(points, signin.points);
	}

	@Override public int hashCode()
	{
		return Objects.hash(password, username, points);
	}

	public static class SigninBuilder
	{
		private String password;
		private String username;
		private BigDecimal points;

		SigninBuilder()
		{
		}

		public Signin build()
		{
			return new Signin(password, username, points);
		}

		public Signin.SigninBuilder points(BigDecimal points)
		{
			this.points = points;
			return this;
		}

		public Signin.SigninBuilder password(String password)
		{
			this.password = password;
			return this;
		}

		@Override public String toString()
		{
			return "Signin.SigninBuilder(password=" + this.password + ", username=" + this.username + ", points=" + this.points + ")";
		}

		public Signin.SigninBuilder username(String username)
		{
			this.username = username;
			return this;
		}
	}
}