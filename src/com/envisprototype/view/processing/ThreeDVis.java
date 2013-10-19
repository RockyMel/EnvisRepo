package com.envisprototype.view.processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.envisprototype.controller.processing.eventListeners.FrontViewButtonListener;
import com.envisprototype.controller.processing.eventListeners.LeftSideViewButtonListener;
import com.envisprototype.controller.processing.eventListeners.RotateButtonListener;
import com.envisprototype.controller.processing.eventListeners.RotateScopeListener;
import com.envisprototype.controller.processing.eventListeners.TopViewButtnoListener;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;

public class ThreeDVis extends EnvisPApplet{
	
	EnvisButton frontViewButton, leftSideViewButton, topViewButton, rotateButton; 
	//BarGraphSet barGraphSet;
	final boolean DRAW_WITH_SENSORS = true;


public void setup(){
	super.setup();
	// to visualise the map in 3D
	// by this time the map has already been copied from the model
    envisMap.setIf3D(true);
    envisMap.translateToMiddle();
    rotateButton = new EnvisButton(this, "Rotate");
    rotateButton.setPlace(DEF_BTN_X, height/30);
    rotateButton.addEventListener(new RotateButtonListener());
    frontViewButton = new EnvisButton(this, "Front View");
    frontViewButton.setPlace(DEF_BTN_X, 3*height/30);
    frontViewButton.addEventListener(new FrontViewButtonListener());
    leftSideViewButton = new EnvisButton(this, "Left Side");
    leftSideViewButton.setPlace(DEF_BTN_X, 5*height/30);
    leftSideViewButton.addEventListener(new LeftSideViewButtonListener());
    topViewButton = new EnvisButton(this, "Top View");
    topViewButton.setPlace(DEF_BTN_X, 7*height/30);
    topViewButton.addEventListener(new TopViewButtnoListener());
    RotateScopeListener.setIfTop(true);
    // need to get coordinates for the sensors
    {
    String sensorId, setId;
    setIterator = envisSensors.keySet().iterator();
    while(setIterator.hasNext()){
    	sensorId = setIterator.next();
    	if(!SensorListModel.getSingletonInstance().findSensorById(sensorId).isIfDefaultCoors()){
    		envisSensors.get(sensorId).setRealX(SensorListModel.getSingletonInstance().findSensorById(sensorId).getX());
        	envisSensors.get(sensorId).setRealY(SensorListModel.getSingletonInstance().findSensorById(sensorId).getY());
        	envisSensors.get(sensorId).setRealZ(SensorListModel.getSingletonInstance().findSensorById(sensorId).getZ());
        	envisSensors.get(sensorId).setX(SensorListModel.getSingletonInstance().findSensorById(sensorId).getX());
        	envisSensors.get(sensorId).setY(SensorListModel.getSingletonInstance().findSensorById(sensorId).getY());
        	envisSensors.get(sensorId).setZ(SensorListModel.getSingletonInstance().findSensorById(sensorId).getZ());
    	}
    	else{
    		// set xyz as in set that this sensor belongs to
    		setId = SensorListModel.getSingletonInstance().findSensorById(sensorId).getSetid();
    		List<SetInterface> asdf = SetListModel.getSingletonInstance().getSetList();
    		envisSensors.get(sensorId).setRealX(SetListModel.getSingletonInstance().findSetById(setId).getX());
        	envisSensors.get(sensorId).setRealY(SetListModel.getSingletonInstance().findSetById(setId).getY());
        	envisSensors.get(sensorId).setRealZ(SetListModel.getSingletonInstance().findSetById(setId).getZ());
        	envisSensors.get(sensorId).setX(SetListModel.getSingletonInstance().findSetById(setId).getX());
        	envisSensors.get(sensorId).setY(SetListModel.getSingletonInstance().findSetById(setId).getY());
        	envisSensors.get(sensorId).setZ(SetListModel.getSingletonInstance().findSetById(setId).getZ());
    	}
    	envisSensors.get(sensorId).translateSensorsForMap(envisMap);
    	
    }		    	
    }
}
public void draw(){
	super.draw();
	threeDDrawPreset(DRAW_WITH_SENSORS); // true - drawing with sensors
	//rotateButton.drawMe();
	frontViewButton.drawMe();
	leftSideViewButton.drawMe();
	topViewButton.drawMe();
	
}

@Override
public void mouseDragged(){
	super.mouseDragged();
	rotateScope.fireEvent();
}

public void mouseReleased(){
	//rotateButton.fireEvent();
	frontViewButton.fireEvent();
	leftSideViewButton.fireEvent();
	topViewButton.fireEvent();
}

public float medianValue(ArrayList<Float> list){
  Collections.sort(list);
  return list.get(list.size()/2);
}
}


