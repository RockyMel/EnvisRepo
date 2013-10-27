package com.envisprototype.view.processing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.envisprototype.R;
import com.envisprototype.controller.HistoricalThreeDController;
import com.envisprototype.controller.processing.eventListeners.FrontViewButtonListener;
import com.envisprototype.controller.processing.eventListeners.LeftSideViewButtonListener;
import com.envisprototype.controller.processing.eventListeners.PerspectiveSideBtnListener;
import com.envisprototype.controller.processing.eventListeners.RegenerateBarsBtnListener;
import com.envisprototype.controller.processing.eventListeners.RotateButtonListener;
import com.envisprototype.controller.processing.eventListeners.RotateScopeListener;
import com.envisprototype.controller.processing.eventListeners.TopViewButtnoListener;
import com.envisprototype.controller.processing.eventListeners.VisTypeBtnListener;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;

public class ThreeDVis extends EnvisPApplet{

	EnvisButton frontViewButton, leftSideViewButton, topViewButton, rotateButton,
	prospectiveSideButton, regenerateBarSetCoors, visTypeBtn; 
	//BarGraphSet barGraphSet;
	final boolean DRAW_WITH_SENSORS = true;
	String fromDate, toDate = null;
	public static String curDate;
//	private static HashMap<String, Float> sensorReadings = new HashMap<String, Float>();	


