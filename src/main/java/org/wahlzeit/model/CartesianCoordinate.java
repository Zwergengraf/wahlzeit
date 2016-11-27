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

		// Check the radius to make sure that the CartesianCoordinate is valid
		/*double testR = sqrt(pow(getX(), 2) + pow(getY(), 2) + pow(getZ(), 2));
		if(abs(testR - EARTH_RADIUS_KM) > RADIUS_DELTA_KM) {
			throw new InstantiationError("Invalid radius, only coordinates ON earth are supported.");
		}*/
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

	protected double doGetDistance(CartesianCoordinate otherCoordinate) {
		double diffX = Math.abs(this.getX() - otherCoordinate.getX());
		double diffY = Math.abs(this.getY() - otherCoordinate.getY());
		double diffZ = Math.abs(this.getZ() - otherCoordinate.getZ());
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}

	protected boolean doIsEqual(CartesianCoordinate otherCoordinate) {
		return this.getX() == otherCoordinate.getX() &&
				this.getY() == otherCoordinate.getY() &&
				this.getZ() == otherCoordinate.getZ();
	}
}
