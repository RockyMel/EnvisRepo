package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import processing.core.PApplet;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.processing.EnvisPApplet;

public class SaveSetBtnListener extends AbstractPlotBtnListener{
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(spApplet.isIfAddingSets()){
				eButton.setName(eButton.getEpApplet().getString(R.string.save_sets));
				spApplet.getRotateScope().setDefW(spApplet.getMAX_WIDTH()-spApplet.getRotateScope().getDefX());
				spApplet.setIfAddingSets(false);
			}
			else{
				eButton.setName(eButton.getEpApplet().getString(R.string.sets_saved));
			}
		//	Coordinates coorsToSave = spApplet.getEnvisMap().getRealCoors();
//			MapLocalDBHelper.getSingletonInstance(spApplet).
//			saveCoorsForMap(spApplet.getEnvisMap().getMapId(), coorsToSave, spApplet.getEnvisMap().getCOOR_Z());
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					MapSetAssociationDBHelper.getSingletoneInstance(spApplet).
					associateEnvisSensorsSetsWithMap(spApplet.getEnvisSensors(), spApplet.getEnvisMap().getMapId());
				}
			}).start();
		}
	}

}
