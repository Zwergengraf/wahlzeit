package org.wahlzeit.model;

import com.google.appengine.api.images.Image;

import java.util.logging.Logger;

public class LandscapePhotoUtil extends PhotoUtil {

	private static final Logger log = Logger.getLogger(PhotoUtil.class.getName());

	/**
	 * @methodtype creation
	 */
	public static LandscapePhoto createPhoto(String filename, PhotoId id, Image uploadedImage) throws Exception {
		LandscapePhoto photo = LandscapePhotoFactory.getInstance().createPhoto(id);
		photo.setEnding(filename.substring(filename.lastIndexOf(".") + 1));

		createImageFiles(uploadedImage, photo);

		int sourceWidth = uploadedImage.getWidth();
		int sourceHeight = uploadedImage.getHeight();
		photo.setWidthAndHeight(sourceWidth, sourceHeight);

		return photo;
	}
}
