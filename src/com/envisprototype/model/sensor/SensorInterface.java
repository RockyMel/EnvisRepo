package com.envisprototype.model.sensor;

import com.envisprototype.model.processing.SetCoordinates;

import android.location.Location;

public interface SensorInterface {

	public abstract int getType();

	public abstract void setType(int type);

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

	public abstract float getX();
	
	public abstract void setX(float x);
	
	public abstract float getY();
	
	public abstract void setY(float y);
	
	public abstract float getZ();
	
	public abstract void setZ(float z);
	
	public abstract boolean isIfDefaultCoors();
	
	public abstract void setIfDefaultCoors(boolean ifDefaultCoors);

	boolean isIftoPlot();

	void setIftoPlot(boolean iftoPlot);
	
}