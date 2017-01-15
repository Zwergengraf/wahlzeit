package org.wahlzeit.model;

import java.util.logging.Logger;

/**
 * A landscape photo manager provides access to and manages photos.
 */
public class LandscapePhotoManager extends PhotoManager {

	protected static final PhotoManager instance = new LandscapePhotoManager();

	private static final Logger log = Logger.getLogger(LandscapePhotoManager.class.getName());

	/**
	 * @methodtype constructor
	 */
	public LandscapePhotoManager() {
		super();
	}

	/**
	 * @methodtype get
	 */
	public static final PhotoManager getLandscapePhotoManager() {
		return instance;
	}

}
