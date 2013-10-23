package com.envisprototype.controller;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.envisprototype.LocalDBHelper.MapSensorAssociationDBHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;

public class DefaultXYZSensorBtnListener implements OnClickListener{
	String sensorId;
	public DefaultXYZSensorBtnListener(String sensorId){
		this.sensorId = sensorId;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SensorInterface sensor = SensorListModel.getSingletonInstance().findSensorById(sensorId);
		SetInterface setToGetCoors = SetListModel.getSingletonInstance().findSetById(sensor.getSetid());
		if(setToGetCoors.getMapID() != null){
			if(sensor.getX() == setToGetCoors.getX() &&
					sensor.getY() == setToGetCoors.getY() &&
					sensor.getZ() == setToGetCoors.getZ()){
				Toast.makeText(v.getContext(), "Already defaulted",
						   Toast.LENGTH_LONG).show();
			}
			else{
				MapSensorAssociationDBHelper.getSingletoneInstance(v.getContext()).
				associateSensorWithMap(sensorId, setToGetCoors.getX(), setToGetCoors.getY(), setToGetCoors.getZ());			
				sensor.setIfDefaultCoors(true);
			}
			
		}
		else{
			Toast.makeText(v.getContext(), "Sensor is not plotted",
					   Toast.LENGTH_LONG).show();
		}
	}

}
