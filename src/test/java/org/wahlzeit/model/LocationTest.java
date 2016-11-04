package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * The type Location test.
 */
public class LocationTest {

	private Coordinate testCoordinate;

	@Before
	public void setUp() {
		testCoordinate = new Coordinate(0, 0);
	}

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
