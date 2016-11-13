package org.wahlzeit.model;

import org.junit.*;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;

/**
 *
 */
public class LandscapePhotoFactoryTest {

	private static final int PHOTOID = 4711;

	/**
	 * ClassRule to initialize Google AppEngine Datastore
	 */
	@ClassRule
	public static TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	@Test
	public void instantiateLandscapePhoto() {
		assertEquals(LandscapePhotoFactory.getInstance().createPhoto().getClass(), LandscapePhoto.class);
	}

	@Test
	public void instantiateLandscapePhotoWithId() {
		assertEquals(LandscapePhotoFactory.getInstance().createPhoto(new PhotoId(PHOTOID)).getClass(), LandscapePhoto.class);
	}

}
