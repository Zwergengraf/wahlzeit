package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationTest {

	private Coordinate testCoordinate = new Coordinate(47, 11);

	@Test
	public void testConstructor() {
		Location newLocation = new Location(testCoordinate);

		assertNotNull(newLocation);
	}

	@Test
	public void testCoordinateNotNull() {
		Location newLocation = new Location(testCoordinate);

		assertNotNull(newLocation.getCoordinate());
	}

	@Test
	public void testCoordinate() {
		Location newLocation = new Location(testCoordinate);

		assertEquals(47, newLocation.getCoordinate().getLatitude(), 0);
		assertEquals(11, newLocation.getCoordinate().getLongitude(), 0);
	}

}
