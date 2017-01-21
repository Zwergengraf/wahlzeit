package org.wahlzeit.model;

import org.junit.Before;
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

	private LandscapeType alpsType, montBlancType, matterhornType;

	private String landscapeName = "Alps";
	private String landscapeNameMontBlanc = "Mont Blanc";
	private String landscapeNameMatterhorn = "Matterhorn";

	private Set<String> countryCodes;
	private Set<LandscapeType.Aspect> aspects;

	/**
	 * ClassRule to initialize Google AppEngine Datastore
	 */
	@ClassRule
	public static TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	@Before
	public void setUp() {
		// Create country codes set
		countryCodes = new HashSet<>();
		countryCodes.add("FR");
		countryCodes.add("MC");
		countryCodes.add("IT");
		countryCodes.add("CH");
		countryCodes.add("LI");
		countryCodes.add("DE");
		countryCodes.add("AU");
		countryCodes.add("SI");

		// Create aspects set
		aspects = new HashSet<>();
		aspects.add(LandscapeType.Aspect.MOUNTAIN);
		aspects.add(LandscapeType.Aspect.FOREST);

		// Create LandscapeType
		alpsType = LandscapeManager.getLandscapeManager().createLandscapeType(landscapeName, countryCodes, aspects);

		// Create first subtype
		montBlancType = LandscapeManager.getLandscapeManager().createLandscapeType(landscapeNameMontBlanc, countryCodes, aspects);
		montBlancType.setSuperType(alpsType);

		// Create second subtype
		matterhornType = LandscapeManager.getLandscapeManager().createLandscapeType(landscapeNameMatterhorn, countryCodes, aspects);
		matterhornType.setSuperType(alpsType);
	}

	@Test
	public void testGetters() {
		assert(alpsType.getCountryCodes().contains("DE"));
		assert(alpsType.getAspects().contains(LandscapeType.Aspect.MOUNTAIN));
	}

	@Test
	public void testSubTypes() {
		// Assert that both LandscapeTypes are subtypes of alpsType
		assert(montBlancType.isSubtype(alpsType));
		assert(matterhornType.isSubtype(alpsType));
	}

	@Test
	public void testSuperTypeChange() {
		// Create new LandscapeType
		LandscapeType newSuperType = LandscapeManager.getLandscapeManager().createLandscapeType("New SuperType", new HashSet<String>(), new HashSet<LandscapeType.Aspect>());
		montBlancType.setSuperType(newSuperType);

		assert(montBlancType.isSubtype(newSuperType));
		assert(matterhornType.isSubtype(alpsType));
	}

	@Test
	public void testLandscapeTypeInstanceSharing() {
		// Create LandscapeType
		LandscapeType duplicate = LandscapeManager.getLandscapeManager().createLandscapeType(landscapeName, countryCodes, aspects);

		// Identical values -> both LandscapeTypes should reference the same object
		assert(duplicate == alpsType);
	}
}
