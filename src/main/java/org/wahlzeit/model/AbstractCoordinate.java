package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	@Override
	public final double getDistance(Coordinate otherCoordinate) {
		assertCoordinateNotNull(otherCoordinate);

		// Only use cartesian distance if both coordinates are CartesianCoordinates
		if(this instanceof CartesianCoordinate && otherCoordinate instanceof CartesianCoordinate) {
			CartesianCoordinate c1 = (CartesianCoordinate)this;
			CartesianCoordinate c2 = (CartesianCoordinate)otherCoordinate;

			return c1.doGetDistance(c2);
		}

		// Transform both coordinates to SphericCoordinates
		SphericCoordinate sc1 = SphericCoordinate.fromCoordinate(this);
		SphericCoordinate sc2 = SphericCoordinate.fromCoordinate(otherCoordinate);

		return sc1.doGetDistance(sc2);
	}

	@Override
	public final boolean isEqual(Coordinate otherCoordinate) {
		assertCoordinateNotNull(otherCoordinate);

		// Only use cartesian method doIsEqual if both coordinates are CartesianCoordinates
		if(this instanceof CartesianCoordinate && otherCoordinate instanceof CartesianCoordinate) {
			CartesianCoordinate c1 = (CartesianCoordinate)this;
			CartesianCoordinate c2 = (CartesianCoordinate)otherCoordinate;

			return c1.doIsEqual(c2);
		}

		// Transform both coordinates to SphericCoordinates
		SphericCoordinate sc1 = SphericCoordinate.fromCoordinate(this);
		SphericCoordinate sc2 = SphericCoordinate.fromCoordinate(otherCoordinate);

		return sc1.doIsEqual(sc2);
	}

	private void assertCoordinateNotNull(Coordinate c) {
		assert c != null : "Coordinate is null.";
	}

}
