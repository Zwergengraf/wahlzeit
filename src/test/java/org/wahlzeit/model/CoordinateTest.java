package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

	private Coordinate northPole = new Coordinate(90, 0);
	private Coordinate southPole = new Coordinate(-90, 0);

	private Coordinate nuremberg = new Coordinate(49.4529, 11.0768);
	private Coordinate berlin = new Coordinate(52.5189, 13.4024);

	@Test(expected=IllegalArgumentException.class)
	public void testCoordinateInvalid1() {
		Coordinate invalid = new Coordinate(-181, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCoordinateInvalid2() {
		Coordinate invalid = new Coordinate(0, 181);
	}

	/**
	 * North Pole to South Pole: 1/2 circumference (= PI * EARTH_RADIUS)
	 */
	@Test
	public void testDistanceNS() {
		assertEquals(Math.PI * Coordinate.EARTH_RADIUS, northPole.getDistance(southPole), 1);
	}

	/**
	 * North Pole to North Pole: Distance is zero
	 */
	@Test
	public void testDistanceNN() {
		assertEquals(0, northPole.getDistance(northPole), 0);
	}

	/**
	 * Nuremberg to Berlin: ~ 378 km
	 */
	@Test
	public void testDistanceNBG_BER() {
		assertEquals(378, nuremberg.getDistance(berlin), 5);
	}

}
