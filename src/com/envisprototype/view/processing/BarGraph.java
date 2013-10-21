package com.envisprototype.view.processing;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class BarGraph {
	private PApplet p;
	private int color_hoverTrue, color_hoverFalse, color_selectedTrue, color_mainFill;
	private int color_barOutline, color_barFill;
	private int z = 20; // bar graph width or z
	private int x = 20; // bar graph length or x
	private float h, reading;
	private int offset = 15; // value to offset sensor reading text
	private int SENSORTYPE_TEMP = 1;
	private int SENSORTYPE_LIGHT = 2;
	
	private boolean selected;
	private boolean hover;
	private PFont f;
	private int sensorType;
	
	public BarGraph (PApplet parent, float r, int type) {
		
		p = parent;
		reading = r;
		h = r;
		sensorType = type;
		float[] colorRGB = { 0, 0, 0 };
		
		color_hoverTrue = p.color(252, 216, 8);
		color_hoverFalse = p.color(250);
		color_selectedTrue = p.color(255, 202, 54);
		color_mainFill = p.color(127);
		
		if (sensorType == SENSORTYPE_TEMP) {
			float color_grad = PApplet.map(reading, 100, 0, 0, 250);
			float[] colorAssigned = { 255, 0, 0 };		// for users to customize colors to sensor type
			color_mainFill = p.color(colorRGB[0]+colorAssigned[0]+color_grad, 
					colorRGB[1]+colorAssigned[1]+color_grad, 
					colorRGB[2]+colorAssigned[2]+color_grad, 127);
		}
		
		else if (sensorType == SENSORTYPE_LIGHT) {
			float color_grad = PApplet.map(reading, 100, 0, 50, 250);
			float[] colorAssigned = { 0, 0, 255 };		// for users to customize colors to sensor type
			color_mainFill = p.color(colorRGB[0]+colorAssigned[0]+color_grad, 
					colorRGB[1]+colorAssigned[1]+color_grad, 
					colorRGB[2]+colorAssigned[2]+color_grad, 127);
//			color_mainFill = p.color(42, 223, 232 ,127);
		}
		
		color_barOutline = color_hoverFalse;
		color_barFill = color_mainFill;
		
		selected = false;
		hover = false;
		f = p.createFont("Arial",14,true); 
	}
	
	
	public void display() {
		
		/* displaying the sensor reading */
	    p.pushMatrix();
	    p.textFont(f);
	    p.fill(255);
	    p.text(reading,0,z-offset); 
	    p.popMatrix();
	    
	    p.pushMatrix();
	    
	    if (this.hover) {
			color_barOutline = color_hoverTrue;
		}
		else {
			color_barOutline = color_hoverFalse;
		}
		
		if (this.selected) {
			color_barFill = color_selectedTrue;
		}
		
		else {
			color_barFill = color_mainFill;
		}
		
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
	      
	      /* back face */
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
	      
	      p.stroke(255);
	      p.popMatrix();
	}
	
	public float getReading() {
		return h;
	}
	
	public float getHeight() {
		return h;
	}
	
	public int getWidth() {
		return z;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}


}
