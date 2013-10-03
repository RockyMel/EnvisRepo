package com.envisprototype.view.processing;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.util.Log;

import com.envisprototype.controller.processing.eventListeners.FrontViewButtonListener;
import com.envisprototype.controller.processing.eventListeners.LeftSideViewButtonListener;
import com.envisprototype.controller.processing.eventListeners.RotateButtonListener;
import com.envisprototype.controller.processing.eventListeners.TopViewButtnoListener;

public class ThreeDVis extends EnvisPApplet{
	
	EnvisButton frontViewButton, leftSideViewButton, topViewButton, rotateButton; 
	
//@Override
//public void onCreate(Bundle onSavedIstanceState){
//	Log.i("coors","asdf");
//	super.onCreate(onSavedIstanceState);
//}

public void setup(){
	super.setup();
	Log.i("coors","setup");
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
    for(int i = 0; i < envisSensors.size(); i++){
    	envisSensors.get(i).translateSensorsForMap(envisMap);
    }
}
public void draw(){
	super.draw();
	Log.i("coors","draw");
	threeDDrawPreset(true); //drawing with sensors
	rotateButton.drawMe();
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
	rotateButton.fireEvent();
	frontViewButton.fireEvent();
	leftSideViewButton.fireEvent();
	topViewButton.fireEvent();
}

public float medianValue(ArrayList<Float> list){
  Collections.sort(list);
  return list.get(list.size()/2);
}
}


