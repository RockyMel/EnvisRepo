package com.envisprototype.model.sensor;

import java.util.List;

public interface SensorListInterface {

	public abstract void addSensor(SensorInterface sensor);

	public abstract void removeSensor(SensorInterface sensor);

	public abstract SensorInterface findSensorById(String Id);

	public abstract List<SensorInterface> getSensorList();

	boolean isSensorListEmpty();

	List<SensorInterface> getSensorListBySetID(String ID);
	
	List getSensorIDListByType(int type);

	public abstract void setSensorList(List<SensorInterface> thesensor);
	public abstract void ReplicateSensorList();

	public abstract void addAssociateSensortoSet(String sensorID, String setID);

	public abstract void editSensor(SensorInterface sensor);
	public abstract void addAssociateSensortoMap(String setID,
			float x, float y, float z);

}