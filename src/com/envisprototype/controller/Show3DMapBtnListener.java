package com.envisprototype.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.view.processing.ThreeDVis;

public class Show3DMapBtnListener implements OnClickListener{
	String mapID;

	public Show3DMapBtnListener(String id) {
		// TODO Auto-generated constructor stub
		this.mapID = id;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Context context = v.getContext();
		Intent intent = new Intent(context, ThreeDVis.class);
		intent.putExtra("mapID", mapID);
		context.startActivity(intent);
	}

}
