package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.model.LocalDBHelper.SensorLocalDBHelper;
import com.envisprototype.model.LocalDBHelper.SetSensorAssociationLocalDBHelper;
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
		if(!Checks.isOnline(v.getContext()))
			return;
		// TODO Auto-generated method stub
		Log.i("del", id);
		SensorInterface sensor = SensorListModel.getSingletonInstance().findSensorById(id);
		
		SensorListModel.getSingletonInstance().removeSensor(sensor);
		SensorLocalDBHelper.getSingletonInstance(context).removeSensor(sensor);
		SetSensorAssociationLocalDBHelper.getSingletonInstance(context).removeAssociation(id);
		//SensorInfoViewActivity.del=true;
		//SetInterface tempset=SetListModel.getSingletonInstance().findSetById(setid);
		//tempset.removeSensor(tempset.findSensorById(id));
		
		this.context.finish();
	}

}
