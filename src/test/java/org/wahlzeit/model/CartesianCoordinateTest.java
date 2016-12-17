package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link CartesianCoordinate}.
 */
public class CartesianCoordinateTest {

	@Test
	public void testGetters() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(1, 2, 3);
		assertEquals(1, c1.getX(), 0);
		assertEquals(2, c1.getY(), 0);
		assertEquals(3, c1.getZ(), 0);
	}

	@Test
	public void testSimpleCartesianDistance() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(0, 0, 0);
		CartesianCoordinate c2 = CartesianCoordinate.getInstance(2, 0, 0);
		assertEquals(2, c1.getDistance(c2), 0);
	}

	@Test
	public void testHashAndEquals() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(1, 2, 3);
		CartesianCoordinate c2 = CartesianCoordinate.getInstance(1, 2, 3);
		assert(c1.hashCode() == c2.hashCode());
		assert(c1.equals(c2));
	}

}
