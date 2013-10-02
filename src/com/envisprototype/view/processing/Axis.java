package com.envisprototype.view.processing;

import processing.core.PApplet;

public class Axis extends UIElement{
	int width, height;
	float xRotatedBy, yRotatedBy, zRotatedBy;
	public Axis(EnvisPApplet epApplet){
		super(epApplet);
		width = epApplet.sketchWidth();
		height = epApplet.sketchHeight();
	}
	@Override
	public void drawMe() {
		epApplet.pushMatrix();
		epApplet.translate(width/20, height-height/20);
		epApplet.strokeWeight(3);
		epApplet.pushMatrix();
		epApplet.rotateX(xRotatedBy);
		epApplet.rotateY(yRotatedBy);
		epApplet.rotateZ(zRotatedBy);
		epApplet.stroke(255,0,0);
		epApplet.popMatrix();
		epApplet.pushMatrix();
		epApplet.rotateX(xRotatedBy);
		epApplet.rotateY(yRotatedBy);
		epApplet.rotateZ(zRotatedBy);
		epApplet.line(0,0,0,height/20,0,0);
		epApplet.stroke(0,255,0);
		epApplet.popMatrix();
		epApplet.pushMatrix();
		epApplet.rotateX(xRotatedBy);
		epApplet.rotateY(yRotatedBy);
		epApplet.rotateZ(zRotatedBy);
		epApplet.line(0,0,0,0,height/20,0);
		epApplet.stroke(0,0,255);
		epApplet.popMatrix();
		epApplet.pushMatrix();
		epApplet.rotateX(xRotatedBy);
		epApplet.rotateY(yRotatedBy);
		epApplet.rotateZ(zRotatedBy);
		epApplet.line(0,0,0,0,0,height/20);
		epApplet.stroke(255,255,255);
		epApplet.popMatrix();
		epApplet.strokeWeight(1);
		epApplet.popMatrix();
//		epApplet.line(0,0,0,height/20+PApplet.sin(x),height/20*PApplet.sin(y), height/20+PApplet.sin(z));
	}

	@Override
	public void rotate(float xRotate, float yRotate, float zRotate) {
		// TODO Auto-generated method stub
		this.xRotatedBy = xRotate;
		this.yRotatedBy = yRotate;
		this.zRotatedBy = zRotate;
	}

}
