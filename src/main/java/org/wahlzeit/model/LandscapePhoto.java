package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

/**
 * A landscape photo represents a user-provided (uploaded) photo.
 */
@Subclass(index = true)
public class LandscapePhoto extends Photo {

	/**
	 * @methodtype constructor
	 */
	public LandscapePhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public LandscapePhoto(PhotoId myId) {
		super(myId);
	}

}
