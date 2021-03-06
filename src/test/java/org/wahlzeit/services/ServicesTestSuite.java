package org.wahlzeit.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.services.mailing.EmailServiceTest;

/**
 * Contains all services unit tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		EmailServiceTest.class,

		EmailAddressTest.class,
		LogBuilderTest.class
})

public class ServicesTestSuite {
}