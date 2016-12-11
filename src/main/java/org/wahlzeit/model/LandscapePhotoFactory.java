package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.AssertUtil;

import java.util.logging.Logger;

/**
 * A Factory for creating landscape photos and related objects.
 */
public class LandscapePhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(LandscapePhotoFactory.class.getName());

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static PhotoFactory instance = null;

	/**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting LandscapePhotoFactory").toString());
			setInstance(new LandscapePhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of LandscapePhotoFactory.
	 */
	protected static synchronized void setInstance(PhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new LandscapePhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 * @methodtype factory
	 */
	public Photo createPhoto(PhotoId id) {
		AssertUtil.assertObjectNotNull(id, "PhotoId");
		return new LandscapePhoto(id);
	}
}
