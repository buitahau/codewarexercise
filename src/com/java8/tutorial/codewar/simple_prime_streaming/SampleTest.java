package com.java8.tutorial.codewar.simple_prime_streaming;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by HauKute on 8/30/2020.
 */
/**
 * Consider a sequence made up of the consecutive prime numbers. This
 * infinite sequence would start with: "2357111317192329313741434753596167717379"
 * You will be given two numbers: a and b, and your task will be to return b elements starting from index a in this sequence.
 * For example:
 * solve(10,5) == `19232` Because these are 5 elements from index 10 in the
 * sequence.
 */
public class SampleTest {

	@Test
	public void basicTests() {

		doTest(    2,  2, "57");
		doTest(   10,  3, "192");
		doTest(   20,  9, "414347535");
		doTest(   30, 12, "616771737983");
		doTest(   40,  8, "83899710");
		doTest(   50,  6, "031071");
		doTest(10000,  5, "02192");
		doTest(20000,  5, "09334");

	}

	private void doTest(int a, int b, String expected) {
		assertEquals(expected, SimplePrimeStreaming.solve(a, b));
	}
}
