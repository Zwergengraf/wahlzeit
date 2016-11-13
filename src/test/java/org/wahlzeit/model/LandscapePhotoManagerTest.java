package org.wahlzeit.model;

import org.junit.*;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import java.io.IOException;
import static org.junit.Assert.*;

/**
 * Test class for {@link LandscapePhotoManager}.
 */
public class LandscapePhotoManagerTest {

	private static final int PHOTOID = 4711;

	/**
	 * ClassRule to initialize Google AppEngine Datastore
	 */
	@ClassRule
	public static TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	@Test
	public void saveAndLoadPhoto() throws IOException {
		PhotoId id = new PhotoId(PHOTOID);
		LandscapePhoto landscapePhoto = new LandscapePhoto(id);
		LandscapePhotoManager.getInstance().addPhoto(landscapePhoto);

		assertEquals(LandscapePhotoManager.getInstance().getPhoto(id), landscapePhoto);
	}
}
