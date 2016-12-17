package org.wahlzeit.utils;


import org.junit.Test;

public class AssertUtilTest {

	@Test
	public void testAssertObjectNotNullTrue() {
		Object o = new Object();
		AssertUtil.assertObjectNotNull(o, "o");
	}

	@Test(expected=NullPointerException.class)
	public void testAssertObjectNotNullFalse() {
		Object o = null;
		AssertUtil.assertObjectNotNull(o, "null object");
	}

	@Test
	public void testAssertIsValidDoubleTrue() {
		double d = 47.11f;
		AssertUtil.assertIsValidDouble(d, "d");
	}

	@Test(expected=AssertionError.class)
	public void testAssertIsValidDoubleFalse1() {
		double d = Double.POSITIVE_INFINITY;
		AssertUtil.assertIsValidDouble(d, "INFINITY");
	}

	@Test(expected=AssertionError.class)
	public void testAssertIsValidDoubleFalse2() {
		double d = Double.NaN;
		AssertUtil.assertIsValidDouble(d, "NaN");
	}
}
