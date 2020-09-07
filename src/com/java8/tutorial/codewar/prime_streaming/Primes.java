package com.java8.tutorial.codewar.prime_streaming;

import java.util.stream.IntStream;

/**
 * Created by HauKute on 8/31/2020.
 */

/**
 * Create an endless stream of prime numbers - a bit like
 * IntStream.of(2, 3, 5, 7, 11, 13, 17), but infinite.
 * The stream must be able to produce a million primes in a few seconds.
 */
public class Primes {

	public static IntStream stream() {
		return IntStream.iterate(2, i -> i + 1)
			.filter(x -> {
				if(x == 2) return true;
				return !IntStream.range(2, (int) (Math.sqrt(x) + 1))
								.anyMatch(y	-> x % y == 0
				);
			}

		);
	}
}
