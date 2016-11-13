package org.wahlzeit.model;

import org.junit.*;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;

/**
 * Test class for {@link LandscapePhoto}.
 */
public class LandscapePhotoTest {

	private static final int PHOTOID = 4711;

	/**
	 * ClassRule to initialize Google AppEngine Datastore
	 */
	@ClassRule
	public static TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	@Test
	public void createLandscapePhotoWithId() {
		LandscapePhoto photo = new LandscapePhoto(new PhotoId(PHOTOID));
		assertEquals(photo.getId().asInt(), PHOTOID);
	}
}
