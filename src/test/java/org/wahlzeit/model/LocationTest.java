package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link Location}.
 */
public class LocationTest {

	private Coordinate testCoordinate;

	@Before
	public void setUp() {
		testCoordinate = new Coordinate(0, 0);
	}

	@Test
	public void testCoordinateNotNull() {
		Location newLocation = new Location(testCoordinate);

		assertNotNull(newLocation.getCoordinate());
	}

}
