package com.envisprototype.model.sensorSetAssosiation;

import java.util.HashMap;

import com.envisprototype.model.processing.SetCoordinates;

public class SensorSetAssosiationModel {
	
	private static SensorSetAssosiationModel singletoneInstance;
	
	HashMap<String, SetCoordinates> position;
	HashMap<String, String> association;
	
	private SensorSetAssosiationModel(){
		this.position = new HashMap<String, SetCoordinates>();
		this.association = new HashMap<String, String>();
	}

	public static SensorSetAssosiationModel getSingletoneInstance() {
		return singletoneInstance;
	}

	public static void setSingletoneInstance(
			SensorSetAssosiationModel singletoneInstance) {
		SensorSetAssosiationModel.singletoneInstance = singletoneInstance;
	}

	public HashMap<String, SetCoordinates> getPosition() {
		return position;
	}

	public void setPosition(HashMap<String, SetCoordinates> position) {
		this.position = position;
	}

	public HashMap<String, String> getAssociation() {
		return association;
	}

	public void setAssociation(HashMap<String, String> association) {
		this.association = association;
	}

	public String findAssociatedSetForSensorId(String sensorId){
		return association.get(sensorId);
	}
	public SetCoordinates findPositionBySensorId(String sensorId){
		return position.get(sensorId);
	}
}
