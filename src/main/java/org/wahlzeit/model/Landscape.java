package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.services.DataObject;

import java.util.Objects;
import java.util.Set;

@Subclass(index = true)
public class Landscape extends DataObject {

	public enum Season {
		UNKNOWN, SPRING, SUMMER, AUTUMN, WINTER
	}


	private LandscapeType landscapeType;
	private Season season;
	// TODO: Important - when adding new private variables, make sure to add them to hashCode()!

	@Ignore
	private LandscapeManager manager = LandscapeManager.getLandscapeManager();


	// Private constructor to avoid direct instantiation (because every Landscape needs a LandscapeType)
	private Landscape() {}

	public Landscape(LandscapeType landscapeType, Season season) {
		this.landscapeType = landscapeType;
		this.season = season;
	}

	public LandscapeType getLandscapeType() {
		return landscapeType;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public String getLandscapeTypeName() {
		return landscapeType.getName();
	}

	public Set<String> getLandscapeTypeCountryCodes() {
		return landscapeType.getCountryCodes();
	}

	public Set<LandscapeType.Aspect> getAspects() {
		return landscapeType.getAspects();
	}

	public String getId() {
		return Integer.toString(hashCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.landscapeType.getName(), this.season);
	}

}
