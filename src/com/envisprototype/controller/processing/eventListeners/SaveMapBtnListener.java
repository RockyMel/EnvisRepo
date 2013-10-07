package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.controller.processing.CoordinateWriter;
import com.envisprototype.view.processing.EnvisPApplet;

public class SaveMapBtnListener extends AbstractEnvisButtonListener{

	CoordinateWriter output;
	String mapFileName;
	public SaveMapBtnListener(EnvisPApplet epApplet, String mapFileName){
		output = new CoordinateWriter(epApplet);
		this.mapFileName = mapFileName;
	}
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			// closing figure
			eButton.getEpApplet().getEnvisMap().closeFigure();
			if(eButton.getEpApplet().getEnvisMap().isIfCentered()){
				output.saveMapToFile(mapFileName);
				eButton.getEpApplet().getEnvisMap().translateToMiddle();
				Log.i("edit","saved");
			}
			//eButton.getEpApplet().getEnvisMap().translateToMiddle();
		}
		}

	
	
	
}
