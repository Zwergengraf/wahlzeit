package org.wahlzeit.model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {

	private Coordinate northPole;

	@Before
	public void setUp() {
		northPole = new SphericCoordinate(90, 0);
	}

	/**
	 * Test accessing a Coordinate from a Location
	 */
	@Test
	public void testLocationAccess() {
		Location newLocation = new Location(northPole);
		SphericCoordinate sc1 = (SphericCoordinate) newLocation.getCoordinate();

		assertEquals(90, sc1.getLatitude(), 0);
		assertEquals(0,  sc1.getLongitude(), 0);
	}



}
