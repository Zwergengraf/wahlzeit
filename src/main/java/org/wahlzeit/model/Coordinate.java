package org.wahlzeit.model;

/**
 * Coordinate class
 */
public class Coordinate {

	// Earth radius in km - Public because it is accessed in CoordinateTest
	public static final double EARTH_RADIUS = 6371;

	private final double latitude;
	private final double longitude;

	public Coordinate(double latitude, double longitude) {
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Invalid latitude.");
		}

		if (longitude > 180 || longitude < -180) {
			throw new IllegalArgumentException("Invalid longitude.");
		}

		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	/**
	 * Method to calculate the distance between two coordinates
	 *
	 * @param otherCoordinate
	 * @return Distance in km
	 */
	public double getDistance(Coordinate otherCoordinate) {
		if(otherCoordinate == null) {
			throw new NullPointerException("otherCoordinate is null.");
		}

		/* Great circle distance:
		 * https://en.wikipedia.org/wiki/Great-circle_distance
		 */
		double lat1 = Math.toRadians(getLatitude());
		double lon1 = Math.toRadians(getLongitude());
		double lat2 = Math.toRadians(otherCoordinate.getLatitude());
		double lon2 = Math.toRadians(otherCoordinate.getLongitude());

		double dist = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(Math.abs(lon1) - Math.abs(lon2))) * EARTH_RADIUS;

		return dist;
	}
}
