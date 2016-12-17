package org.wahlzeit.model;


import org.wahlzeit.utils.AssertUtil;

/**
 * Implementation of Coordinate interface using cartesian coordinates
 */
public class CartesianCoordinate extends AbstractCoordinate {

	private static ObjectCache<CartesianCoordinate> instances = new ObjectCache<>();

	private final double x;
	private final double y;
	private final double z;

	public static CartesianCoordinate getInstance(double x, double y, double z) {
		CartesianCoordinate newObject = new CartesianCoordinate(x, y, z);
		CartesianCoordinate returnValue = instances.getObject(newObject);
		AssertUtil.assertObjectNotNull(returnValue, "returnValue");
		return returnValue;
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

	public static int getCachedObjectCount() {
		return instances.getObjectCount();
	}

	private CartesianCoordinate(double x, double y, double z) {
		AssertUtil.assertIsValidDouble(x, "x");
		AssertUtil.assertIsValidDouble(y, "y");
		AssertUtil.assertIsValidDouble(z, "z");

		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariants();
	}

	private void assertClassInvariants() {
		AssertUtil.assertIsValidDouble(x, "x");
		AssertUtil.assertIsValidDouble(y, "y");
		AssertUtil.assertIsValidDouble(z, "z");
	}
}
