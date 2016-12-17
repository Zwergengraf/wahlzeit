package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link Location}.
 */
public class LocationTest {

	private CartesianCoordinate testCartesianCoordinate;

	@Before
	public void setUp() {
		testCartesianCoordinate = CartesianCoordinate.getInstance(0, 0, 0);
	}

	@Test
	public void testCartesianCoordinateNotNull() {
		Location newLocation = new Location(testCartesianCoordinate);

		assertNotNull(newLocation.getCoordinate());
	}

}
