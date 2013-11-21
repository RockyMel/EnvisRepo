package com.envisprototype.controller.processing.dragListeners;

import java.util.EventObject;
import java.util.HashMap;

import android.util.Log;

import com.envisprototype.controller.processing.eventListeners.AbstractEnvisButtonListener;
import com.envisprototype.model.processing.SensorReadingsModel;
import com.envisprototype.view.processing.BarGraphSet;
import com.envisprototype.view.processing.SphereGraphSet;
import com.envisprototype.view.processing.ThreeDVis;
import com.envisprototype.view.processing.ZCoorSpinner;

public class TimeStampBtnListener extends AbstractEnvisButtonListener{
	protected ZCoorSpinner spinner;
	ThreeDVis tdPapplet = null;

	@Override
	public void handleEnvisDragEvent(EventObject e){
		//super.handleEnvisDragEvent(e);
		super.handleEnvisClassEvent(e);
		if(this.tdPapplet == null && eButton.getEpApplet() instanceof ThreeDVis)
			this.tdPapplet =  (ThreeDVis) eButton.getEpApplet();
		if(this.spinner == null)
			this.spinner = (ZCoorSpinner) e.getSource();
		if(ifHitTheButton()){
			if( spinner.getInitX() < spinner.getEpApplet().mouseX &&
					spinner.getEpApplet().getMAX_WIDTH() > spinner.getEpApplet().mouseX){
				spinner.setDefX(spinner.getEpApplet().mouseX-spinner.getDefH()/2);
				int newIndex = (int) spinner.adjustValue(spinner.getEpApplet().getEnvisMap(),
						SensorReadingsModel.getSingletonInstance().getTimeStamps().size(), 0);
				if(newIndex >= SensorReadingsModel.getSingletonInstance().getTimeStamps().size())
					newIndex = SensorReadingsModel.getSingletonInstance().getTimeStamps().size()-1;
				SensorReadingsModel.getSingletonInstance().setTimeIndex(newIndex);




				for(BarGraphSet barSet: tdPapplet.getBarGraphSetList()){
					HashMap<String, Float>pair = SensorReadingsModel.getSingletonInstance().FindTimeReadingPairsForId(barSet.getSensorID());
					//Iterator<String> iterator = pair.keySet().iterator();
					String timeStamp = "No timestamp found";
					try{
						timeStamp = SensorReadingsModel.getSingletonInstance().getTimeStamps().get(newIndex);
					}catch(Exception timeStampException){
						Log.i("no timestamp", " No timestamp found");
						timeStampException.printStackTrace();
						return;
					}
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
					String timeStamp = SensorReadingsModel.getSingletonInstance().getTimeStamps().get(newIndex);
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

	public boolean ifHitTheButton(){
		float defX, defY, defW, defH;
		defX = spinner.getDefX();
		defY = spinner.getDefY();
		defW = spinner.getDefW();
		defH = spinner.getDefH();
		// TODO Auto-generated method stub
		if(spinner.getEpApplet().mouseX>=defX 
				&& spinner.getEpApplet().mouseY>=defY
				&& spinner.getEpApplet().mouseY<=defY+defH
				&& spinner.getEpApplet().mouseX<=defX+defW){
			return true;
		}
		else{
			return false;
		}
	}

}