package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;
import java.util.TreeMap;

import com.envisprototype.model.processing.SensorReadingsModel;
import com.envisprototype.view.processing.BarGraphSet;
import com.envisprototype.view.processing.SphereGraphSet;
import com.envisprototype.view.processing.ThreeDVis;

public class PrevTimeStampBtnListener extends AbstractEnvisButtonListener{
	ThreeDVis tdPapplet = null;

	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(this.tdPapplet == null && eButton.getEpApplet() instanceof ThreeDVis)
			this.tdPapplet =  (ThreeDVis) eButton.getEpApplet();
		if(ifHitTheButton()){
			SensorReadingsModel.getSingletonInstance().decreaseIndex();
			for(BarGraphSet barSet: tdPapplet.getBarGraphSetList()){
				TreeMap<String, Float>pair = SensorReadingsModel.getSingletonInstance().FindTimeReadingPairsForId(barSet.getSensorID());
				//Iterator<String> iterator = pair.keySet().iterator();
				int i = SensorReadingsModel.getSingletonInstance().getTimeIndex();
				String timeStamp = SensorReadingsModel.getSingletonInstance().getTimeStamps().get(i);
				ThreeDVis.curDate = timeStamp;
				barSet.getBarGraphList().get(0).setReading(pair.get(timeStamp));
			}
			for(SphereGraphSet sphereSet: tdPapplet.getSphereGraphList()){
				TreeMap<String, Float>pair = SensorReadingsModel.getSingletonInstance().FindTimeReadingPairsForId(sphereSet.getSensorID());
				//Iterator<String> iterator = pair.keySet().iterator();
				int i = SensorReadingsModel.getSingletonInstance().getTimeIndex();
				String timeStamp = SensorReadingsModel.getSingletonInstance().getTimeStamps().get(i);
				ThreeDVis.curDate = timeStamp;
				sphereSet.setReadingForSphere(pair.get(timeStamp));
			}
		}
	}
}
