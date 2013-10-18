package com.envisprototype.controller;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.R;
import com.envisprototype.view.processing.ThreeDVis;

public class Show3DMapBtnListener implements OnClickListener{
	String mapId;
	Context context;
	ArrayList<String> setIds;
	ArrayList<String> sensorIds;
	int MODE;
	Calendar calfrom;
	Calendar calto;
	public Show3DMapBtnListener(String id) {
		// TODO Auto-generated constructor stub
		this.mapId = id;
	}
	
	
	//FOR HISTORICAL
	public Show3DMapBtnListener(
			Context context, String mapId,
			ArrayList<String> setIds, ArrayList<String> sensorIds,int MODE,Calendar calfrom,Calendar calto) {
		// TODO Auto-generated constructor stub
		this.mapId = mapId;
		this.context = context;
		this.setIds = setIds;
		this.sensorIds = sensorIds;
		this.MODE = MODE;
		this.calfrom = calfrom;
		this.calto = calto;
		
		
		
	}

	//FOR REAL TIME
	public Show3DMapBtnListener(
			Context context, String mapId,
			ArrayList<String> setIds, ArrayList<String> sensorIds,int MODE) {
		// TODO Auto-generated constructor stub
		this.mapId = mapId;
		this.context = context;
		this.setIds = setIds;
		this.sensorIds = sensorIds;
		this.MODE = MODE;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Context context = v.getContext();
		Intent intent = new Intent(context, ThreeDVis.class);
		intent.putExtra(v.getContext().getString(R.string.map_id_extra), mapId);
		intent.putStringArrayListExtra(v.getContext().getString(R.string.sets_to_vis_extra), setIds);
		intent.putStringArrayListExtra(v.getContext().getString(R.string.sensors_to_vis_extra), sensorIds);
		// !!!!! add sets + sensors ids + coordinates
		//intent.putExtra(v.getContext().getString(R.string.set_coor_extra, value)
		context.startActivity(intent);
	}

}
