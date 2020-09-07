package com.java8.tutorial.codewar.simple_prime_streaming;

/**
 * Created by HauKute on 8/30/2020.
 */

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Consider a sequence made up of the consecutive prime numbers. This
 * infinite sequence would start with: "2357111317192329313741434753596167717379"
 * You will be given two numbers: a and b, and your task will be to return b elements starting from index a in this sequence.
 * For example:
 * solve(10,5) == `19232` Because these are 5 elements from index 10 in the
 * sequence.
 */
public class SimplePrimeStreaming {

	private static final int STEP = 10000;

	private static final int MAX_LENGTH = 30000;

	private static String CONSECUTIVE_PRIME_NUMBERS_STRING = null;

	public static String solve(int a, int b) {

		/* My solution
		Stream<String> characterStream =_getConsecutivePrimeNumbersString()
						.codePoints()
						.mapToObj(c -> String.valueOf((char) c));

		return characterStream
						.skip(a)
						.limit(b)
						.reduce("", (x, y) -> x + y);
		*/

		/*
		 * Other solution
		 */
		return IntStream.iterate(2, i -> i + 1)
						.filter(x -> _checkConsecutiveNumber(x))
						.mapToObj(c -> String.valueOf(c))
						.flatMap(c -> c.codePoints().mapToObj(d -> String.valueOf((char)d)))
						.skip(a)
						.limit(b)
						.reduce("", (x, y) -> x + y);
	}

	private static String _getConsecutivePrimeNumbersString() {

		if(CONSECUTIVE_PRIME_NUMBERS_STRING != null) {
			return CONSECUTIVE_PRIME_NUMBERS_STRING;
		}

		int startInclusive = 2;

		StringBuilder result = new StringBuilder("");

		while (result.length() < MAX_LENGTH) {
			result.append(IntStream.range(startInclusive, startInclusive + STEP)
							.filter(x -> _checkConsecutiveNumber(x))
							.mapToObj(y -> String.valueOf(y))
							.reduce("", (a, b) -> a + b));

			startInclusive += STEP;
		}

		CONSECUTIVE_PRIME_NUMBERS_STRING = result.toString();

		return CONSECUTIVE_PRIME_NUMBERS_STRING;
	}

	private static boolean _checkConsecutiveNumber(final int x) {

		if(x <= 2) {
			return true;
		}

		return IntStream.range(2, (int)(Math.sqrt(x) + 1))
					.filter(y -> x % y == 0)
					.count() == 0;
	}

	/**
	 * Other solution from codewar
	 */

	private static IntPredicate isPrime;

	public static String solution1(int a, int b) {

		isPrime = x -> true;

		final IntStream primes= IntStream.iterate(2, i -> i + 1)
						.filter(i -> isPrime.test(i))
						.peek(i -> isPrime = isPrime.and(v -> v % i != 0));

		return primes
						.mapToObj(i -> String.valueOf(i))
						.flatMap(s -> s.codePoints().mapToObj(c -> String.valueOf((char) c)))
						.skip(a)
						.limit(b)
						.collect(Collectors.joining(""));
	}

	public static String solution2(int a, int b) {

		return IntStream.iterate(2, i -> i + 1)
						.filter(number -> (new BigInteger(String.valueOf(number))).isProbablePrime(number))
						.mapToObj(arr -> String.valueOf(arr).split(""))
						.flatMap(Arrays::stream)
						.skip(a)
						.limit(b)
						.collect(Collectors.joining());
	}
}
