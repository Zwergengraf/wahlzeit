package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.utils.AssertUtil;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LandscapeManager extends ObjectManager {

	protected static final LandscapeManager instance = new LandscapeManager();

	private ConcurrentHashMap<Landscape, Landscape> landscapes = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, LandscapeType> landscapeTypes = new ConcurrentHashMap<>();


	/**
	 * @methodtype constructor
	 */
	public LandscapeManager() {
		super();
	}

	/**
	 * @methodtype get
	 */
	public static final LandscapeManager getLandscapeManager() {
		return instance;
	}


	public synchronized LandscapeType createLandscapeType(String landscapeTypeName, Set<String> landscapeTypeCountryCodes, Set<LandscapeType.Aspect> aspects) {
		if(landscapeTypes.containsKey(landscapeTypeName)) {
			return landscapeTypes.get(landscapeTypeName);
		} else {
			LandscapeType newLandscapeType = new LandscapeType(landscapeTypeName, landscapeTypeCountryCodes, aspects);
			landscapeTypes.put(landscapeTypeName, newLandscapeType);
			return newLandscapeType;
		}
	}

	public synchronized Landscape createLandscape(LandscapeType landscapeType, Landscape.Season season) {
		AssertUtil.assertObjectNotNull(landscapeType, "landscapeType");
		AssertUtil.assertObjectNotNull(season, "season");
		assertValidLandscapeTypeName(landscapeType.getName());

		LandscapeType type = getLandscapeType(landscapeType.getName());
		Landscape landscape = type.createLandscape(season);

		landscapes.put(landscape, landscape);
		return landscapes.get(landscape);
	}

	public LandscapeType getLandscapeType(String name) {
		return landscapeTypes.get(name);
	}

	private void assertValidLandscapeTypeName(String name) {
		if(!landscapeTypes.containsKey(name)) {
			throw new IllegalArgumentException("Invalid LandscapeType name");
		}
	}

}
