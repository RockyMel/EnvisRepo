package com.envisprototype.model.set;

import android.location.Location;

public interface SetInterface {

	public abstract String getId();

	public abstract void setId(String id);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getNotes();

	public abstract void setNotes(String notes);

	public abstract Location getLocation();

	public abstract void setLocation(Location location);
	public abstract boolean isIftoPlot();
	public abstract void setIftoPlot(boolean iftoPlot);
	
	public abstract float getX();
	public abstract void setX(float x);
	public abstract float getY();
	public abstract void setY(float y);
	public abstract float getZ();
	public abstract void setZ(float z);

	//public abstract List<SensorInterface> getSensors();
	
	//public abstract void setSensors(List<SensorInterface> sensors);
	
//	public abstract SensorInterface findSensorById(String id);
//	
//	public abstract void addSensor(SensorInterface sensor);
//	
//	public abstract void removeSensor(SensorInterface sensor);
//
//	public abstract boolean searchSensorById(String id);
//	
//	public abstract SensorInterface getSensor(String id);

	
	
	
}