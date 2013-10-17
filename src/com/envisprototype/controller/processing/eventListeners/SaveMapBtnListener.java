package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.processing.EnvisPApplet;

public class SaveMapBtnListener extends AbstractEnvisButtonListener{

	//CoordinateWriter output;
	String mapFileName;
	public SaveMapBtnListener(EnvisPApplet epApplet, String mapFileName){
		//output = new CoordinateWriter(epApplet);
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
				Coordinates coorsToSave = eButton.getEpApplet().getEnvisMap().getRealCoors();
				MapLocalDBHelper.getSingletonInstance(eButton.getEpApplet()).
				saveCoorsForMap(eButton.getEpApplet().
						getEnvisMap().getMapId(), coorsToSave, eButton.getEpApplet().
						getEnvisMap().getCOOR_Z());
				eButton.getEpApplet().getEnvisMap().translateToMiddle();
				Log.i("edit","saved");
			}
			//eButton.getEpApplet().getEnvisMap().translateToMiddle();
		}
		}

	
	
	
}
