package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;
import java.util.HashMap;

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
				HashMap<String, Float>pair = SensorReadingsModel.getSingletonInstance().FindTimeReadingPairsForId(barSet.getSensorID());
				//Iterator<String> iterator = pair.keySet().iterator();
				int i = SensorReadingsModel.getSingletonInstance().getTimeIndex();
				String timeStamp = SensorReadingsModel.getSingletonInstance().getTimeStamps().get(i);
				if(pair != null && timeStamp != null){
					//if(timeStamp != null){
					ThreeDVis.curDate = timeStamp;
					Float reading = null;
					if(pair!=null)
						reading = pair.get(timeStamp);
					//Log.i("structure size ", "st s = " + SensorReadingsModel.getSingletonInstance().getSensorReadings());
					//if(reading != null)
					barSet.getBarGraphList().get(0).setReading(reading);
						}
			}
			for(SphereGraphSet sphereSet: tdPapplet.getSphereGraphList()){
				HashMap<String, Float>pair = SensorReadingsModel.getSingletonInstance().FindTimeReadingPairsForId(sphereSet.getSensorID());
				//Iterator<String> iterator = pair.keySet().iterator();
				int i = SensorReadingsModel.getSingletonInstance().getTimeIndex();
				String timeStamp = SensorReadingsModel.getSingletonInstance().getTimeStamps().get(i);
				if(pair != null && timeStamp != null){
					//if(timeStamp != null){
					ThreeDVis.curDate = timeStamp;
					Float reading = null;
					if(pair!=null)
						reading = pair.get(timeStamp);
					//Log.i("structure size ", "st s = " + SensorReadingsModel.getSingletonInstance().getSensorReadings());
					//if(reading != null)
					sphereSet.setReadingForSphere(reading);
						}
			}
		}
	}
}
