package com.envisprototype.view.model;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.envisprototype.R;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;

public class ChartVisualizationSettingsListAdapter extends ArrayAdapter<String> {

	TextView setplussensor;
	
	public ChartVisualizationSettingsListAdapter(Context context, int resource,
			List<String> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(inflatedView==null){
			LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflatedView=inflater.inflate(R.layout.adapterforchartvisualizationsettinglist, null);

		}
		setplussensor = (TextView)inflatedView.findViewById(R.id.SetPlusSensor);
//
	String ID = getItem(position);
//		
//		Log.i("TESTSTST", ID);
		
		SensorInterface temp = SensorListModel.getSingletonInstance().findSensorById(ID);
		Log.i("TESTSTST1", temp.getId() +"  "+ temp.getSetid());
		Log.i("coors", temp.getId() +"  "+ temp.getId());
		Log.i("coors", temp.getId() +"  "+ temp.getX());
		Log.i("coors", temp.getId() +"  "+ temp.getY());
		Log.i("coors", temp.getId() +"  "+ temp.getZ());
		SetInterface tempset =SetListModel.getSingletonInstance().findSetById(temp.getSetid());
		Log.i("TESTSTST2", tempset.getId() +"  "+ tempset.getName());
		setplussensor.setText(tempset.getName()+"::"+temp.getName());
						
		return inflatedView;
	}

}
