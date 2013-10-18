package com.envisprototype.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.view.SensorInfoViewActivity;


public class SensorViewButtonController implements OnClickListener {

	String id;
	Context context;
	String setid;
	
	public SensorViewButtonController(String id, String setid, Context context) {
		this.id=id;
		this.context=context;
		this.setid=setid;
	}

	@Override
	public void onClick(View view) {

		Intent intent=new Intent(view.getContext(),SensorInfoViewActivity.class);
		intent.putExtra("sensorid",id);
		intent.putExtra("flag", "exist");
		Log.i("Yahaan Par 2", setid);
		intent.putExtra("setid",setid);
		
		view.getContext().startActivity(intent);
	}

}
