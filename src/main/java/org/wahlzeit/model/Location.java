package org.wahlzeit.model;

import org.wahlzeit.utils.AssertUtil;

/**
 * Location class
 */
public class Location {

	private CartesianCoordinate coordinate;

	public Location(CartesianCoordinate coordinate) {
		if(coordinate == null) {
			throw new NullPointerException("coordinate is null.");
		}

		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		AssertUtil.assertObjectNotNull(coordinate, "coordinate");
		return coordinate;
	}

}
