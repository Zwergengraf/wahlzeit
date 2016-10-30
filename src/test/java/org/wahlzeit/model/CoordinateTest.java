package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

	private static final Coordinate NORTH_POLE = new Coordinate(90, 0);
	private static final Coordinate SOUTH_POLE = new Coordinate(-90, 0);

	private static final Coordinate NUREMBERG = new Coordinate(49.4529, 11.0768);
	private static final Coordinate BERLIN = new Coordinate(52.5189, 13.4024);

	@Test(expected=IllegalArgumentException.class)
	public void testCoordinateInvalid1() {
		Coordinate invalid = new Coordinate(-181, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCoordinateInvalid2() {
		Coordinate invalid = new Coordinate(0, 181);
	}

	/**
	 * Test accessing a Coordinate from a Location
	 */
	@Test
	public void testLocationAccess() {
		Location newLocation = new Location(NORTH_POLE);

		assertEquals(90, newLocation.getCoordinate().getLatitude(), 0);
		assertEquals(0, newLocation.getCoordinate().getLongitude(), 0);
	}

	/**
	 * North Pole to South Pole: 1/2 circumference (= PI * EARTH_RADIUS)
	 */
	@Test
	public void testDistanceNS() {
		assertEquals(Math.PI * Coordinate.EARTH_RADIUS, NORTH_POLE.getDistance(SOUTH_POLE), 1);
	}

	/**
	 * North Pole to North Pole: Distance is zero
	 */
	@Test
	public void testDistanceNN() {
		assertEquals(0, NORTH_POLE.getDistance(NORTH_POLE), 0);
	}

	/**
	 * Nuremberg to Berlin: ~ 378 km
	 */
	@Test
	public void testDistanceNBG_BER() {
		assertEquals(378, NUREMBERG.getDistance(BERLIN), 5);
	}

}
