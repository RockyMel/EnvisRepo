package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.view.SensorInfoViewActivity;
import com.envisprototype.view.SetInfoViewActivity;

public class IdEnteredOKButtonController implements OnClickListener {

	EditText id;
	Activity activity;
	String setid;
	String mode;

	//Called By Sensor
	public IdEnteredOKButtonController(String mode, EditText id2,String setid, Activity activity){
		this.id=id2;
		this.activity=activity;
		this.setid=setid;
		this.mode=mode;
	}

	//Called By Set
	public IdEnteredOKButtonController(String mode2, EditText id2,
			Activity activity) {
		// TODO Auto-generated constructor stub
		this.mode=mode2;
		this.id=id2;
		this.activity=activity;

	}


	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(mode.equals("set"))
		{
			Intent intent=new Intent(view.getContext(),SetInfoViewActivity.class);
			//intent.putExtra("setid", setid);
			intent.putExtra("setid",id.getText().toString());
			intent.putExtra("flag","new");
			view.getContext().startActivity(intent);
			activity.finish();
		}
		else
		{
			Intent intent=new Intent(view.getContext(),SensorInfoViewActivity.class);
			intent.putExtra("setid", setid);
			intent.putExtra("sensorid",id.getText().toString());
			intent.putExtra("flag","new");
			view.getContext().startActivity(intent);
			activity.finish();
		}
	}

}
