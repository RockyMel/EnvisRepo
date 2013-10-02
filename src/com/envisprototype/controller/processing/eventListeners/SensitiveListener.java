package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.view.processing.EnvisButton;
import com.envisprototype.view.processing.SensorSet;
import com.envisprototype.view.processing.SetPlotPApplet;

public class SensitiveListener extends AbstractSetSetsListener{

	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			float tempX = eButton.getEpApplet().mouseX;
			float tempY = eButton.getEpApplet().mouseY;
			SensorSet setToAdd = new SensorSet(eButton.getEpApplet(),
					"dummy id",  tempX, tempY, 0);
			setToAdd.translateSensorsForFile(eButton.getEpApplet().getEnvisMap());
			if(ePlotApplet.isIfAddingSets()){
				eButton.getEpApplet().getEnvisSensors().add(setToAdd);
				
			}
			else{
				if(ePlotApplet.isIfRemovingSets()){
					for(int i = 0; i < eButton.getEpApplet().getEnvisSensors().size(); i++){
						if(eButton.getEpApplet().getEnvisSensors().get(i).isSameSpot(setToAdd)){
							eButton.getEpApplet().getEnvisSensors().remove(i);
							break;
						}
					}
				}
			}
			((SetPlotPApplet) eButton.getEpApplet()).getSaveSensorsBtn().
			setName(eButton.getEpApplet().getString(R.string.save_sets));
		}
	}

}
