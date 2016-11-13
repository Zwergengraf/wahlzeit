package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.persistence.*;

/**
 * Contains all model unit tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		DatastoreAdapterTest.class,
		GcsAdapterTest.class,

		AccessRightsTest.class,
		CoordinateTest.class,
		FlagReasonTest.class,
		GenderTest.class,
		GuestTest.class,
		LocationTest.class,
		PhotoFilterTest.class,
		TagsTest.class,
		UserStatusTest.class,
		ValueTest.class,

		LandscapePhotoFactoryTest.class,
		LandscapePhotoManagerTest.class,
		LandscapePhotoTest.class
})

public class ModelTestSuite {
}