package com.envisprototype.controller;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.envisprototype.R;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.view.processing.PlotPApplet;

public class PlotSensorsBtnListener implements OnClickListener{
	ArrayList<String> sensorIds;
	String mapId;

	public PlotSensorsBtnListener(ArrayList<String> sensorIds, String mapId){
		this.sensorIds = sensorIds;
		this.mapId = mapId;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("plotting btn pressed");
		ArrayList<String> tempSensorIds = new ArrayList<String>();
		for(String id: sensorIds){
			tempSensorIds.add(id);
		}
		Iterator<String> iterator = tempSensorIds.iterator();
		while(iterator.hasNext()){
			String id = iterator.next();
			if(!SensorListModel.getSingletonInstance().findSensorById(id).isIftoPlot()){
				iterator.remove();
			}
		}
		if(tempSensorIds.size() > 0){
			Intent intent = new Intent(v.getContext(),PlotPApplet.class);
			intent.putExtra(v.getContext().getString(R.string.map_id_extra), mapId);
			intent.putExtra(v.getContext().getString(R.string.flags),
					v.getContext().getString(R.string.plot_flag_extra));
			intent.putStringArrayListExtra(v.getContext().getString(R.string.sensor_id_extra), tempSensorIds);
			v.getContext().startActivity(intent);
		}
		else{
			Toast toast = Toast.makeText(v.getContext(), "Select sensors first", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}