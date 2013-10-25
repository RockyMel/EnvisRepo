package com.envisprototype.view.processing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

import com.envisprototype.controller.processing.dragListeners.ZCoorBtnListener;
import com.envisprototype.controller.processing.eventListeners.IdBtnListener;
import com.envisprototype.controller.processing.eventListeners.SaveSetBtnListener;
import com.envisprototype.controller.processing.eventListeners.SensitiveListener;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;

public class SetPlotPApplet extends EnvisPApplet {
	
	EnvisButton addSensitivePart , upSetIdBtn, downSetIdBtn, okBtn;
	ZCoorSpinner zCoorSpinner;
	String currentIdToPlot;
	Iterator iterator;
	ArrayList<EnvisButton> idButtons;
	
	private float curZ;
	
	private boolean ifAddingSets = true;

public void setup(){
	super.setup();
	addSensitivePart = new EnvisButton(this, "");
	addSensitivePart.addEventListener(new SensitiveListener());
	addSensitivePart.setSize(MAX_WIDTH, height);
	addSensitivePart.setPlace(0, 0);
	envisMap.translateToMiddle();
	zCoorSpinner = new ZCoorSpinner(this, "");
	int zCoorY = (currentClick.getDefY() + currentClick.getDefH()/2)-width/60; 
	zCoorSpinner.setSize(width/30, width/30);
	zCoorSpinner.setPlace(currentClick.getDefW()+currentClick.getDefX()+width/50,zCoorY);
	zCoorSpinner.addDragEventListener(new ZCoorBtnListener());
	setIterator = envisSensors.keySet().iterator();
	while(setIterator.hasNext()){
		envisSensors.get(setIterator.next()).translateSensorsForMap(envisMap);
	}
	
	// setting up the spinning list of ids to the right
	upSetIdBtn = new EnvisButton(this, "UP");
	downSetIdBtn = new EnvisButton(this, "DOWN");
	upSetIdBtn.setPlace(MAX_WIDTH, height/20);
	okBtn = new EnvisButton(this, "Ok");
	okBtn.addEventListener(new SaveSetBtnListener());
	idButtons = new ArrayList<EnvisButton>();
	int setBtnHeight = 2*height/20;
	for(String id: setIdFromAndroid){
		
		EnvisButton tempSetBtn = new EnvisButton(this, id, id);
		Log.i("id",id);
		tempSetBtn.setPlace(MAX_WIDTH, setBtnHeight);
		tempSetBtn.addEventListener(new IdBtnListener());
		idButtons.add(tempSetBtn);
		setBtnHeight+=height/20;
	}
	downSetIdBtn.setPlace(MAX_WIDTH, height-2*height/20);
	okBtn.setPlace(MAX_WIDTH, height-height/20);
	String sensorId;// setId;
    setIterator = envisSensors.keySet().iterator();
	while(setIterator.hasNext()){
    	sensorId = setIterator.next();
    	SensorInterface tempSensor = SensorListModel.getSingletonInstance().findSensorById(sensorId);
    	SetInterface tempSet = SetListModel.getSingletonInstance().findSetById(sensorId);
    	if(tempSensor != null){
    		envisSensors.get(sensorId).setRealX(tempSensor.getX());
        	envisSensors.get(sensorId).setRealY(tempSensor.getY());
        	envisSensors.get(sensorId).setRealZ(tempSensor.getZ());
        	envisSensors.get(sensorId).setX(tempSensor.getX());
        	envisSensors.get(sensorId).setY(tempSensor.getY());
        	envisSensors.get(sensorId).setZ(tempSensor.getZ());
    	}
    	else
    	if(tempSet != null){
    		// set xyz as in set that this sensor belongs to
    		envisSensors.get(sensorId).setRealX(tempSet.getX());
        	envisSensors.get(sensorId).setRealY(tempSet.getY());
        	envisSensors.get(sensorId).setRealZ(tempSet.getZ());
        	envisSensors.get(sensorId).setX(tempSet.getX());
        	envisSensors.get(sensorId).setY(tempSet.getY());
        	envisSensors.get(sensorId).setZ(tempSet.getZ());
    	}
    	envisSensors.get(sensorId).translateSensorsForMap(envisMap);
    	
    }	
}

public void draw(){
	super.draw();
	for(int i = 0; i < idButtons.size(); i++){
		idButtons.get(i).drawMe();
		idButtons.get(i).fireEvent();
	}
	upSetIdBtn.drawMe();
	downSetIdBtn.drawMe();
	okBtn.drawMe();
	if(ifAddingSets){
		pushMatrix();
		translate(width/2, height/2);
		envisMap.drawMe2D();
		iterator = envisSensors.keySet().iterator();
		while(iterator.hasNext()){
			envisSensors.get(iterator.next()).drawMe();
		}
		popMatrix();
	}
	else{
		threeDDrawPreset(true);
		zCoorSpinner.drawMe();
		zCoorSpinner.fireDragEvent();
	}
	okBtn.fireEvent();
}

public void mouseReleased(){
	addSensitivePart.fireEvent();
}

@Override
public void mouseDragged(){}

public boolean isIfAddingSets() {
	return ifAddingSets;
}

public void setIfAddingSets(boolean ifAddingSets) {
	this.ifAddingSets = ifAddingSets;
}

public String getCurrentIdToPlot() {
	return currentIdToPlot;
}

public void setCurrentIdToPlot(String currentIdToPlot) {
	this.currentIdToPlot = currentIdToPlot;
}

public ArrayList<EnvisButton> getIdButtons() {
	return idButtons;
}

public void setIdButtons(ArrayList<EnvisButton> idButtons) {
	this.idButtons = idButtons;
}



public EnvisButton getOkBtn() {
	return okBtn;
}

public void setOkBtn(EnvisButton okBtn) {
	this.okBtn = okBtn;
}

public EnvisButton findidButtonById(String id){
	for(EnvisButton btn: idButtons)
		if(btn.getBtnID().equals(id))
			return btn;
	return null;
}

public float getCurZ() {
	return curZ;
}

public void setCurZ(float curZ) {
	this.curZ = curZ;
}



}
