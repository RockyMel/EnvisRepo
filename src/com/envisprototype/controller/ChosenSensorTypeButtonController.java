package com.envisprototype.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.view.NewSensorInfoViewActivity;

public class ChosenSensorTypeButtonController implements OnClickListener{

	
	
	private Activity activity;
	private String setid;
	private int type;
	public ChosenSensorTypeButtonController(String setid, int type,
			Activity activity) {
		// TODO Auto-generated constructor stub
		this.setid=setid;
		this.type=type;
		this.activity=activity;
		
		
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(view.getContext(),NewSensorInfoViewActivity.class);
		intent.putExtra("setid", setid);
		intent.putExtra("type", type+"");
		intent.putExtra("flag","new");
		view.getContext().startActivity(intent);
		activity.finish();
	}

}
