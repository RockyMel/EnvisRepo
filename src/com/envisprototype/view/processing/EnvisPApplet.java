package com.envisprototype.view.processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import processing.core.PApplet;
import processing.core.PConstants;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.envisprototype.R;
import com.envisprototype.controller.processing.eventListeners.RotateScopeListener;
import com.envisprototype.controller.processing.eventListeners.ZoomListener;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
//import com.envisprototype.controller.ModelSaver;

public abstract class EnvisPApplet extends PApplet{
	
	static final int BACKGROUND_COLOR = 0xff000000; 
	public static final int STROKE_COLOR = 0xffffffff;
	static final int STROKE_WEIGHT = 1;
	public static int DEF_BTN_X;
	int MAX_WIDTH, MIN_WIDTH = 0;
	EnvisButton zoom, rotateScope;
	private Axis axis;
	public PositionDisplay currentClick;
	HashMap<String,SensorSet> envisSensors;
	Map envisMap;
	String flag;
	ArrayList<String> setIdFromAndroid;
	Bundle extras;
	Iterator setIterator;
	  
	  public void setup(){
		  // default setup which will be reused
		  // to specify size of ui elements for different screens
		  ellipseMode(PConstants.CORNERS);
		  fill(217,200,33);
		  MAX_WIDTH =  width-width/7;
		  DEF_BTN_X = width-width/8;
		  background(BACKGROUND_COLOR);
		  stroke(STROKE_COLOR);
		  // smooth is not available on most devices, but sometimes makes
		  // the ui prettier
		  smooth();
		  envisSensors = new HashMap<String,SensorSet>();
		  // // current click is showing coordinates of the last finger tap
		  currentClick = new PositionDisplay(this, "");
		  currentClick.setPlace(width/15, height/30);
		  rotateScope = new EnvisButton(this, "");
		  rotateScope.setPlace(width/40, height-3*height/50);
		  rotateScope.setSize(width-width/10, 2*height/50);
		  rotateScope.addEventListener(new RotateScopeListener());
		  envisMap = new Map(this);
		  // getting a map id from model.
		  extras = getIntent().getExtras();
		  if(extras != null){
			  if(extras.containsKey(getString(R.string.map_id_extra))){
				  String mapId = extras.getString(getString(R.string.map_id_extra));
				  envisMap.setMapId(mapId);
				  Coordinates coors = MapListModel.getSingletonInstance().findMapById(mapId).getRealCoordinates();
				  
				  Log.i("coors",coors.toString());
				  envisMap.setAllCoors(coors);
			  }
			  if(extras.containsKey(getString(R.string.flags))){
				  flag = extras.getString(getString(R.string.flags));
				  if(flag.equals(getString(R.string.plot_flag_extra))){
					  // plotting several sets at the same time
					  if(extras.containsKey(getString(R.string.sets_id_extra))){
						  setIdFromAndroid = extras.getStringArrayList(getString(R.string.sets_id_extra));
						  for(String setId: setIdFromAndroid){
							  SetInterface setFromModel =  SetListModel.getSingletonInstance().findSetById(setId);
							  SensorSet setToShow = new SensorSet(this, setId);
							  envisSensors.put(setId, setToShow);
						  }
					  }
				  }
			  }
		  }
		  // HERE SENSORS MUST BE ADDED SIMILARLY TO MAP (EXTRAS)
		  //envisSensors = filler.prepareSensorsCoordinates("sensors.txt");
		  // zoom works only in 3D
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
	 * - rotateScope border ���������
	 * - zoomBar
	 * - 3D map ���������
	 * - sets ���������
	 * - axis ���������
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
//	println("coor in threedvissss = " + envisMap.getRealCoors().getCoorX().toString());
	if(ifWithSensors){
		setIterator = envisSensors.keySet().iterator();
		while(setIterator.hasNext()){
			envisSensors.get(setIterator.next()).drawMe();
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
	public HashMap<String,SensorSet> getEnvisSensors() {
		return envisSensors;
	}
	public void setEnvisSensors(HashMap<String,SensorSet> envisSensors) {
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
	
	public ArrayList<String> getSetIdFromAndroid(){
		return setIdFromAndroid;
	}
	

	
	public EnvisButton getRotateScope() {
		return rotateScope;
	}

	public void setRotateScope(EnvisButton rotateScope) {
		this.rotateScope = rotateScope;
	}

	public int sketchWidth() {
			Display display = getWindowManager().getDefaultDisplay();
		  return display.getWidth(); }
	  public int sketchHeight() {
			Display display = getWindowManager().getDefaultDisplay();
		  return display.getHeight(); }
	  public String sketchRenderer() { return P3D; }
}
