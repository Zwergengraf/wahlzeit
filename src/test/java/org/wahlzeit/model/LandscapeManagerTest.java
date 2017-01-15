package org.wahlzeit.model;


import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class LandscapeManagerTest {

	/**
	 * ClassRule to initialize Google AppEngine Datastore
	 */
	@ClassRule
	public static TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	@Test
	public void testCreateLandscapeType() {
		String landscapeName = "Yosemite National Park";

		// Create country codes set
		Set<String> countryCodes = new HashSet<>();
		countryCodes.add("US");

		// Create aspects set
		Set<LandscapeType.Aspect> aspects = new HashSet<>();
		aspects.add(LandscapeType.Aspect.MOUNTAIN);
		aspects.add(LandscapeType.Aspect.FOREST);

		// Create LandscapeType
		LandscapeType yosemiteLandscapeType = LandscapeManager.getLandscapeManager().createLandscapeType(landscapeName, countryCodes, aspects);

		// Create first Landscape with this Type
		Landscape yosemiteSummer = LandscapeManager.getLandscapeManager().createLandscape(yosemiteLandscapeType, Landscape.Season.SUMMER);
		assert(yosemiteSummer.getLandscapeTypeName().equals(landscapeName));
		assert(yosemiteSummer.getLandscapeTypeCountryCodes().equals(countryCodes));

		// Create second Landscape with this Type
		Landscape yosemiteWinter = LandscapeManager.getLandscapeManager().createLandscape(yosemiteLandscapeType, Landscape.Season.WINTER);
		assert(yosemiteWinter.getLandscapeTypeName().equals(landscapeName));
		assert(yosemiteWinter.getLandscapeTypeCountryCodes().equals(countryCodes));

		// Assert that both Landscapes share the same LandscapeType
		assert(yosemiteWinter.getLandscapeType().hashCode() == yosemiteSummer.getLandscapeType().hashCode());

		// Assert that the LandscapeType contains both Landscapes
		assertTrue(yosemiteLandscapeType.hasInstance(yosemiteSummer));
		assertTrue(yosemiteLandscapeType.hasInstance(yosemiteWinter));
	}

	@Test
	public void testSubTypes() {
		String landscapeName = "Alps";
		String landscapeNameMontBlanc = "Mont Blanc";
		String landscapeNameMatterhorn = "Matterhorn";

		// Create country codes set
		Set<String> countryCodes = new HashSet<>();
		countryCodes.add("FR");
		countryCodes.add("MC");
		countryCodes.add("IT");
		countryCodes.add("CH");
		countryCodes.add("LI");
		countryCodes.add("DE");
		countryCodes.add("AU");
		countryCodes.add("SI");

		// Create aspects set
		Set<LandscapeType.Aspect> aspects = new HashSet<>();
		aspects.add(LandscapeType.Aspect.MOUNTAIN);
		aspects.add(LandscapeType.Aspect.FOREST);

		// Create LandscapeType
		LandscapeType alpsType = LandscapeManager.getLandscapeManager().createLandscapeType(landscapeName, countryCodes, aspects);

		// Create first subtype
		LandscapeType montBlancType = LandscapeManager.getLandscapeManager().createLandscapeType(landscapeNameMontBlanc, countryCodes, aspects);
		montBlancType.setSuperType(alpsType);

		// Create second subtype
		LandscapeType matterhornType = LandscapeManager.getLandscapeManager().createLandscapeType(landscapeNameMatterhorn, countryCodes, aspects);
		matterhornType.setSuperType(alpsType);

		// Assert that both LandscapeTypes are subtypes
		assert(montBlancType.getSuperType().getName().equals(landscapeName));
		assert(matterhornType.getSuperType().getName().equals(landscapeName));
	}

}
