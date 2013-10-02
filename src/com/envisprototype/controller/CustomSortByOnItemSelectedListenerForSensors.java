package com.envisprototype.controller;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.view.SensorListActivity;
import com.envisprototype.view.model.SensorListAdapter;
import com.envisprototype.view.model.SortByAlph;
import com.envisprototype.view.model.SortByLoc;

public class CustomSortByOnItemSelectedListenerForSensors implements OnItemSelectedListener {

	public List<SensorInterface> sensors;
	public SensorListAdapter sla;
	public ListView lv;	
	Context context;
	
	public CustomSortByOnItemSelectedListenerForSensors(List<SensorInterface> sensors,
			SensorListAdapter sla, ListView lv,Context context) {
		super();
		this.context=context;
		this.sensors = sensors;
		this.sla = sla;
		this.lv = lv;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		if(parent.getItemAtPosition(pos).toString().equals("Alphabetical Order"))
			Collections.sort(sensors, new SortByAlph());
		else
			Collections.sort(sensors, new SortByLoc(context));
		
		sla = new SensorListAdapter(context,0,sensors);
		
		Log.i("test", sensors.toString());
		Log.i("test1",sla.toString() );
		SensorListActivity.updateadapter(sla);
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
