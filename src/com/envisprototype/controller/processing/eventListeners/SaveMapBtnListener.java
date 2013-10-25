package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.processing.EnvisPApplet;

public class SaveMapBtnListener extends AbstractEnvisButtonListener{

	//CoordinateWriter output;
	public SaveMapBtnListener(){
		//output = new CoordinateWriter(epApplet);
	}
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			// closing figure
			eButton.getEpApplet().getEnvisMap().closeFigure();
			if(eButton.getEpApplet().getEnvisMap().isIfCentered()){
				Coordinates coorsToSave = eButton.getEpApplet().getEnvisMap().getRealCoors();
				MapLocalDBHelper.getSingletonInstance(eButton.getEpApplet()).
				saveCoorsForMap(eButton.getEpApplet().getEnvisMap().getMapId(),
						coorsToSave, eButton.getEpApplet().getEnvisMap().getCOOR_Z());
				eButton.setName(eButton.getEpApplet().getString(R.string.saved));
				//eButton.getEpApplet().getEnvisMap().translateToMiddle();
			}
			//eButton.getEpApplet().getEnvisMap().translateToMiddle();
		}
		}

	
	
	
}
