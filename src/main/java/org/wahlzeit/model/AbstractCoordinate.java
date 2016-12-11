package org.wahlzeit.model;

import org.wahlzeit.utils.AssertUtil;

public abstract class AbstractCoordinate implements Coordinate {

	public static final double COORDINATE_DELTA = 1E-6;

	/**
	 * Returns the distance (in KM) between two coordinates
	 * @param otherCoordinate
	 * @return distance in KM
	 */
	@Override
	public double getDistance(Coordinate otherCoordinate) {
		AssertUtil.assertObjectNotNull(otherCoordinate, "otherCoordinate");

		double diffX = Math.abs(this.getX() - otherCoordinate.getX());
		double diffY = Math.abs(this.getY() - otherCoordinate.getY());
		double diffZ = Math.abs(this.getZ() - otherCoordinate.getZ());

		double distance = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2) + Math.pow(diffZ, 2));
		assertValidDistance(distance);
		return distance;
	}

	/**
	 * Checks for equality of two coordinates (using COORDINATE_DELTA)
	 * @param otherCoordinate
	 * @return true if equal, false otherwise
	 */
	@Override
	public boolean isEqual(Coordinate otherCoordinate) {
		AssertUtil.assertObjectNotNull(otherCoordinate, "otherCoordinate");

		return this.getDistance(otherCoordinate) < COORDINATE_DELTA;
	}

	protected void assertValidDistance(double distance) {
		AssertUtil.assertIsValidDouble(distance, "distance");
		assert distance >= 0 : "Invalid distance, must be > 0.";
	}

}
