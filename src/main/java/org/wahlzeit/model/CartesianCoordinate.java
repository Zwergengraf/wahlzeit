package org.wahlzeit.model;


import org.wahlzeit.utils.AssertUtil;

/**
 * Implementation of Coordinate interface using cartesian coordinates
 */
public class CartesianCoordinate extends AbstractCoordinate {

	private final double x;
	private final double y;
	private final double z;

	public CartesianCoordinate(double x, double y, double z) {
		AssertUtil.assertIsValidDouble(x, "x");
		AssertUtil.assertIsValidDouble(y, "y");
		AssertUtil.assertIsValidDouble(z, "z");

		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariants();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	private void assertClassInvariants() {
		AssertUtil.assertIsValidDouble(x, "x");
		AssertUtil.assertIsValidDouble(y, "y");
		AssertUtil.assertIsValidDouble(z, "z");
	}
}
