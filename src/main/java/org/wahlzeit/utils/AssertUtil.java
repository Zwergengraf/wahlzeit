package org.wahlzeit.utils;

public class AssertUtil {
	public static void assertObjectNotNull(Object o, String name) {
		assert o != null : name + " is null.";
	}

	public static void assertIsValidDouble(Double d, String name) {
		assert !Double.isInfinite(d) && !Double.isNaN(d) : name + " is not a valid Double.";
	}
}
