package org.wahlzeit.model;

import org.junit.*;
import org.wahlzeit.utils.CoordinateUtil;

import static java.lang.Math.*;
import static org.junit.Assert.*;

public class CartesianCoordinateTest {

	private double nurembergLat = 49.4529;
	private double nurembergLon = 11.0768;
	private double x = CoordinateUtil.EARTH_RADIUS_KM * sin(toRadians(nurembergLat)) * cos(toRadians(nurembergLon));
	private double y = CoordinateUtil.EARTH_RADIUS_KM * sin(toRadians(nurembergLat)) * sin(toRadians(nurembergLon));
	private double z = CoordinateUtil.EARTH_RADIUS_KM * cos(toRadians(nurembergLat));
	private CartesianCoordinate nuremberg;

	@Before
	public void setUp() {
		nuremberg = new CartesianCoordinate(x, y, z);
	}

	@Test
	public void testGetters() {
		assertEquals(x, nuremberg.getX(), 0);
		assertEquals(y, nuremberg.getY(), 0);
		assertEquals(z, nuremberg.getZ(), 0);
	}



}
