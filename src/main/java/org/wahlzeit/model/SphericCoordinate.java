package org.wahlzeit.model;

import static java.lang.Math.*;


/**
 * Implementation of Coordinate interface using spheric coordinates
 */
public class SphericCoordinate extends AbstractCoordinate {

	private final double latitude;
	private final double longitude;
	private final double radius;

	public static final double EARTH_RADIUS_KM = 6371;

	/**
	 * Constructor for SphericCoordinate, using earth radius
	 *
	 * @methodtype constructor
	 * @param latitude
	 * @param longitude
	 */
	public SphericCoordinate(double latitude, double longitude) {
		this(latitude, longitude, EARTH_RADIUS_KM);
	}

	/**
	 * Constructor for SphericCoordinate
	 *
	 * @methodtype constructor
	 * @param latitude
	 * @param longitude
	 * @param radius
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Invalid latitude.");
		}

		if (longitude > 180 || longitude < -180) {
			throw new IllegalArgumentException("Invalid longitude.");
		}

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getRadius() { return radius; }

	/**
	 * Transforms a given Coordinate into a SphericCoordinate
	 *
	 * @param c1
	 * @return New SphericCoordinate
	 */
	public static SphericCoordinate fromCoordinate(Coordinate c1) {
		if(c1 instanceof SphericCoordinate) {

			// Is already a SphericCoordinate, nothing more to do
			return (SphericCoordinate)c1;

		} else if(c1 instanceof CartesianCoordinate) {

			// Transform Cartesian -> Spheric
			// https://de.wikipedia.org/wiki/Kugelkoordinaten
			CartesianCoordinate cc1 = (CartesianCoordinate) c1;

			double lat = (PI / 2) - atan(cc1.getZ() / sqrt(pow(cc1.getX(), 2) + pow(cc1.getY(), 2)));
			double lon = atan2(toRadians(cc1.getY()), toRadians(cc1.getX()));

			double degLat = toDegrees(lat);
			double degLon = toDegrees(lon);
			return new SphericCoordinate(degLat, degLon);

		} else {
			throw new InstantiationError("Unsupported class used in fromCoordinate.");
		}
	}

	protected double doGetDistance(SphericCoordinate otherCoordinate) {
		assertSameRadius(otherCoordinate);

		// Great circle distance: https://en.wikipedia.org/wiki/Great-circle_distance
		double lat1 = Math.toRadians(this.getLatitude());
		double lon1 = Math.toRadians(this.getLongitude());
		double lat2 = Math.toRadians(otherCoordinate.getLatitude());
		double lon2 = Math.toRadians(otherCoordinate.getLongitude());

		double dist = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(Math.abs(lon1) - Math.abs(lon2)))
				* getRadius();

		return dist;
	}

	protected boolean doIsEqual(SphericCoordinate otherCoordinate) {
		assertSameRadius(otherCoordinate);

		return this.getLatitude() == otherCoordinate.getLatitude() &&
				this.getLongitude() == otherCoordinate.getLongitude() &&
				this.getRadius() == otherCoordinate.getRadius();
	}

	private void assertSameRadius(SphericCoordinate otherCoordinate) {
		assert this.getRadius() == otherCoordinate.getRadius() : "Radius of SphericCoordinates must be equal.";
	}
}
