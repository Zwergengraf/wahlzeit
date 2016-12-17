package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeit.utils.AssertUtil;

public class ObjectCacheTest {

	@Test
	public void testObjectCreation() {
		CartesianCoordinate newCoordinate = CartesianCoordinate.getInstance(1, 1, 1);
		AssertUtil.assertObjectNotNull(newCoordinate, "newCoordinate");
	}

	@Test
	public void testObjectLookup() {
		CartesianCoordinate newCoordinate = CartesianCoordinate.getInstance(1, 2, 3);
		AssertUtil.assertObjectNotNull(newCoordinate, "newCoordinate");

		int before = CartesianCoordinate.getCachedObjectCount();
		CartesianCoordinate duplicateCoordinate = CartesianCoordinate.getInstance(1, 2, 3);
		int after = CartesianCoordinate.getCachedObjectCount();

		// Object count must be unchanged
		assert(before == after);

		// Assert that th
		assert(duplicateCoordinate.hashCode() == newCoordinate.hashCode());
		assert(duplicateCoordinate.equals(newCoordinate));
		assert(duplicateCoordinate == newCoordinate);
	}
}
