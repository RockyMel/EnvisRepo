package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.processing.ThreeDVis;

public class Show3DMapBtnListener implements OnClickListener{
	
	Activity context;
	String mapID;

	public Show3DMapBtnListener(Context mapInfoViewActivity,
			String id) {
		// TODO Auto-generated constructor stub
		this.context = (Activity) mapInfoViewActivity;
		this.mapID = id;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Coordinates realCoorsMap = MapListModel.getSingletonInstance().findMapById(mapID).getRealCoordinates();
		Intent intent = new Intent(context, ThreeDVis.class);
		//intent.putExtra("coors", realCoorsMap);
		intent.putExtra("mapID", mapID);
		context.startActivity(intent);
	}

}