	public void setup(){
		super.setup();
		{
			Calendar tempCal = Calendar.getInstance();
			curDate = tempCal.get(Calendar.YEAR) + "-" + 
			tempCal.get(Calendar.MONTH) + "-" + tempCal.get(Calendar.DAY_OF_MONTH)+ " " 
					+ tempCal.get(Calendar.HOUR_OF_DAY) + ":" + tempCal.get(Calendar.MINUTE) + ":10";
		}
		// to visualise the map in 3D
		// by this time the map has already been copied from the model
		envisMap.setIf3D(true);
		envisMap.translateToMiddle();
		rotateButton = new EnvisButton(this, "Rotate");
		rotateButton.setPlace(DEF_BTN_X, height/30);
		rotateButton.addEventListener(new RotateButtonListener());
		frontViewButton = new EnvisButton(this, "Front View");
		frontViewButton.setPlace(DEF_BTN_X, 3*height/30);
		frontViewButton.addEventListener(new FrontViewButtonListener());
		leftSideViewButton = new EnvisButton(this, "Left Side");
		leftSideViewButton.setPlace(DEF_BTN_X, 5*height/30);
		leftSideViewButton.addEventListener(new LeftSideViewButtonListener());
		topViewButton = new EnvisButton(this, "Top View");
		topViewButton.setPlace(DEF_BTN_X, 7*height/30);
		topViewButton.addEventListener(new TopViewButtnoListener());
		prospectiveSideButton = new EnvisButton(this, "Perspective");
		prospectiveSideButton.setPlace(DEF_BTN_X, 9*height/30);
		prospectiveSideButton.addEventListener(new PerspectiveSideBtnListener());
		regenerateBarSetCoors = new EnvisButton(this, "Regenerate");
		regenerateBarSetCoors.setPlace(DEF_BTN_X, 15*height/30);
		regenerateBarSetCoors.addEventListener(new RegenerateBarsBtnListener());
		visTypeBtn = new EnvisButton(this, "Show spheres");
		visTypeBtn.setPlace(DEF_BTN_X, 13*height/30);
		visTypeBtn.addEventListener(new VisTypeBtnListener());
		RotateScopeListener.setIfTop(true);
		// need to get coordinates for the sensors
		{
			String sensorId;// setId;
			setIterator = envisSensors.keySet().iterator();
			while(setIterator.hasNext()){
				sensorId = setIterator.next();
//				sensorReadings.put(sensorId, 0f);
				if(!SensorListModel.getSingletonInstance().findSensorById(sensorId).isIfDefaultCoors()){
					envisSensors.get(sensorId).setRealX(SensorListModel.getSingletonInstance().findSensorById(sensorId).getX());
					envisSensors.get(sensorId).setRealY(SensorListModel.getSingletonInstance().findSensorById(sensorId).getY());
					envisSensors.get(sensorId).setRealZ(SensorListModel.getSingletonInstance().findSensorById(sensorId).getZ());
					envisSensors.get(sensorId).setX(SensorListModel.getSingletonInstance().findSensorById(sensorId).getX());
					envisSensors.get(sensorId).setY(SensorListModel.getSingletonInstance().findSensorById(sensorId).getY());
					envisSensors.get(sensorId).setZ(SensorListModel.getSingletonInstance().findSensorById(sensorId).getZ());
				}
				else{
					// set xyz as in set that this sensor belongs to
					//setId = SensorListModel.getSingletonInstance().findSensorById(sensorId).getSetid();
					List<SetInterface> asdf = SetListModel.getSingletonInstance().getSetList();
					envisSensors.get(sensorId).setRealX(SensorListModel.getSingletonInstance().findSensorById(sensorId).getX());
					envisSensors.get(sensorId).setRealY(SensorListModel.getSingletonInstance().findSensorById(sensorId).getY());
					envisSensors.get(sensorId).setRealZ(SensorListModel.getSingletonInstance().findSensorById(sensorId).getZ());
					envisSensors.get(sensorId).setX(SensorListModel.getSingletonInstance().findSensorById(sensorId).getX());
					envisSensors.get(sensorId).setY(SensorListModel.getSingletonInstance().findSensorById(sensorId).getY());
					envisSensors.get(sensorId).setZ(SensorListModel.getSingletonInstance().findSensorById(sensorId).getZ());
				}
				envisSensors.get(sensorId).translateSensorsForMap(envisMap);
				if(!extras.containsKey(getString(R.string.date_flag)))
					envisSensors.get(sensorId).startRealTime();

			}		    	
		}

		if(extras.containsKey(getString(R.string.sensors_to_vis_extra))){

			setIdFromAndroid = extras.getStringArrayList(getString(R.string.sensors_to_vis_extra));
			for(String setId: setIdFromAndroid){

				BarGraphSet barSetToAdd = new BarGraphSet(this, "", setId, 1);
				barGraphSetList.add(barSetToAdd);

				SphereGraphSet sphereGraphSet = new SphereGraphSet(this, "", setId, 1);
				sphereGraphList.add(sphereGraphSet);
			}
		}
		
		if(extras.containsKey(getString(R.string.date_flag))){
			 fromDate = extras.getString(getString(R.string.from_date_flag));
			 toDate = extras.getString(getString(R.string.to_date_flag));
			this.curDate = fromDate;
			HistoricalThreeDController toGetHistData = new HistoricalThreeDController(setIdFromAndroid, fromDate, toDate);
			toGetHistData.fetchData();
		}
		//    for(BarGraphSet barset: barGraphSetList){
		//    	//if(barset.getSensorID())
		//    	barset.startRealTime();
		//    }
	}
	public void draw(){
		super.draw();
		threeDDrawPreset(DRAW_WITH_SENSORS); // true - drawing with sensors
		//rotateButton.drawMe();
		frontViewButton.drawMe();
		leftSideViewButton.drawMe();
		topViewButton.drawMe();
		regenerateBarSetCoors.drawMe();
		prospectiveSideButton.drawMe();
		visTypeBtn.drawMe();
		text(curDate,width/2-textWidth(curDate), currentClick.getDefY());

	}

	@Override
	public void mouseDragged(){
		super.mouseDragged();
		rotateScope.fireEvent();
	}

	public void mouseReleased(){
		//rotateButton.fireEvent();
		frontViewButton.fireEvent();
		leftSideViewButton.fireEvent();
		topViewButton.fireEvent();
		regenerateBarSetCoors.fireEvent();
		prospectiveSideButton.fireEvent();
		visTypeBtn.fireEvent();
	}

	public float medianValue(ArrayList<Float> list){
		Collections.sort(list);
		return list.get(list.size()/2);
	}
//	public static HashMap<String, Float> getSensorReadings() {
//		return sensorReadings;
//	}
//	public static void setSensorReadings(HashMap<String, Float> sensorReadings) {
//		ThreeDVis.sensorReadings = sensorReadings;
//	}
	public EnvisButton getRegenerateBarSetCoors() {
		return regenerateBarSetCoors;
	}
	public void setRegenerateBarSetCoors(EnvisButton regenerateBarSetCoors) {
		this.regenerateBarSetCoors = regenerateBarSetCoors;
	}
}


