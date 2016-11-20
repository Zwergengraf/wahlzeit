package org.wahlzeit.utils;

import org.wahlzeit.model.Coordinate;
import org.wahlzeit.model.SphericCoordinate;

/**
 * Collection of util methods used by classes that implement the Coordinate interface
 */
public class CoordinateUtil {

	// Radius in km, public for use in test classes
	public static final double EARTH_RADIUS_KM = 6371;

	public static double doGetDistance(Coordinate c1, Coordinate c2) {

		// Transform the coordinates to SphericCoordinates
		SphericCoordinate sc1 = SphericCoordinate.fromCoordinate(c1);
		SphericCoordinate sc2 = SphericCoordinate.fromCoordinate(c2);

		// Great circle distance: https://en.wikipedia.org/wiki/Great-circle_distance
		double lat1 = Math.toRadians(sc1.getLatitude());
		double lon1 = Math.toRadians(sc1.getLongitude());
		double lat2 = Math.toRadians(sc2.getLatitude());
		double lon2 = Math.toRadians(sc2.getLongitude());

		double dist = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(Math.abs(lon1) - Math.abs(lon2)))
						* EARTH_RADIUS_KM;

		return dist;
	}

}
