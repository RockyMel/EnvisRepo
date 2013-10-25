package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;

public class DeleteSensorButtonController implements OnClickListener {

	String id;
	Activity context;
	String setid;
	
	public DeleteSensorButtonController(String id,
			String setid, Context context) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.context=(Activity)context;
		this.setid=setid;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		SensorInterface sensor = SensorListModel.getSingletonInstance().findSensorById(id);
		
		SensorListModel.getSingletonInstance().removeSensor(sensor);
		
		//SensorInfoViewActivity.del=true;
		//SetInterface tempset=SetListModel.getSingletonInstance().findSetById(setid);
		//tempset.removeSensor(tempset.findSensorById(id));
		
		this.context.finish();
	}

}
