package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	public static final double COORDINATE_DELTA = 1E-6;

	public abstract double getX();
	public abstract double getY();
	public abstract double getZ();

	@Override
	public double getDistance(Coordinate otherCoordinate) {
		assertCoordinateNotNull(otherCoordinate);

		double diffX = Math.abs(this.getX() - ((AbstractCoordinate)otherCoordinate).getX());
		double diffY = Math.abs(this.getY() - ((AbstractCoordinate)otherCoordinate).getY());
		double diffZ = Math.abs(this.getZ() - ((AbstractCoordinate)otherCoordinate).getZ());

		return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2) + Math.pow(diffZ, 2));
	}

	@Override
	public boolean isEqual(Coordinate otherCoordinate) {
		return this.getDistance(otherCoordinate) < COORDINATE_DELTA;
	}

	protected void assertCoordinateNotNull(Coordinate c) {
		assert c != null : "Coordinate is null.";
	}

}
