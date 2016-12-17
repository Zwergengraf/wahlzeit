package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectCache<T> {

	private HashMap<Integer, List<T>> objects = new HashMap<>();

	/**
	 * This method caches the given object if it is not already cached and returns the object
	 * (or a different cached object with the same value).
	 * @param object Object to be cached
	 * @return
	 */
	public synchronized T getObject(T object) {
		if(objects.containsKey(object.hashCode())) {
			// There is at least one object with this hashCode saved
			List<T> existingList = objects.get(object.hashCode());
			for (T possibleObject : existingList)
			{
				if(possibleObject.equals(object)) {
					return possibleObject;
				}
			}

			// Hash collision -> add object to List<T> and return it
			existingList.add(object);
			return object;
		}

		// This object is not yet cached -> cache it and return it
		ArrayList<T> list = new ArrayList<>();
		list.add(object);
		objects.put(object.hashCode(), list);

		return object;
	}

	public int getObjectCount() {
		return objects.size();
	}
}
