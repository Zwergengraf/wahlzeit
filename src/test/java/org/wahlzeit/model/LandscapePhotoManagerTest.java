package org.wahlzeit.model;

import org.junit.*;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import java.io.IOException;
import static org.junit.Assert.*;

/**
 *
 */
public class LandscapePhotoManagerTest {

	/**
	 * ClassRule to initialize Google AppEngine Datastore
	 */
	@ClassRule
	public static TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	@Test
	public void saveAndLoadPhoto() throws IOException {
		PhotoId id = new PhotoId(4711);
		LandscapePhoto landscapePhoto = new LandscapePhoto(id);
		LandscapePhotoManager.getInstance().addPhoto(landscapePhoto);

		assertEquals(LandscapePhotoManager.getInstance().getPhoto(id), landscapePhoto);
	}
}
