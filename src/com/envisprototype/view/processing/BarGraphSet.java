package com.envisprototype.view.processing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.envisprototype.controller.RealTimeThreeDVis;
import com.envisprototype.model.processing.Coords;


/* collection of bar graphs for one set */
public class BarGraphSet extends AbstractEnvisButton {

	private EnvisPApplet p;
	ArrayList<BarGraph> barGraphList;
	ArrayList<Coords> graphCoords;
	int midpoint_x, midpoint_y;
	boolean hover = false;
	boolean selected = false;
	boolean secondClick = false;
	PFont font;
	int timeRangeType;
	
	private static int SENSORTYPE_TEMP = 1;
	
	//private ArrayList<Float> readingsList;
//	public ArrayList<Float> getReadingsList() {
//		return readingsList;
//	}
//
//	public void setReadingsList(ArrayList<Float> readingsList) {
//		this.readingsList = readingsList;
//	}



	private String sensorID;

	public String getSensorID() {
		return sensorID;
	}

	public void setSensorID(String sensorID) {
		this.sensorID = sensorID;
	}

	public BarGraphSet(EnvisPApplet epApplet, String name, String sensorID, int timeRangeType) {
		super(epApplet, name);
		p = epApplet;
		this.timeRangeType = timeRangeType;
		font = p.createFont("Arial",14,true);
		midpoint_x = p.displayWidth/2;
		midpoint_y = p.displayHeight/2;

		
		barGraphList = new ArrayList<BarGraph>();
		//graphCoords = new ArrayList<Coords>();
		this.sensorID = sensorID;
		
//		readingsList = new ArrayList<Float>();
//		readingsList.add(100.0f);
//		readingsList.add((float)24.6);
//		readingsList.add((float)88.4);
		
		/* timeRangeType == 1 is for real time */
		if (timeRangeType == 1) {
			//barGraphList.add(new BarGraph(p, readingsList.get(0), SENSORTYPE_TEMP));
			BarGraph toAdd = new BarGraph(p, 0, SENSORTYPE_TEMP);
			barGraphList.add(toAdd);
			//toAdd.setReading(ThreeDVis.getSensorReadings().get(sensorID));
			addGeneratedCoors();
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
	
	public void addGeneratedCoors(){
		graphCoords = new ArrayList<Coords>();
		graphCoords.add(generateCoords(new Coords(p.getEnvisSensors().get(sensorID).getX(),
				p.getEnvisSensors().get(sensorID).getY(), p.getEnvisSensors().get(sensorID).getZ())));
	}
	
	public Coords generateCoords(Coords sensorCoords) {
		Coords newCoords = null;
		float tempx;
		float tempy;
		float xTocheck, yTocheck;
		int size;
		boolean ifOverlaps;
		do{
			ifOverlaps = false;
			tempx = 0;
			tempy = 0;
			
	      /*generating and storing coordinate values*/
			
			tempx = sensorCoords.getX();
			tempy = sensorCoords.getY();
			Log.i("coords", tempx + " " + tempy);
			tempx = (int)p.random(tempx-p.width/10, tempx+p.width/10);
			tempy = (int)p.random(tempy-p.height/10, tempy+p.height/10);
			
			Log.i("coords", tempx + " " + tempy);
		    
		    newCoords = new Coords((int)tempx, (int)tempy, p.getEnvisMap().getCOOR_Z()+p.height/50);
		    for(BarGraphSet setToCheck: p.getBarGraphSetList()){
				
			    size = p.getBarGraphSetList().size();
			    if(size > 1 && setToCheck != this){
			    	xTocheck = setToCheck.getBarGraphCoords(0).getX();
			    	yTocheck = setToCheck.getBarGraphCoords(0).getY();
			    	if(PApplet.abs(xTocheck-newCoords.getX())<=barGraphList.get(0).getWidth()
				    		&& PApplet.abs(yTocheck-newCoords.getY())<=barGraphList.get(0).getWidth()){
				    	ifOverlaps = true;
				    }
			    }
		    }
		    }while(ifOverlaps);
		
		
		
	    return newCoords;
	}
	
	private void changeFirstCoord(int x, int y){
		if (timeRangeType == 1) {
			// real-time
			
			int z = (int) graphCoords.get(0).getZ();
			graphCoords.get(0).setCoords(x, y, z);
		}
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
			
//			p.translate(p.getEnvisSensors().get(sensorID).getX(), 
//					p.getEnvisSensors().get(sensorID).getY(), p.getEnvisSensors().get(sensorID).getZ());
//			p.translate(graphCoords.get(j).getX(), graphCoords.get(j).getY(), graphCoords.get(j).getZ());
//			p.line(p.getEnvisSensors().get(sensorID).getX(), barGraphList.get(j).getReading(), p.getEnvisSensors().get(sensorID).getZ(), 
//					-graphCoords.get(j).getX(), -graphCoords.get(j).getY(), 
//					-graphCoords.get(j).getZ());
//			p.line(p.getEnvisSensors().get(sensorID).getX(), barGraphList.get(j).getReading(), p.getEnvisSensors().get(sensorID).getZ(), 
//					-p.getEnvisSensors().get(sensorID).getX(), -p.getEnvisSensors().get(sensorID).getY(), p.getEnvisSensors().get(sensorID).getZ());
			p.line(p.getEnvisSensors().get(sensorID).getX(), p.getEnvisSensors().get(sensorID).getY(), p.getEnvisSensors().get(sensorID).getZ(), 
					graphCoords.get(j).getX(), graphCoords.get(j).getY(), graphCoords.get(j).getZ());
			p.pushMatrix();
			p.translate(graphCoords.get(j).getX(), graphCoords.get(j).getY(), graphCoords.get(j).getZ());
			p.fill(255,255,0);
			p.textFont(font);
			p.text(sensorID, 0,	0,barGraphList.get(j).getHeight());
			//barGraphList.get(j).setReading(readingsList.get(0));
			//hover(j);
			//barGraphList.get(j).setReading(readingsList.get(0));
			//barGraphList.get(j).setReading(ThreeDVis.getSensorReadings().get(sensorID));
			barGraphList.get(j).display();
			p.popMatrix();
//			p.translate(-p.getEnvisSensors().get(sensorID).getX(), 
//					-p.getEnvisSensors().get(sensorID).getY(), -p.getEnvisSensors().get(sensorID).getZ());

		}
	}
	
	public ArrayList<Coords> getGraphCoords() {
		return graphCoords;
	}

	public void setGraphCoords(ArrayList<Coords> graphCoords) {
		this.graphCoords = graphCoords;
	}

	@Override
	public void setPlace(int defX, int defY) {
		// TODO Auto-generated method stub
		super.setPlace(defX, defY);
		changeFirstCoord(defX, defY);
		
	}
	
//	public boolean hover(int i) {
//	float tempx = graphCoords.get(i).getX() + (p.displayWidth/2);
//	float tempy = graphCoords.get(i).getY() + (p.displayHeight/2);
//	
//
//    p.text(tempx + "-" + tempy, 300, 300, 0);
//	
//	/* mouse hovers over a bar graph */ 
//		if ((tempx - p.mouseX < 300) && (tempx - p.mouseX > -300) && 
//				  (tempy - p.mouseY < 300) && 
//				  (tempy - p.mouseY > -300)) {
//	        hover = true; 
//	        barGraphList.get(i).setHover(hover);
//	        barGraphList.get(i).setSelected(true);
//	        
////	        if (p.mousePressed) {
////	        	selected = true;
////	        	barGraphList.get(i).setSelected(selected);
////	          
////	        }
//	        
//	      } 
//	    
//	    else {
//	      hover = false;
//	      barGraphList.get(i).setHover(hover);
//	      
////	      /* click on new location for bar graph */
////	      if (p.mousePressed) {
////
////		        if (barGraphList.get(i).isSelected()) {
////		        	float newPos_x = p.mouseX - ((p.displayWidth/2) + p.getEnvisSensors().get(sensorID).getX());
////		        	float newPos_y = p.mouseY - ((p.displayHeight/2) + p.getEnvisSensors().get(sensorID).getY());
////		        	
////		        	graphCoords.get(i).setCoords((int)newPos_x, (int)newPos_y, 0);
////		        	
////		        	selected = false;
////		        	barGraphList.get(i).setSelected(selected);
////		        }
////	      }
//		    }
//		return hover;
//	
//	}

	@Override
	public void rotate(float xRotate, float yRotate, float zRotate) {
		// TODO Auto-generated method stub
		
	}
	

}
