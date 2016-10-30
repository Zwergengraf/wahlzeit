package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

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

}
