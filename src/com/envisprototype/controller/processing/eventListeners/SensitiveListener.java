package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.view.processing.EnvisButton;
import com.envisprototype.view.processing.SensorSet;
import com.envisprototype.view.processing.SetPlotPApplet;

public class SensitiveListener extends AbstractPlotBtnListener{
	public SensitiveListener(){
		super();
	}

	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(spApplet.getCurrentIdToPlot() == null){
				Log.i("id", "id = null");
			}
			if(spApplet.isIfAddingSets()){
				Log.i("id", "id = " + spApplet.getCurrentIdToPlot());
				float tempX = eButton.getEpApplet().mouseX;
				float tempY = eButton.getEpApplet().mouseY;
				SensorSet setToAdd = new SensorSet(eButton.getEpApplet(),
				spApplet.getCurrentIdToPlot(),  tempX, tempY, 0);
				setToAdd.translateSensorsForFile(eButton.getEpApplet().getEnvisMap());
				eButton.getEpApplet().getEnvisSensors().put(spApplet.getCurrentIdToPlot(),setToAdd);
				//ePlotApplet.setIfAddingSets(false);
			}
		}
	}

}
