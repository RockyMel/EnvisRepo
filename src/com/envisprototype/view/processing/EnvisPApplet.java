package com.envisprototype.view.processing;

import java.util.ArrayList;

import processing.core.PApplet;
import android.util.Log;
import android.view.Display;

import com.envisprototype.controller.processing.CoordinateFiller;
import com.envisprototype.controller.processing.eventListeners.RotateScopeListener;
import com.envisprototype.controller.processing.eventListeners.ZoomListener;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.processing.Coordinates;

public abstract class EnvisPApplet extends PApplet{
	
	static final int BACKGROUND_COLOR = 0xff000000; 
	public static final int STROKE_COLOR = 0xffffffff;
	static final int STROKE_WEIGHT = 1;
	public static int DEF_BTN_X;
	int MAX_WIDTH;
	EnvisButton zoom;
	EnvisButton rotateScope;
	private Axis axis;
	public PositionDisplay currentClick;
	ArrayList<SensorSet> envisSensors;
	Map envisMap;
	  
	  
	  public void setup(){
		  //size(width,height,P3D);
		  MAX_WIDTH =  width-width/7;
		  DEF_BTN_X = width-width/8;
		  background(BACKGROUND_COLOR);
		  stroke(STROKE_COLOR);
		  smooth();
		  envisSensors = new ArrayList<SensorSet>();		  
		  currentClick = new PositionDisplay(this, "");
		  currentClick.setPlace(width/15, height/30);
		  envisMap = new Map(this);
		  rotateScope = new EnvisButton(this, "");
		  rotateScope.setPlace(1, 1);
		  rotateScope.setSize(MAX_WIDTH, height-2);
		  rotateScope.addEventListener(new RotateScopeListener());
		  CoordinateFiller filler = new CoordinateFiller(this);
		  filler.prepareMapCoordinates("map.txt");
		  if(getIntent().getExtras() != null){
			  if(getIntent().getExtras().containsKey("mapID")){
				  String mapID = (String)(getIntent().getExtras().get("mapID"));
				  Coordinates coors = MapListModel.getSingletonInstance().findMapById(mapID).getRealCoordinates();
				  Log.i("coors",coors.toString());
				  envisMap.setRealCoors(coors);
				  envisMap.setVisCoors(coors);
			  }
		  }
		  envisSensors = filler.prepareSensorsCoordinates("sensors.txt");
		  zoom = new EnvisButton(this, "");
		  zoom.addEventListener(new ZoomListener(envisMap));  
		  //drawing a zoom bar
		  zoom.setPlace(width/100, width/100);
		  zoom.setSize(width/50, height-height/20);
		  axis = new Axis(this);
	  }
	  
	  public void draw(){
		  clear();
	  }
@Override
public void mouseDragged(){
	if(envisMap.isIfCentered()){
	zoom.fireEvent();
	}
}

public void threeDDrawPreset(boolean ifWithSensors){
	/*
	 * Things to draw:
	 * - rotateScope border ���
	 * - zoomBar
	 * - 3D map ���
	 * - sets ���
	 * - axis ���
	 * - a bunch of buttons to choose vis type (bars or spheres)
	 * - bars or spheres
	 */
	currentClick.drawMe();
	pushMatrix();
	rotateScope.drawRect();
	translate(width/2,height/2);
	rotateScope.fireEvent();
	envisMap.drawMe();
	//scale(envisMap.getZoomValue());
	//println("zoom value = " + envisMap.getZoomValue());
	if(ifWithSensors){
		for(int i = 0; i < envisSensors.size(); i++){
	    	envisSensors.get(i).drawMe();
	    }
	}
	popMatrix();
	axis.drawMe();
	line(width/2,0,width/2,height);
	line(0,height/2,MAX_WIDTH,height/2);
	line(width/2,0,width/2,height);
	if(ifWithSensors){
		zoom.drawRect();
	}
}
	  
	public Axis getAxis() {
		return axis;
	}
	public void setAxis(Axis axis) {
		this.axis = axis;
	}
	public EnvisButton getZoom() {
		return zoom;
	}
	public void setZoom(EnvisButton zoom) {
		this.zoom = zoom;
	}
	public ArrayList<SensorSet> getEnvisSensors() {
		return envisSensors;
	}
	public void setEnvisSensors(ArrayList<SensorSet> envisSensors) {
		this.envisSensors = envisSensors;
	}
	public int getMAX_WIDTH() {
		return MAX_WIDTH;
	}
	  public Map getEnvisMap() {
		return envisMap;
	}
	public void setEnvisMap(Map envisMap) {
		this.envisMap = envisMap;
	}
//	public int sketchWidth() {
//			Display display = getWindowManager().getDefaultDisplay();
//		  return display.getWidth(); }
//	  public int sketchHeight() {
//			Display display = getWindowManager().getDefaultDisplay();
//		  return display.getHeight(); }
	  //public String sketchRenderer() { return P3D; }
}
