package com.envisprototype.model.maps;

import java.util.HashMap;

import com.envisprototype.model.processing.Coordinates;

import android.location.Location;

public interface MapInterface {

	public abstract String getName();
	public abstract void setName(String name);
	public abstract Location getLocation();
	public abstract void setLocation(Location location);
	public abstract String getId();
	public abstract void setId(String id);
	public abstract void addRealCoordinates(Float x,Float y);
	public abstract void removeRealCoordinates(int nodeToRemove);
	public abstract void shiftNodes(int index);
	public abstract Coordinates getRealCoordinates();
	public abstract void setRealCoordinates(Coordinates realCoordinates);
	public abstract Float getzCoordinate();
	public abstract void setzCoordinate(Float zCoordinate);
	public abstract void addSetToMap(String SetID,SetCoordinate xyz);
	public abstract void removeSetFromMap(String SetID);
	public abstract String getNotes();
	public abstract void setNotes(String notes);


}