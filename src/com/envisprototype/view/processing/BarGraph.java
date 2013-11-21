package com.envisprototype.view.processing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import android.util.Log;

import com.envisprototype.controller.HistoricalThreeDController;
import com.envisprototype.controller.RealTimeThreeDVis;
import com.envisprototype.model.processing.SensorReadingsModel;
import com.envisprototype.model.processing.XbeeTagPair;
import com.envisprototype.model.sensor.AbstractSensor;
import com.envisprototype.model.sensor.SensorListModel;

public class BarGraph {
	private EnvisPApplet p;
	private int color_hoverFalse, color_selectedTrue, color_mainFill;
	private int color_barOutline, color_barFill;
	float[] colorRGB = { 0, 0, 0 };
	private int z = 20; // bar graph width or z
	private int x = 20; // bar graph length or x
	private float h, reading;
	private String sensorId;
	int maxValue;
	int minValue;
	Integer idInt;
	private float[][] colorAssigned= {{0,0,0},{0, 255, 238},{255, 136, 0},{255, 238, 0},
			{255, 0, 204},{255, 0, 0},{0, 0, 255},{0, 255, 0}};
	//private TreeMap<String, Float> readingRange = null;
	public void setReading(Float reading) {
		if(reading != null){
			this.reading = reading;
		}else{
			Log.i("comparing dates", sensorId);
			if(HistoricalThreeDController.compareDates(p.curDate,
					SensorReadingsModel.getSingletonInstance().findFirstTimeStampForId(sensorId)) < 0){
				this.reading = 0;
			}
		}
		this.h = PApplet.map(this.reading, minValue, maxValue, 0, 100);
		if(RealTimeThreeDVis.xbeeTokens.size() > 0)
			idInt = RealTimeThreeDVis.xbeeTokens.get(sensorId);
		else{
			//idInt
			ArrayList<XbeeTagPair> pairsForTimeStamp =  HistoricalThreeDController.xbeeTokens.get(EnvisPApplet.curDate);
			if(pairsForTimeStamp != null)
			for(XbeeTagPair pair: pairsForTimeStamp){
				if(pair.getTagId().equals(sensorId)){
					idInt = pair.getxBeeId();
				}
			}
		}
			
		if(idInt !=null){
			String idString = idInt.toString();
			SensorSet toGetCoor = p.getEnvisSensors().get(idString);
			if(toGetCoor != null){
				p.getEnvisSensors().get(sensorId).setX(toGetCoor.getX());
				p.getEnvisSensors().get(sensorId).setY(toGetCoor.getY());
				p.getEnvisSensors().get(sensorId).setZ(toGetCoor.getZ());
			}
		}
	}

	private int offset = 15; // value to offset sensor reading text
//	private final int SENSORTYPE_TEMP = 1;
//	private final int SENSORTYPE_LIGHT = 2;
	
	private boolean selected;
	private PFont f;
	private int sensorType;
	
	public BarGraph (String sensorId, EnvisPApplet parent, float r, int type) {
		this.sensorId = sensorId;
		maxValue = (int)SensorListModel.getSingletonInstance().findSensorById(sensorId).getMaxValue();
		minValue = (int)SensorListModel.getSingletonInstance().findSensorById(sensorId).getMinValue();
		p = parent;
		reading = r;
		h = r;
		sensorType = type;
		
		color_selectedTrue = p.color(255, 202, 54);
		color_mainFill = p.color(127);
		
		color_barOutline = p.color(255);
		color_barFill = color_mainFill;
		
		selected = false;
		f = p.createFont("Arial",14,true); 
	}
	
	
	public void display() {
		//Log.i("values", maxReading + "");
		float color_grad = PApplet.map(reading, (int)maxValue, (int)minValue, 0, 255);
//		this.h = PApplet.map(reading, (int)minValue, (int)maxValueg, 0, 100);
		
		color_mainFill = p.color(colorRGB[0]+colorAssigned[sensorType][0]+color_grad, 
				colorRGB[1]+colorAssigned[sensorType][1]+color_grad, 
				colorRGB[2]+colorAssigned[sensorType][2]+color_grad, 127);

		
		if (this.selected) {
			color_barFill = color_selectedTrue;
		}
		
		else {
			color_barFill = color_mainFill;
		}
		
		
		
		p.pushMatrix();
		/* displaying the sensor reading */
	    p.pushMatrix();
	    p.textFont(f);
	    p.fill(255);
	    //p.text(reading,0,z+offset); 
	    p.popMatrix();
	    
	    p.pushMatrix();
	    
		
		p.stroke(color_barOutline);
		p.fill(color_barFill);

	      /* base face */
	      p.beginShape();
	      p.vertex(0, 0, 0);
	      p.vertex(x, 0, 0);
	      p.vertex(x, 0, h);
	      p.vertex(0, 0, h);  
	      p.endShape(PConstants.CLOSE);
	      
	      /* front face */
	      p.beginShape();
	      p.vertex(0, 0, 0);
	      p.vertex(0, z, 0);
	      p.vertex(x, z, 0);
	      p.vertex(x, 0, 0);
	      p.endShape(PConstants.CLOSE);
	      
	      /* left face */
	      p.beginShape();
	      p.vertex(0, 0, h);
	      p.vertex(0, z, h);
	      p.vertex(0, z, 0);
	      p.vertex(0, 0, 0);
	      p.endShape(PConstants.CLOSE);
	      
	      /* top face */
	      p.beginShape();
	      p.vertex(0, z, 0);
	      p.vertex(x, z, 0);
	      p.vertex(x, z, h);
	      p.vertex(0, z, h);
	      p.endShape(PConstants.CLOSE);
	      
	      /* back face (top face here) */
	      p.beginShape();
	      p.vertex(0, z, h);
	      p.vertex(0, 0, h);
	      p.vertex(x, 0, h);
	      p.vertex(x, z, h);
	      p.endShape(PConstants.CLOSE);
	      
	      /* right face */
	      p.beginShape();
	      p.vertex(x, z, h);
	      p.vertex(x, 0, h);
	      p.vertex(x, 0, 0);
	      p.vertex(x, z, 0);
	      p.endShape(PConstants.CLOSE);
	      
	      //p.stroke(255);
	      p.stroke(255);
	      p.popMatrix();
	      p.popMatrix();
	}
	
	public float getReading() {
		return reading;
	}
	
	public float getHeight() {
		return h;
	}
	
	public int getWidth() {
		return z;
	}
	
	public int getLength() {
		return z;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}



}
