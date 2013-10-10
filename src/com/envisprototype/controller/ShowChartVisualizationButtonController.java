package com.envisprototype.controller;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.view.LineChart;

public class ShowChartVisualizationButtonController implements OnClickListener {

	Context context;
	List<String> setIds;
	List<String> sensorIds;
	int MODE;
	Calendar calfrom;
	Calendar calto;
	
	
	//FOR HISTORICAL
	public ShowChartVisualizationButtonController(
			Context context,
			List<String> setIds, List<String> sensorIds,int MODE,Calendar calfrom,Calendar calto) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.setIds = setIds;
		this.sensorIds = sensorIds;
		this.MODE = MODE;
		this.calfrom = calfrom;
		this.calto = calto;
		
		
		
	}

	//FOR REAL TIME
	public ShowChartVisualizationButtonController(
			Context context,
			List<String> setIds, List<String> sensorIds,int MODE) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.setIds = setIds;
		this.sensorIds = sensorIds;
		this.MODE = MODE;
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		Intent intent = new Intent(context,LineChart.class);
		view.getContext().startActivity(intent);
		
		
	}

}
