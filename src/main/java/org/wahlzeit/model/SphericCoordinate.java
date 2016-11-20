package org.wahlzeit.model;

import org.wahlzeit.utils.CoordinateUtil;

import static java.lang.Math.*;


/**
 * Implementation of Coordinate interface using spheric coordinates
 */
public class SphericCoordinate implements Coordinate {

	private final double latitude;
	private final double longitude;

	/**
	 * Constructor for SphericCoordinate
	 *
	 * @methodtype constructor
	 * @param latitude
	 * @param longitude
	 * @return Distance in km
	 */
	public SphericCoordinate(double latitude, double longitude) {
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Invalid latitude.");
		}

		if (longitude > 180 || longitude < -180) {
			throw new IllegalArgumentException("Invalid longitude.");
		}

		this.latitude = latitude;
		this.longitude = longitude;
	}

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
			throw new InstantiationError("Unsupported class used in fromCoordinate");
		}
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
	@Override
	public double getDistance(Coordinate otherCoordinate) {
		if(otherCoordinate == null) {
			throw new NullPointerException("otherCoordinate is null.");
		}

		return CoordinateUtil.doGetDistance(this, otherCoordinate);
	}

}
