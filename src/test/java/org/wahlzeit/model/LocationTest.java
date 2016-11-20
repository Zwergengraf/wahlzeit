package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link Location}.
 */
public class LocationTest {

	private Coordinate testSphericCoordinate;
	private Coordinate testCartesianCoordinate;

	@Before
	public void setUp() {
		testSphericCoordinate = new SphericCoordinate(0, 0);
		testCartesianCoordinate = new CartesianCoordinate(0, 0, 0);
	}

	@Test
	public void testSphericCoordinateNotNull() {
		Location newLocation = new Location(testSphericCoordinate);

		assertNotNull(newLocation.getCoordinate());
	}

	@Test
	public void testCartesianCoordinateNotNull() {
		Location newLocation = new Location(testCartesianCoordinate);

		assertNotNull(newLocation.getCoordinate());
	}

}
