package com.envisprototype.view.processing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

import com.envisprototype.controller.HistoricalThreeDController;
import com.envisprototype.controller.RealTimeThreeDVis;
import com.envisprototype.model.processing.XbeeTagPair;
import com.envisprototype.model.sensor.SensorListModel;

public class SphereGraph {
	
	EnvisPApplet p;
	private PFont f;
	private int color_hoverTrue, color_hoverFalse, color_selectedTrue, color_mainFill;
	private int color_barOutline, color_barFill;
	float[] colorRGB = { 0, 0, 0 };
	private int size = 20;
	private int offset = 20;
	private int opacity = 80;
	private float reading; 
	private int sensorType;
	private float fillColor;
	private String sensorId;
	int maxValue;
	int minValue;
	int rad = 0;
	SensorSet masterSensor;
	float[] coors;
	int[] randomColor = new int[3]; 
	private int SENSORTYPE_TEMP = 1;
	private int SENSORTYPE_LIGHT = 2;
	private float[][] colorAssigned= {{0,0,0},{0, 255, 238},{255, 136, 0},{255, 238, 0},
			{255, 0, 204},{255, 0, 0},{0, 0, 255},{0, 255, 0}};
	Integer idInt;
	
	public SphereGraph(String sensorId, EnvisPApplet parent, float reading, int sensorType) {
		this.sensorId = sensorId;
		maxValue = (int)SensorListModel.getSingletonInstance().findSensorById(sensorId).getMaxValue();
		minValue = (int)SensorListModel.getSingletonInstance().findSensorById(sensorId).getMinValue();
		p = parent;
		coors= p.getEnvisMap().calculateMiddleCoors();
		f = p.createFont("Arial",14,true);
		this.reading = reading;
		this.sensorType = sensorType;
		
		color_hoverTrue = p.color(252, 216, 8);
		color_hoverFalse = p.color(250);
		color_selectedTrue = p.color(255, 202, 54);
		color_mainFill = p.color(127);
		randomColor[0] = (int) p.random(255);
		randomColor[1] = (int) p.random(255);
		randomColor[2] = (int) p.random(255);
		
	}
	
	public void display() {
		
		float color_grad = PApplet.map(reading, (int)maxValue, (int)minValue, 0, 255);
		color_mainFill = p.color(colorRGB[0]+colorAssigned[sensorType][0]+color_grad, 
				colorRGB[1]+colorAssigned[sensorType][1]+color_grad, 
				colorRGB[2]+colorAssigned[sensorType][2]+color_grad, 127);
		if(SensorListModel.getSingletonInstance().findSensorById(sensorId).getNotes().equals("slave")){
			p.pushMatrix();
			p.ellipseMode(PConstants.RADIUS);
			p.noFill();
			p.stroke(randomColor[0],randomColor[1],randomColor[2]);
			//p.tint(255,255,255,230);
			rad = (int)(421.36*p.pow(p.exp(1), -0.0589f*reading))*p.width/60;
			//SensorInterface toGetCoor = SensorListModel.getSingletonInstance().findSensorById(RealTimeThreeDVis.xbeeTokens.get(sensorId).toString());
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
			
			//Log.i("sial", rad + "");
			if(rad != 0)
			p.ellipse(0,0,rad,rad);
			p.pushMatrix();
			p.textSize(20);
			p.fill(randomColor[0],randomColor[1],randomColor[2]);
			p.text(sensorId, rad/2, rad);
			p.text(reading, rad/2, rad+offset);
			p.popMatrix();
			p.popMatrix();
		}
		//p.textFont(f);
		p.pushMatrix();
		//p.noStroke();
		p.fill(color_mainFill);
		//p.sphere(size);
		//p.fill(255);
		if(SensorListModel.getSingletonInstance().findSensorById(sensorId).getNotes().equals("master")){
			p.pushMatrix();
			p.fill(255);
			p.box(10);
			p.textSize(20);
			p.text(sensorId, offset,offset);
			p.popMatrix();
		}
		else{
			if(SensorListModel.getSingletonInstance().findSensorById(sensorId).getNotes().equals("slave")){
//				p.noFill();
//				p.stroke(10);
				//p.ellipse(0,0,rad,rad);
//				p.text(sensorId, rad/2, rad);
//				p.text(reading, rad/2, rad+offset);
			}
			//p.text(reading, offset, offset);
		}
		
		p.popMatrix();
	}
	
	public float getReading() {
		return reading;
	}
	public void setReading(float reading) {
		this.reading = reading;
	}

}
