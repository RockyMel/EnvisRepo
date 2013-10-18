package com.envisprototype.controller;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;

public class OnSensorChosenToPlot implements OnCheckedChangeListener{
	
	String sensorId;
	
	public OnSensorChosenToPlot(String sensorId){
		this.sensorId = sensorId;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			SensorInterface sensorToPlot = SensorListModel.getSingletonInstance().findSensorById(sensorId);
			sensorToPlot.setIftoPlot(isChecked);
	}

}
