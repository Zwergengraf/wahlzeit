package org.wahlzeit.model;

import com.google.appengine.api.images.Image;

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

	/**
	 *
	 */
	public LandscapePhoto createPhoto(String filename, Image uploadedImage) throws Exception {
		PhotoId id = PhotoId.getNextId();
		LandscapePhoto photo = LandscapePhotoUtil.createPhoto(filename, id, uploadedImage);
		addPhoto(photo);
		return photo;
	}
}
