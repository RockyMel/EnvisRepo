package com.envisprototype.controller;

import com.envisprototype.view.SensorInfoViewActivity;
import com.envisprototype.view.SetInfoViewActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class SetViewButtonController implements OnClickListener {

	String id;
	Context context;
	
	public SetViewButtonController(String id, Context context) {
		// TODO Auto-generated constructor stub
	this.context=context;
	this.id=id;
	
	
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(view.getContext(),SetInfoViewActivity.class);
		intent.putExtra("setid",id);
		intent.putExtra("flag", "exist");
		view.getContext().startActivity(intent);

		
	}

}
