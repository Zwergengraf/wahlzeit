package org.wahlzeit.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Contains all util unit tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		AssertUtilTest.class,
		StringUtilTest.class,
		VersionTest.class
})

public class UtilsTestSuite {
}