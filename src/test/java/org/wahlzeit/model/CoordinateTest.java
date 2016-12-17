package org.wahlzeit.model;

import org.junit.Test;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static org.junit.Assert.assertEquals;
import static org.wahlzeit.model.SphericCoordinate.EARTH_RADIUS_KM;

/**
 * All test cases of the class {@link Coordinate}.
 */
public class CoordinateTest {

	private double nurembergLat = 49.4529;
	private double nurembergLon = 11.0768;
	private double x = EARTH_RADIUS_KM * sin(toRadians(nurembergLat)) * cos(toRadians(nurembergLon));
	private double y = EARTH_RADIUS_KM * sin(toRadians(nurembergLat)) * sin(toRadians(nurembergLon));
	private double z = EARTH_RADIUS_KM * cos(toRadians(nurembergLat));

	@Test
	public void testDistanceSphericCartesian() {
		CartesianCoordinate nurembergCartesian = CartesianCoordinate.getInstance(x, y, z);
		SphericCoordinate nurembergSpheric = SphericCoordinate.getInstance(nurembergLat, nurembergLon);

		double distance = nurembergCartesian.getDistance(nurembergSpheric);
		assertEquals(0, distance, 0.01);
	}

}
