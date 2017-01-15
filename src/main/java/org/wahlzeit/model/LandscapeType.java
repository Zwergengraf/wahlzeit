package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.AssertUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A LandscapeType describes a geographical location or a type of locations, including its name, country and aspects
 */
@Subclass(index = true)
public class LandscapeType extends DataObject {

	public enum Aspect {
		MOUNTAIN, HILLS, FOREST, BEACH, SEA
	}

	private String name;
	private Set<String> countryCodes;
	private Set<Aspect> aspects;


	protected LandscapeType superType = null;
	protected Set<LandscapeType> subTypes = new HashSet<>();

	public LandscapeType getSuperType() {
		return superType;
	}

	public Iterator<LandscapeType> getSubTypeIterator() {
		return subTypes.iterator();
	}

	public void addSubType(LandscapeType ft) {
		assert (ft != null) : "tried to set null sub-type";
		ft.setSuperType(this);
		subTypes.add(ft);
	}

	public void setSuperType(LandscapeType landscapeType) {
		AssertUtil.assertObjectNotNull(landscapeType, "landscapeType");

		this.superType = landscapeType;
		superType.subTypes.add(landscapeType);
	}

	public boolean hasInstance(Landscape landscape) {
		AssertUtil.assertObjectNotNull(landscape, "landscape");

		if (landscape.getLandscapeType() == this) {
			return true;
		}
		for (LandscapeType type : subTypes) {
			if (type.hasInstance(landscape)) {
				return true;
			}
		}
		return false;
	}


	public LandscapeType(String name, Set<String> countryCodes, Set<Aspect> aspects) {
		AssertUtil.assertObjectNotNull(name, "name");
		AssertUtil.assertObjectNotNull(countryCodes, "countryCodes");
		AssertUtil.assertObjectNotNull(aspects, "aspects");

		this.name = name;
		this.countryCodes = countryCodes;
		this.aspects = aspects;
	}

	public Landscape createLandscape(Landscape.Season season) {
		return new Landscape(this, season);
	}

	public String getName() {
		return name;
	}

	public Set<String> getCountryCodes() {
		return countryCodes;
	}

	public Set<Aspect> getAspects() {
		return aspects;
	}

}
