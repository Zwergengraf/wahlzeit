package org.wahlzeit.model;


/**
 * Coordinate interface, allows to calculate the distance between two coordinates
 */
public interface Coordinate {

	double getDistance(Coordinate otherCoordinate);

	boolean isEqual(Coordinate otherCoordinate);

	double getX();
	double getY();
	double getZ();

}
