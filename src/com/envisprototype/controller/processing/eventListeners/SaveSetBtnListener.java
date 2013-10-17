package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.controller.processing.CoordinateWriter;
import com.envisprototype.view.processing.EnvisPApplet;

public class SaveSetBtnListener extends AbstractPlotBtnListener{
	String sensorsFileName;
	CoordinateWriter sensorWriter;
	
	public SaveSetBtnListener(EnvisPApplet envisPApplet, String sensorsFileName){
		this.sensorsFileName = sensorsFileName;
		sensorWriter = new CoordinateWriter(envisPApplet);
	}
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
			sensorWriter.saveSetsToFile(sensorsFileName, spApplet.getEnvisSensors());
			MapSetAssociationDBHelper.getSingletoneInstance(spApplet).
			associateEnvisSensorsWithMap(spApplet.getEnvisSensors(), spApplet.getEnvisMap().getMapId());
		}
	}

}
