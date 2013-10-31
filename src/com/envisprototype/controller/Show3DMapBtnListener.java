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
	String fromstr;
	String tostr;
	Calendar from;
	Calendar to;
	public Show3DMapBtnListener(String id) {
		// TODO Auto-generated constructor stub
		this.mapId = id;
	}


	//FOR HISTORICAL
	public Show3DMapBtnListener(
			Context context, String mapId,
			ArrayList<String> setIds, ArrayList<String> sensorIds,int MODE,Calendar from,Calendar to) {
		// TODO Auto-generated constructor stub
		this.mapId = mapId;
		this.context = context;
		this.setIds = setIds;
		this.sensorIds = sensorIds;
		this.MODE = MODE;
		this.from = from;
		this.to = to;
//		fromstr = from.get(Calendar.YEAR) + "-" + from.get(Calendar.MONTH) + "-" + from.get(Calendar.DAY_OF_MONTH)+ " " + from.get(Calendar.HOUR_OF_DAY) + ":" + from.get(Calendar.MINUTE) + ":10";
//		tostr = to.get(Calendar.YEAR) + "-" + (to.get(Calendar.MONTH)+1) + "-" + to.get(Calendar.DAY_OF_MONTH)+ " " + to.get(Calendar.HOUR_OF_DAY) + ":" + to.get(Calendar.MINUTE) + ":10";
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
		if(MODE == 0){
			fromstr = from.get(Calendar.YEAR) + "-" + from.get(Calendar.MONTH) + "-" + from.get(Calendar.DAY_OF_MONTH)+ " " + from.get(Calendar.HOUR_OF_DAY) + ":" + from.get(Calendar.MINUTE) + ":10";
			tostr = to.get(Calendar.YEAR) + "-" + (to.get(Calendar.MONTH)+1) + "-" + to.get(Calendar.DAY_OF_MONTH)+ " " + to.get(Calendar.HOUR_OF_DAY) + ":" + to.get(Calendar.MINUTE) + ":10";
			intent.putExtra(v.getContext().getString(R.string.date_flag), v.getContext().getString(R.string.date_flag));
			intent.putExtra(v.getContext().getString(R.string.from_date_flag), fromstr);
			intent.putExtra(v.getContext().getString(R.string.to_date_flag), tostr);
		}
		context.startActivity(intent);
	}

}
