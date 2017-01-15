package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.utils.AssertUtil;

/**
 * A landscape photo represents a user-provided (uploaded) photo.
 */
@Subclass(index = true)
public class LandscapePhoto extends Photo {

	@Ignore
	private PhotoManager manager = LandscapePhotoManager.getInstance();

	@Container
	private Landscape landscape;


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
		AssertUtil.assertObjectNotNull(id, "PhotoId");
	}


	public Landscape getLandscape() {
		return landscape;
	}

	public void setLandscape(Landscape landscape) {
		this.landscape = landscape;
	}

}
