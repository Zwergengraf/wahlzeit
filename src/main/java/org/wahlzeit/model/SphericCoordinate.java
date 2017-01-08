package org.wahlzeit.model;

import org.wahlzeit.utils.AssertUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.*;


/**
 * Implementation of Coordinate interface using spheric coordinates
 */
@PatternInstance(
		patternName = "Value Object",
		participants = {
				"SphericCoordinate"
		}
)
public class SphericCoordinate extends AbstractCoordinate {

	private static ObjectCache<SphericCoordinate> instances = new ObjectCache<>();

	private static final Logger logger = Logger.getLogger(SphericCoordinate.class.getName());

	private final double latitude;
	private final double longitude;
	private final double radius;

	public static final double EARTH_RADIUS_KM = 6371;


	public static SphericCoordinate getInstance(double latitude, double longitude) throws IllegalArgumentException {
		SphericCoordinate newObject = new SphericCoordinate(latitude, longitude);
		SphericCoordinate returnValue = instances.getObject(newObject);
		AssertUtil.assertObjectNotNull(returnValue, "returnValue");
		return returnValue;
	}

	public static SphericCoordinate getInstance(double latitude, double longitude, double radius) throws IllegalArgumentException {
		SphericCoordinate newObject = new SphericCoordinate(latitude, longitude, radius);
		SphericCoordinate returnValue = instances.getObject(newObject);
		AssertUtil.assertObjectNotNull(returnValue, "returnValue");
		return returnValue;
	}

	/**
	 * Constructor for SphericCoordinate, using earth radius
	 *
	 * @methodtype constructor
	 * @param latitude
	 * @param longitude
	 */
	private SphericCoordinate(double latitude, double longitude) throws IllegalArgumentException {
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
	private SphericCoordinate(double latitude, double longitude, double radius) throws IllegalArgumentException {
		if(!isValidSphericCoordinate(latitude, longitude, radius)) {
			logger.log(Level.ALL, "Invalid SphericCoordinate given.");
			throw new IllegalArgumentException("Invalid SphericCoordinate.");
		}

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;

		assertClassInvariants();
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

	/**
	 * Returns the distance (in KM) between two coordinates
	 * @param otherCoordinate
	 * @return distance in KM
	 */
	@Override
	public double getDistance(Coordinate otherCoordinate) {
		AssertUtil.assertObjectNotNull(otherCoordinate, "otherCoordinate");

		if (otherCoordinate instanceof SphericCoordinate) {
			SphericCoordinate sc = (SphericCoordinate) otherCoordinate;

			// Only use spheric distance if both are SphericCoordinates and have an equal radius
			if (Math.abs(sc.getRadius() - this.radius) < AbstractCoordinate.COORDINATE_DELTA) {
				return this.doGetDistance(sc);
			}
		}

		return super.getDistance(otherCoordinate);
	}

	private double doGetDistance(SphericCoordinate otherCoordinate) {
		assertSameRadius(otherCoordinate);
		assertValidSphericCoordinate(otherCoordinate);
		assertClassInvariants();

		// Great circle distance: https://en.wikipedia.org/wiki/Great-circle_distance
		double lat1 = Math.toRadians(this.getLatitude());
		double lon1 = Math.toRadians(this.getLongitude());
		double lat2 = Math.toRadians(otherCoordinate.getLatitude());
		double lon2 = Math.toRadians(otherCoordinate.getLongitude());

		double dist = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(Math.abs(lon1) - Math.abs(lon2)))
				* getRadius();

		assertValidDistance(dist);
		return dist;
	}

	private void assertSameRadius(SphericCoordinate otherCoordinate) throws IllegalArgumentException {
		if (Math.abs(otherCoordinate.getRadius() - this.radius) > AbstractCoordinate.COORDINATE_DELTA) {
			throw new IllegalArgumentException("Radius of SphericCoordinates must be equal.");
		}
	}

	private void assertValidSphericCoordinate(SphericCoordinate sc) {
		AssertUtil.assertIsValidDouble(sc.getLatitude(), "latitude");
		AssertUtil.assertIsValidDouble(sc.getLongitude(), "longitude");
		AssertUtil.assertIsValidDouble(sc.getRadius(), "radius");

		assert isValidSphericCoordinate(sc.latitude, sc.longitude, sc.radius) : "Invalid coordinate.";
	}

	private void assertClassInvariants() {
		assertValidSphericCoordinate(this);
	}

	private boolean isValidSphericCoordinate(double latitude, double longitude, double radius) {
		return ( (latitude >= -90 && latitude <= 90) &&
				(longitude >= -180 && longitude <= 180) &&
				(radius > 0) );
	}

}
