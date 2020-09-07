package com.java8.tutorial.codewar.fibonacci_streaming;

import org.junit.Test;
import java.util.PrimitiveIterator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

/**
 * Created by HauKute on 8/30/2020.
 */

public class TestSuite {

	@Test
	public void testThatTheFirstTwentyElementsAreCorrect() {
		assertArrayEquals("The first twenty elements are incorrect!",
						new int[] {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765},
						Utility.generateFibonacciSequence2().limit(20)
										.toArray());
	}
}
