package org.wahlzeit.utils;

import org.junit.Test;
import org.wahlzeit.model.CartesianCoordinate;
import org.wahlzeit.model.SphericCoordinate;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static org.junit.Assert.assertEquals;

/**
 * All test cases of the class {@link CoordinateUtil}.
 */
public class CoordinateUtilTest {

	private double nurembergLat = 49.4529;
	private double nurembergLon = 11.0768;
	private double x = CoordinateUtil.EARTH_RADIUS_KM * sin(toRadians(nurembergLat)) * cos(toRadians(nurembergLon));
	private double y = CoordinateUtil.EARTH_RADIUS_KM * sin(toRadians(nurembergLat)) * sin(toRadians(nurembergLon));
	private double z = CoordinateUtil.EARTH_RADIUS_KM * cos(toRadians(nurembergLat));

	@Test
	public void testDistanceSphericCartesian() {
		CartesianCoordinate nurembergCartesian = new CartesianCoordinate(x, y, z);
		SphericCoordinate nurembergSpheric = new SphericCoordinate(nurembergLat, nurembergLon);

		double distance = nurembergCartesian.getDistance(nurembergSpheric);
		assertEquals(0, distance, 0.01);
	}

}
