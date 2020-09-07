package com.java8.tutorial.functional_interface;

/**
 * Created by HauKute on 7/26/2020.
 */
@java.lang.FunctionalInterface
public interface FunctionalInterfaceTest {

	void call();

	@Override
	String toString();

	@Override
	boolean equals(Object obj);

	default void doWork() {

		System.out.println("This is default method");
	}
}
