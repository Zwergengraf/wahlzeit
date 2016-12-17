package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link CartesianCoordinate}.
 */
public class CartesianCoordinateTest {

	private CartesianCoordinate nuremberg;

	@Before
	public void setUp() {
		nuremberg = CartesianCoordinate.getInstance(1, 2, 3);
	}

	@Test
	public void testGetters() {
		assertEquals(1, nuremberg.getX(), 0);
		assertEquals(2, nuremberg.getY(), 0);
		assertEquals(3, nuremberg.getZ(), 0);
	}

	@Test
	public void testSimpleCartesianDistance() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(0, 0, 0);
		CartesianCoordinate c2 = CartesianCoordinate.getInstance(2, 0, 0);
		assertEquals(2, c1.getDistance(c2), 0);
	}

}
