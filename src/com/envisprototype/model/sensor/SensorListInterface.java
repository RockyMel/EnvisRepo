package com.envisprototype.model.sensor;

import java.util.List;

public interface SensorListInterface {

	public abstract void addSensor(SensorInterface sensor);

	public abstract void removeSensor(SensorInterface sensor);

	public abstract SensorInterface findSensorById(String Id);

	public abstract List<SensorInterface> getSensorList();

	boolean isSensorListEmpty();

	List getSensorListBySetID(String ID);
	
	List getSensorListByType(int type);
	

}