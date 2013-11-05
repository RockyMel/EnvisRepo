package com.envisprototype.view.processing;

import com.envisprototype.model.sensor.SensorListModel;

import android.util.Log;


public class SphereGraphSet {
	
	private EnvisPApplet p;
	private String sensorID;
	public String getSensorID() {
		return sensorID;
	}

	public void setSensorID(String sensorID) {
		this.sensorID = sensorID;
	}

	SphereGraph oneSphereGraph;
	//ArrayList<Float> readingsList;
	private int sensorType;
	
	private static int SENSORTYPE_TEMP = 1;
	
	public SphereGraphSet(EnvisPApplet parent, String name, String sensorID, int sensorType) {
		p = parent;
		
		this.sensorType = sensorType;
		
		this.sensorID = sensorID;
		p.getEnvisSensors().get(sensorID);
		
		//readingsList = new ArrayList<Float>();
		//readingsList.add(ThreeDVis.getSensorReadings().get(sensorID));
		sensorType = SensorListModel.getSingletonInstance().findSensorById(sensorID).getType();
		oneSphereGraph = new SphereGraph(sensorID,p, 0, sensorType);
		
	}
	
	public void drawMe() {
		
		p.pushMatrix();
		p.translate(p.getEnvisSensors().get(sensorID).getX(), p.getEnvisSensors().get(sensorID).getY(), p.getEnvisSensors().get(sensorID).getZ());
		oneSphereGraph.display();
		p.popMatrix();
		
	}
	
	public void setReadingForSphere(Float reading){
		if(reading != null){
			this.oneSphereGraph.setReading(reading);
		}
	}

}
