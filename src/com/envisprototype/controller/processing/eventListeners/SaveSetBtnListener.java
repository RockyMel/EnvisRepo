package com.envisprototype.controller.processing.eventListeners;

import java.util.ArrayList;
import java.util.EventObject;

import com.envisprototype.R;
import com.envisprototype.controller.processing.CoordinateWriter;
import com.envisprototype.view.processing.EnvisButton;
import com.envisprototype.view.processing.EnvisPApplet;
import com.envisprototype.view.processing.SensorSet;

public class SaveSetBtnListener extends AbstractEnvisButtonListener{
	ArrayList<SensorSet> envisSensors;
	String sensorsFileName;
	CoordinateWriter sensorWriter;
	
	public SaveSetBtnListener(EnvisPApplet envisPApplet, String sensorsFileName){
		this.envisSensors = envisPApplet.getEnvisSensors();
		this.sensorsFileName = sensorsFileName;
		sensorWriter = new CoordinateWriter(envisPApplet);
	}
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			sensorWriter.saveSetsToFile(sensorsFileName, envisSensors);
			eButton.setName(eButton.getEpApplet().getString(R.string.sets_saved));
		}
	}

}
