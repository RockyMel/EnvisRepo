package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.view.processing.EnvisButton;

public class IdBtnListener extends AbstractPlotBtnListener{

	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			Log.i("id", eButton.getBtnID() + " was chosen");
			spApplet.setCurrentIdToPlot(eButton.getBtnID());
			for(EnvisButton btn: spApplet.getIdButtons()){
				if(btn.getBtnID().equals(eButton.getBtnID())){
					btn.setColor(255, 0, 0);
				}
				else{
					btn.setColor(255, 255, 255);
				}
			}
		}
	}

}
