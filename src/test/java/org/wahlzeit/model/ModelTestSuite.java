package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.persistence.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		AbstractAdapterTest.class,
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
		ValueTest.class
})

public class ModelTestSuite {
	// the class remains empty,
	// used only as a holder for the above annotations
}