package org.wahlzeit.model;


/**
 * Coordinate interface, allows to calculate the distance between two coordinates
 */
public interface Coordinate {

	double getDistance(Coordinate otherCoordinate);

}
