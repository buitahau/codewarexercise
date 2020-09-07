package com.java8.tutorial.codewar.fibonacci_streaming;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by HauKute on 8/30/2020.
 */

/**
 * You're going to provide a needy programmer a utility method that generates
 * an infinite sized, sequential IntStream (in TypeScript Iterator<number>,
 * in Python generator) which contains all the numbers in a fibonacci sequence.
 * A fibonacci sequence starts with two 1s. Every element afterwards is the
 * sum of the two previous elements. See:
 * 1, 1, 2, 3, 5, 8, 13, ..., 89, 144, 233, 377, ...
 */

public class Utility {

	static int num_pre;
	static int num_pre_pre;

	public static IntStream generateFibonacciSequence() {

		/**
		 * My solution
		 */
		num_pre = 0;
		num_pre_pre = 0;

		return IntStream.iterate(1, i -> i + 1)
			.map(i -> {
				if(num_pre_pre == 0){
					num_pre_pre = 1;
					return 1;
				}
				if(num_pre == 0){
					num_pre = 1;
					return 1;
				}

				int result = num_pre + num_pre_pre;
				num_pre_pre = num_pre;
				num_pre = result;
				return result;
			});
	}

	public static IntStream generateFibonacciSequence1() {
		return IntStream.generate(new IntSupplier() {

			int pre = 0;
			int pre_pre = 0;

			@Override public int getAsInt() {

				if(pre == 0) {
					pre = 1;
					return 1;
				}

				if(pre_pre == 0) {
					pre_pre = 1;
					return 1;
				}

				int next = pre + pre_pre;
				pre_pre = pre;
				pre = next;
				return next;
			}
		});
	}

	public static IntStream generateFibonacciSequence2() {

		return Stream.iterate(new int[]{1,1}, arr -> new int[]{arr[1],
						arr[0] + arr[1]})
						.mapToInt(a -> a[0]);
	}
}
