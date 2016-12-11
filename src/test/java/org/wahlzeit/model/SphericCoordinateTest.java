package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.*;
import static org.wahlzeit.model.SphericCoordinate.EARTH_RADIUS_KM;

/**
 * All test cases of the class {@link SphericCoordinate}.
 */
public class SphericCoordinateTest {

	private Coordinate northPole;
	private Coordinate southPole;

	private Coordinate nuremberg;
	private Coordinate berlin;

	@Before
	public void setUp() {
		northPole = new SphericCoordinate(90, 0);
		southPole = new SphericCoordinate(-90, 0);

		nuremberg = new SphericCoordinate(49.4529, 11.0768);
		berlin = new SphericCoordinate(52.5189, 13.4024);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCoordinateInvalid1() {
		new SphericCoordinate(-181, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCoordinateInvalid2() {
		new SphericCoordinate(0, 181);
	}

	/**
	 * Spheric - North Pole to South Pole: 1/2 circumference (= PI * EARTH_RADIUS_KM)
	 */
	@Test
	public void testSphericDistanceNS() {
		assertEquals(Math.PI * EARTH_RADIUS_KM, northPole.getDistance(southPole), 1);
	}

	/**
	 * Spheric - North Pole to North Pole: Distance is zero
	 */
	@Test
	public void testSphericDistanceNN() {
		assertEquals(0, northPole.getDistance(northPole), 0);
	}

	/**
	 * Spheric - Nuremberg to Berlin: ~ 378 km
	 */
	@Test
	public void testSphericDistanceNBG_BER() {
		assertEquals(378, nuremberg.getDistance(berlin), 5);
	}

	/**
	 * Distance a to b should equal distance b to a
	 */
	@Test
	public void testLocationSwap() {
		assertEquals(nuremberg.getDistance(berlin), berlin.getDistance(nuremberg), 0);
	}

	/**
	 * Test isEqual() method, should succeed
	 */
	@Test
	public void testIsEqual() {
		assertTrue(nuremberg.isEqual(nuremberg));
	}

	/**
	 * Test isEqual() method, should fail
	 */
	@Test
	public void testFailIsEqual() {
		assertFalse(nuremberg.isEqual(northPole));
	}
}
