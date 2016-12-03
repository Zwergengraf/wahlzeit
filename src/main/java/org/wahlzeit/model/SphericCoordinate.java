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
		assertValidSphericCoordinate(latitude, longitude, radius);

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

	@Override
	public double getX() {
		return radius * sin(toRadians(latitude)) * cos(toRadians(longitude));
	}

	@Override
	public double getY() {
		return radius * sin(toRadians(latitude)) * sin(toRadians(longitude));
	}

	@Override
	public double getZ() {
		return radius * cos(toRadians(latitude));
	}

	@Override
	public double getDistance(Coordinate otherCoordinate) {
		assertCoordinateNotNull(otherCoordinate);

		if (otherCoordinate instanceof SphericCoordinate) {
			SphericCoordinate sc = (SphericCoordinate) otherCoordinate;

			// Only use spheric distance if both are SphericCoordinates and have an equal radius
			if (Math.abs(sc.getRadius() - this.radius) < AbstractCoordinate.COORDINATE_DELTA) {
				return this.doGetDistance(sc);
			}
		}

		return super.getDistance(otherCoordinate);
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

	private void assertSameRadius(SphericCoordinate otherCoordinate) {
		assert (Math.abs(otherCoordinate.getRadius() - this.radius) < AbstractCoordinate.COORDINATE_DELTA) : "Radius of SphericCoordinates must be equal.";
	}

	private void assertValidSphericCoordinate(double latitude, double longitude, double radius) {
		assert (latitude <= 90 && latitude >= -90) : "Invalid latitude.";
		assert (longitude <= 180 && latitude >= -180) : "Invalid longitude.";
		assert (radius > 0) : "Invalid radius.";
	}
}
