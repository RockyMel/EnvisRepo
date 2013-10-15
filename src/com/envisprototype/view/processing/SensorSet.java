package com.envisprototype.view.processing;

import processing.core.PApplet;

import com.envisprototype.model.processing.Coordinates;

import android.util.Log;

public class SensorSet extends UIElement{
	String id;
	float x,y,z;
	float realX, realY, realZ;
	private int SET_STROKE_WEIGHT = 20;

	public SensorSet(EnvisPApplet epApplet, String id, float x, float y, float z) {
		super(epApplet);
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.realX = x;
		this.realY = y;
		this.realZ = z;
		printCoors();
	}
	
	public SensorSet(EnvisPApplet epApplet, String id, String name, float x, float y, float z) {
		this(epApplet, id, x, y, z);
		this.name = name;
		printCoors();
	}
	
	public void printCoors(){
		Log.i("sets", "vis: "+ x + ", " + y + ", " + z + " \n"+
	" real: " + realX + ", " + realY + ", " + realZ);
	}
	

	@Override
	public void drawMe() {
		// TODO Auto-generated method stub
		epApplet.pushMatrix();
		//epApplet.translate(xRotatedBy, yRotatedBy, zRotatedBy);
		epApplet.strokeWeight(SET_STROKE_WEIGHT);
		epApplet.stroke(color[R],color[G],color[B]);
		//epApplet.scale(epApplet.getEnvisMap().getZoomValue());
		epApplet.point(x,y,z);
		epApplet.strokeWeight(EnvisPApplet.STROKE_WEIGHT);
		epApplet.text(realX + ", " + realY, x,y,z);
		epApplet.popMatrix();
	}
	
	public boolean isSameSpot(SensorSet setToCompare){
		if(this.ifHitSet(setToCompare.getX(), setToCompare.getY()))
			return true;
		else
			return false;
	}

	private boolean ifHitSet(float x, float y){
		if(PApplet.abs(this.x-x) <= SET_STROKE_WEIGHT && PApplet.abs(this.y - y) <= SET_STROKE_WEIGHT){
			return true;
		}
		else return false;
	}
	
	public void translateSensorsForMap(Map map){
		float[] coors = map.calculateMiddleCoors();
		if(coors == null)
			return;
		x-=coors[Map.indexX];
		y-=coors[Map.indexY];
		Log.i("sets", "for map");
		printCoors();
	}
	
	public void translateSensorsForFile(Map map){
		float[] coors = map.calculateMiddleCoors();
		realX+=coors[Map.indexX]-epApplet.width/2;
		realY+=coors[Map.indexY]-epApplet.height/2;
		realZ+=coors[Map.indexZ];
		x-=epApplet.width/2;
		y-=epApplet.height/2;
		Log.i("sets", "for file");
		printCoors();
	}

	public int getSET_STROKE_WEIGHT() {
		return SET_STROKE_WEIGHT;
	}

	public void setSET_STROKE_WEIGHT(int sET_STROKE_WEIGHT) {
		SET_STROKE_WEIGHT = sET_STROKE_WEIGHT;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getRealX() {
		return realX;
	}

	public void setRealX(float realX) {
		this.realX = realX;
	}

	public float getRealY() {
		return realY;
	}

	public void setRealY(float realY) {
		this.realY = realY;
	}

	public float getRealZ() {
		return realZ;
	}

	public void setRealZ(float realZ) {
		this.realZ = realZ;
	}

	@Override
	public void rotate(float xRotate, float yRotate, float zRotate) {
		// TODO Auto-generated method stub
		epApplet.rotateX(xRotate);
		epApplet.rotateY(yRotate);
		epApplet.rotateZ(zRotate);
	}
	
	

}
