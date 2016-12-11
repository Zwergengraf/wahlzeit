package org.wahlzeit.utils;

public class AssertUtil {
	public static void assertObjectNotNull(Object o, String name) throws NullPointerException {
		if(o == null) {
			throw new NullPointerException(name);
		}
	}

	public static void assertIsValidDouble(Double d, String name) {
		assert !Double.isInfinite(d) && !Double.isNaN(d) : name + " is not a valid Double.";
	}
}
