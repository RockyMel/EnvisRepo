package com.envisprototype.model.sensor;

import android.location.Location;

public interface SensorInterface {

	public abstract String getType();

	public abstract void setType(String type);

	public abstract String getNotes();

	public abstract void setNotes(String notes);

	public abstract String getState();

	public abstract void setState(String state);

	public abstract String getName();
	
	public abstract void setName(String name);

	public abstract Location getLocation();

	public abstract void setLocation(Location location);

	public abstract String getId();

	public abstract void setId(String id);
	
	public abstract String getBrand();

	public abstract void setBrand(String brand);
	
	public abstract String getSetid();
	
	public abstract void setSetid(String setid);
}