package org.wahlzeit.model;

import org.wahlzeit.utils.CoordinateUtil;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Implementation of Coordinate interface using cartesian coordinates
 */
public class CartesianCoordinate implements Coordinate {

	// Radius delta, used to check for valid radius
	private static final double RADIUS_DELTA_KM = 5;

	private final double x;
	private final double y;
	private final double z;

	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

		// Check the radius to make sure that the CartesianCoordinate is valid
		double testR = sqrt(pow(getX(), 2) + pow(getY(), 2) + pow(getZ(), 2));
		if(abs(testR - CoordinateUtil.EARTH_RADIUS_KM) > RADIUS_DELTA_KM) {
			throw new InstantiationError("Invalid radius, only coordinates ON earth are supported.");
		}
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

	@Override
	public double getDistance(Coordinate otherCoordinate) {
		if(otherCoordinate == null) {
			throw new NullPointerException("otherCoordinate is null.");
		}

		return CoordinateUtil.doGetDistance(this, otherCoordinate);
	}
}
