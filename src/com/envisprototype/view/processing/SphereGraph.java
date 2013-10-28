package com.envisprototype.view.processing;

import processing.core.PApplet;
import processing.core.PFont;

public class SphereGraph {
	
	PApplet p;
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
	
	private int SENSORTYPE_TEMP = 1;
	private int SENSORTYPE_LIGHT = 2;
	
	public SphereGraph(PApplet parent, float reading, int sensorType) {
		p = parent;
		f = p.createFont("Arial",14,true);
		this.reading = reading;
		this.sensorType = sensorType;
		
		color_hoverTrue = p.color(252, 216, 8);
		color_hoverFalse = p.color(250);
		color_selectedTrue = p.color(255, 202, 54);
		color_mainFill = p.color(127);
		
	}
	
	public void display() {
		
		if (sensorType == SENSORTYPE_TEMP) {
			float color_grad = PApplet.map(reading, 1000, 0, 0, 250);
			float[] colorAssigned = { 255, 0, 0 };		// for users to customize colors to sensor type
			color_mainFill = p.color(colorRGB[0]+colorAssigned[0]+color_grad, 
					colorRGB[1]+colorAssigned[1]+color_grad, 
					colorRGB[2]+colorAssigned[2]+color_grad,
					opacity);
		}
		
		else if (sensorType == SENSORTYPE_LIGHT) {
			float color_grad = PApplet.map(reading, 1000, 0, 50, 250);
			float[] colorAssigned = { 0, 0, 255 };		// for users to customize colors to sensor type
			color_mainFill = p.color(colorRGB[0]+colorAssigned[0]+color_grad, 
					colorRGB[1]+colorAssigned[1]+color_grad, 
					colorRGB[2]+colorAssigned[2]+color_grad,
					opacity);
		}
		p.textFont(f);
		p.pushMatrix();
		p.noStroke();
		p.fill(color_mainFill);
		p.sphere(size);
		p.fill(255);
		p.text(reading, offset, offset);
		p.popMatrix();
	}
	
	public float getReading() {
		return reading;
	}
	public void setReading(float reading) {
		this.reading = reading;
	}

}
