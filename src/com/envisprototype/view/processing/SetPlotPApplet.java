package com.envisprototype.view.processing;

import com.envisprototype.R;
import com.envisprototype.controller.processing.eventListeners.AddSetsBtnListener;
import com.envisprototype.controller.processing.eventListeners.RemoveSetsBtnListener;
import com.envisprototype.controller.processing.eventListeners.SaveSetBtnListener;
import com.envisprototype.controller.processing.eventListeners.SensitiveListener;

public class SetPlotPApplet extends EnvisPApplet {
	
	EnvisButton saveSensorsBtn, removeLastSetBtn, addSetsBtn;
	EnvisButton addSensitivePart;
	
	private boolean ifAddingSets = false;
	private boolean ifRemovingSets = false;

public void setup(){
	super.setup();
	addSensitivePart = new EnvisButton(this, "");
	saveSensorsBtn = new EnvisButton(this, getString(R.string.save_sets));
	saveSensorsBtn.setPlace(DEF_BTN_X, height - height/30);
	saveSensorsBtn.addEventListener(new SaveSetBtnListener(this, "sensors.txt"));
	addSensitivePart.addEventListener(new SensitiveListener());
	addSensitivePart.setSize(MAX_WIDTH, height);
	addSensitivePart.setPlace(0, 0);
	removeLastSetBtn = new EnvisButton(this, getString(R.string.remove_sets));
	removeLastSetBtn.setPlace(DEF_BTN_X, height/30);
	removeLastSetBtn.addEventListener(new RemoveSetsBtnListener());
	addSetsBtn = new EnvisButton(this, getString(R.string.add_set));
	addSetsBtn.setPlace(DEF_BTN_X, 3*height/30);
	addSetsBtn.addEventListener(new AddSetsBtnListener());
	envisMap.translateToMiddle();
	for(int i = 0; i < envisSensors.size(); i++){
		envisSensors.get(i).translateSensorsForMap(envisMap);
	}
}

public void draw(){
	super.draw();
	//Log.i("sets","set size" + envisSensors.size());
	float[] center = envisMap.calculateMiddleCoors();
	currentClick.drawText((mouseX + center[Map.indexX] - width/2) + ", " + (mouseY + center[Map.indexY] - height/2)+ "; ");
	//addSensitivePart.drawRect();
	//currentClick.drawMe();
	saveSensorsBtn.drawMe();
	removeLastSetBtn.drawMe();
	addSetsBtn.drawMe();
	pushMatrix();
	translate(width/2, height/2);
	envisMap.drawMe2D();
	for(int i = 0; i < envisSensors.size(); i++){
		envisSensors.get(i).drawMe();
		//getEnvisSensors().get(i).rotate();
	}
	popMatrix();
	
}

public void mouseReleased(){
	addSensitivePart.fireEvent();
	saveSensorsBtn.fireEvent();
	removeLastSetBtn.fireEvent();
	addSetsBtn.fireEvent();
}

@Override
public void mouseDragged(){}

public EnvisButton getSaveSensorsBtn() {
	return saveSensorsBtn;
}

public void setSaveSensorsBtn(EnvisButton saveSensors) {
	this.saveSensorsBtn = saveSensors;
}



public EnvisButton getRemoveLastSetBtn() {
	return removeLastSetBtn;
}

public void setRemoveLastSetBtn(EnvisButton removeLastSetBtn) {
	this.removeLastSetBtn = removeLastSetBtn;
}

public EnvisButton getAddSetsBtn() {
	return addSetsBtn;
}

public void setAddSetsBtn(EnvisButton addSetsBtn) {
	this.addSetsBtn = addSetsBtn;
}

public boolean isIfAddingSets() {
	return ifAddingSets;
}

public void setIfAddingSets(boolean ifAddingSets) {
	this.ifAddingSets = ifAddingSets;
}

public boolean isIfRemovingSets() {
	return ifRemovingSets;
}

public void setIfRemovingSets(boolean ifRemovingSets) {
	this.ifRemovingSets = ifRemovingSets;
};



}
