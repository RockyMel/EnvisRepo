package com.envisprototype.controller.processing.dragListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.controller.processing.eventListeners.AbstractPlotBtnListener;
import com.envisprototype.view.processing.SensorSet;
import com.envisprototype.view.processing.PlotPApplet;
import com.envisprototype.view.processing.ZCoorSpinner;

public class ZCoorBtnListener extends AbstractPlotBtnListener{
	
	@Override
	public void handleEnvisDragEvent(EventObject e){
		super.handleEnvisDragEvent(e);
		if(ifHitTheButton()){
			if(spApplet == null){
				spApplet = (PlotPApplet)eButton.getEpApplet();
			}
			// moving the spinner
			if(eButton instanceof ZCoorSpinner){
				if( ((ZCoorSpinner) eButton).getInitX() < spApplet.mouseX && spApplet.getMAX_WIDTH() > spApplet.mouseX){
					eButton.setDefX(spApplet.mouseX-eButton.getDefH()/2);
					SensorSet set = spApplet.getEnvisSensors().get(spApplet.getCurrentIdToPlot());
					if(set != null){
						set.setZ(((ZCoorSpinner) eButton).calcZ(spApplet.getEnvisMap()));
						set.setRealZ(((ZCoorSpinner) eButton).calcZ(spApplet.getEnvisMap())+spApplet.getEnvisMap().getCOOR_Z()/2);
						spApplet.getOkBtn().setText(spApplet.getString(R.string.save));
					}
				}
			}
			
		}
	}

}