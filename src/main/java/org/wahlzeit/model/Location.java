package org.wahlzeit.model;

public class Location {

	private Coordinate coordinate;

	public Location(Coordinate coordinate) {
		if(coordinate == null) {
			throw new NullPointerException("coordinate is null.");
		}

		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

}
