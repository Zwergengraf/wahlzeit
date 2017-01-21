package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.AssertUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
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

	public void addSubType(LandscapeType landscapeType) {
		AssertUtil.assertObjectNotNull(landscapeType, "landscapeType");

		if(subTypes.contains(landscapeType)) {
			return;
		}

		landscapeType.setSuperType(this);
		subTypes.add(landscapeType);
	}

	public void removeSubType(LandscapeType landscapeType) {
		AssertUtil.assertObjectNotNull(landscapeType, "landscapeType");
		subTypes.remove(landscapeType);
	}

	public void setSuperType(LandscapeType landscapeType) {
		AssertUtil.assertObjectNotNull(landscapeType, "landscapeType");

		if(landscapeType.equals(this.superType)) {
			return;
		}

		// When changing the superType we need to 'de-register' this LandscapeType
		if(this.superType != null) {
			this.superType.removeSubType(this);
		}

		this.superType = landscapeType;
		landscapeType.addSubType(this);
	}

	public boolean isSubtype(LandscapeType kt){
		LandscapeType cur = this;
		do {
			if(cur == kt){
				return true;
			}
			cur = cur.getSuperType();
		} while(cur != null);

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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LandscapeType that = (LandscapeType) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(countryCodes, that.countryCodes) &&
				Objects.equals(aspects, that.aspects);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, countryCodes, aspects);
	}
}
