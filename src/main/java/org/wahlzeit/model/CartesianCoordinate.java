package org.wahlzeit.model;


/**
 * Implementation of Coordinate interface using cartesian coordinates
 */
public class CartesianCoordinate extends AbstractCoordinate {

	private final double x;
	private final double y;
	private final double z;

	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
}
