package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.view.processing.SensorSet;
import com.envisprototype.view.processing.SetPlotPApplet;
import com.envisprototype.view.processing.ZCoorSpinner;

public class zCoorBtnListener extends AbstractPlotBtnListener{
	
	@Override
	public void handleEnvisDragEvent(EventObject e){
		super.handleEnvisDragEvent(e);
		if(ifHitTheButton()){
			if(spApplet == null){
				spApplet = (SetPlotPApplet)eButton.getEpApplet();
			}
			// moving the spinner
			if(eButton instanceof ZCoorSpinner){
				if( ((ZCoorSpinner) eButton).getInitX() < spApplet.mouseX && spApplet.getMAX_WIDTH() > spApplet.mouseX){
					eButton.setDefX(spApplet.mouseX-eButton.getDefH()/2);
					SensorSet set = spApplet.getEnvisSensors().get(spApplet.getCurrentIdToPlot());
					if(set != null){
						set.setZ(((ZCoorSpinner) eButton).calcZ(spApplet.getEnvisMap()));
						set.setRealZ(((ZCoorSpinner) eButton).calcZ(spApplet.getEnvisMap())+spApplet.getEnvisMap().getCOOR_Z()/2);
						Log.i("z", " realz = " + set.getRealZ());
						Log.i("z", " visz = " + set.getZ());
						spApplet.getOkBtn().setName(spApplet.getString(R.string.save_sets));
					}
				}
			}
			
		}
	}

}