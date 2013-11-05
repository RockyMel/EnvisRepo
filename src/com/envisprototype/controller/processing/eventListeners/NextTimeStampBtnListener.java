package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;

import android.util.Log;

import com.envisprototype.model.processing.SensorReadingsModel;
import com.envisprototype.view.processing.BarGraphSet;
import com.envisprototype.view.processing.SphereGraphSet;
import com.envisprototype.view.processing.ThreeDVis;

	public class NextTimeStampBtnListener extends AbstractEnvisButtonListener{
		ThreeDVis tdPapplet = null;

		@Override
		public void handleEnvisClassEvent(EventObject e) {
			// TODO Auto-generated method stub
			super.handleEnvisClassEvent(e);
			if(this.tdPapplet == null && eButton.getEpApplet() instanceof ThreeDVis)
				this.tdPapplet =  (ThreeDVis) eButton.getEpApplet();
			if(ifHitTheButton()){
				SensorReadingsModel.getSingletonInstance().increaseIndex();
				for(BarGraphSet barSet: tdPapplet.getBarGraphSetList()){
					HashMap<String, Float>pair = SensorReadingsModel.getSingletonInstance().FindTimeReadingPairsForId(barSet.getSensorID());
					//Iterator<String> iterator = pair.keySet().iterator();
					int i = SensorReadingsModel.getSingletonInstance().getTimeIndex();
					String timeStamp = new String(SensorReadingsModel.getSingletonInstance().getTimeStamps().get(i));
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
				Iterator<String> iter = SensorReadingsModel.getSingletonInstance().getSensorReadings().keySet().iterator();
				while(iter.hasNext()){
					Log.i("structure size ", " str s = " + SensorReadingsModel.getSingletonInstance().getSensorReadings().size());
					String id = iter.next();
					Log.i("structure size ", "sensor id = " + id);
					Iterator<String> iterTimeSt = SensorReadingsModel.getSingletonInstance().getSensorReadings().get(id).keySet().iterator();
					Log.i("structure size ", " amount of timestamps for this sensor = " + SensorReadingsModel.getSingletonInstance().getSensorReadings().get(id).keySet().size());
//					while(iterTimeSt.hasNext()){
//						String timeStString = iterTimeSt.next();
//						Log.i("structure size ", " reading = " + SensorReadingsModel.getSingletonInstance().getSensorReadings().get(id).get(timeStString));
//					}
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