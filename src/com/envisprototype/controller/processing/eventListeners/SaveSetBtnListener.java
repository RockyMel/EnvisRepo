package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;
import java.util.Iterator;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.MapSensorAssociationDBHelper;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.view.processing.EnvisSensor;
import com.envisprototype.view.processing.SensorSet;

public class SaveSetBtnListener extends AbstractPlotBtnListener{
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(spApplet.isIfAddingSets()){
				eButton.setText(eButton.getEpApplet().getString(R.string.save));
				spApplet.getRotateScope().setDefW(spApplet.getMAX_WIDTH()-spApplet.getRotateScope().getDefX());
				spApplet.setIfAddingSets(false);
			}
			else{
				eButton.setText(eButton.getEpApplet().getString(R.string.saved));
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
					Iterator<String> iterator = spApplet.getEnvisSensors().keySet().iterator();
					while(iterator.hasNext()){
						String tempSensorId = iterator.next();
						SensorSet sensor = spApplet.getEnvisSensors().get(tempSensorId);
						if(sensor instanceof EnvisSensor){
							MapSensorAssociationDBHelper.getSingletoneInstance(spApplet).
							associateSensorWithMap(sensor.getId(), sensor.getRealX(),
									sensor.getRealY(), sensor.getRealZ());
						}
					}
				}
			}).start();
		}
	}

}
