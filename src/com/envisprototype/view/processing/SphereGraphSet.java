package com.envisprototype.view.processing;


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
		oneSphereGraph = new SphereGraph(p, 0, SENSORTYPE_TEMP);
		
	}
	
	public void drawMe() {
		
		p.pushMatrix();
		p.translate(p.getEnvisSensors().get(sensorID).getX(), p.getEnvisSensors().get(sensorID).getY(), p.getEnvisSensors().get(sensorID).getZ());
		oneSphereGraph.display();
		p.popMatrix();
		
	}
	
	public void setReadingForSphere(float reading){
		this.oneSphereGraph.setReading(reading);
	}

}
