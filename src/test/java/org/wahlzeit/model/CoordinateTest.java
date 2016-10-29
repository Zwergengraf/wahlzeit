package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {

	private Coordinate northPole = new Coordinate(0, 90);
	private Coordinate southPole = new Coordinate(0, 270);

	@Test
	public void testDistanceNS() {
		assertEquals(Coordinate.EARTH_RADIUS, northPole.getDistance(southPole) / Math.PI, 1);
	}

	@Test
	public void testDistanceNN() {
		assertEquals(0, northPole.getDistance(northPole) / Math.PI, 0);
	}

}
