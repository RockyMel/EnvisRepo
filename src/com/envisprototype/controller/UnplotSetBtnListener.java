package com.envisprototype.controller;

import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.LocalDBHelper.MapSensorAssociationDBHelper;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;

public class UnplotSetBtnListener implements OnClickListener{
	
	String setId;

	public UnplotSetBtnListener(String setid) {
		// TODO Auto-generated constructor stub
		this.setId = setid;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MapSetAssociationDBHelper.getSingletoneInstance(v.getContext()).removeAssociation(setId);
		List<SensorInterface> sensorsToUnplot = SensorListModel.getSingletonInstance().getSensorListBySetID(setId);
		for(SensorInterface sensor: sensorsToUnplot){
			MapSensorAssociationDBHelper.getSingletoneInstance(v.getContext()).removeAssociation(sensor.getId());
		}
	}

}
