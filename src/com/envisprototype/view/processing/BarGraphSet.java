package com.envisprototype.view.processing;

import java.util.ArrayList;

import processing.core.PApplet;


/* collection of bar graphs for one set */
public class BarGraphSet extends AbstractEnvisButton {

	private EnvisPApplet p;
	ArrayList<BarGraph> barGraphList;
	ArrayList<Coords> graphCoords;
	int midpoint_x, midpoint_y;
	boolean hover = false;
	boolean selected = false;
	boolean secondClick = false;
	
	private static int SENSORTYPE_TEMP = 1;
	
	ArrayList<Float> readingsList;
	private String sensorID;

	public BarGraphSet(EnvisPApplet epApplet, String name, String sensorID, int timeRangeType) {
		super(epApplet, name);
		p = epApplet;
		midpoint_x = p.displayWidth/2;
		midpoint_y = p.displayHeight/2;
		
		barGraphList = new ArrayList<BarGraph>();
		graphCoords = new ArrayList<Coords>();
		this.sensorID = sensorID;
		
		readingsList = new ArrayList<Float>();
		readingsList.add((float) 100.0);
		readingsList.add((float)24.6);
		readingsList.add((float)88.4);
		
		/* timeRangeType == 1 is for real time */
		if (timeRangeType == 1) {
			barGraphList.add(new BarGraph(p, readingsList.get(0), SENSORTYPE_TEMP));
			graphCoords.add(generateCoords(new Coords(p.getEnvisSensors().get(sensorID).getX(),
					p.getEnvisSensors().get(sensorID).getY(), p.getEnvisSensors().get(sensorID).getZ())));
		}
		
		/* timeRangeType == 2 is for historical and requires an array of readings */
		
//		if (timeRangeType == 2) {
//			/*CHANGE TO HASHMAP WITH ITERATOR?*/
//			for (int i = 0; i < readingsList.size(); i++) {
//				barGraphList.add(new BarGraph(p, readingsList.get(1), SENSORTYPE_TEMP));
//			}
//		}
		
		p.getEnvisSensors().get(sensorID);
		
		
	}
	
	public Coords generateCoords(Coords sensorCoords) {
		
		float tempx = 0;
		float tempy = 0;
		
      /*generating and storing coordinate values*/
		    
	    if (sensorCoords.x < midpoint_x)
	    {
	      tempx = (int)p.random(0, midpoint_x);
	      tempx = tempx - (sensorCoords.getX()/2);
	    }
	    else if (sensorCoords.x > midpoint_x)
	    {
	      tempx = (int)p.random(midpoint_x, p.displayWidth);
	      tempx = tempx - (sensorCoords.getX()/2);
	    }
		    
	    if (sensorCoords.getY() < midpoint_y)
	    {
	      tempy = (int)p.random(-p.displayHeight/4, 0);
	      tempy = tempy - (sensorCoords.getY()/2);
	    }
		    
	    else if (sensorCoords.getY() > midpoint_y)
	    {
	      tempy = (int)p.random(0, p.displayHeight/4);
	      tempy = tempy + (sensorCoords.getY()/2);
	    }
	    
	    Coords newCoords = new Coords((int)tempx, (int)tempy, 0);
	    return newCoords;
	}
	
	
	public Coords getBarGraphCoords(int index) {
		return graphCoords.get(index);
	}
	
	public ArrayList<BarGraph> getBarGraphList() {
		return barGraphList;
	}
	
	@Override
	public void drawMe() {
		for (int j = 0; j < barGraphList.size(); j++) {
			
			p.translate(p.getEnvisSensors().get(sensorID).getX(), 
					p.getEnvisSensors().get(sensorID).getY(), p.getEnvisSensors().get(sensorID).getZ());
			p.translate(graphCoords.get(j).getX(), graphCoords.get(j).getY(), graphCoords.get(j).getZ());
			p.line(p.getEnvisSensors().get(sensorID).getX(), barGraphList.get(j).getReading(), p.getEnvisSensors().get(sensorID).getZ(), 
					-graphCoords.get(j).getX(), -graphCoords.get(j).getY(), 
					-graphCoords.get(j).getZ());
			
			barGraphList.get(j).display();

		}
	}



	@Override
	public void rotate(float xRotate, float yRotate, float zRotate) {
		// TODO Auto-generated method stub
		
	}

}
