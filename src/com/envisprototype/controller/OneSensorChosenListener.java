package com.envisprototype.controller;

import com.envisprototype.view.model.ChartVisualizationSettingsModel;

import android.content.Context;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class OneSensorChosenListener implements OnCheckedChangeListener{

	Context context;
	String ID;

	public OneSensorChosenListener(Context context, String childText) {
		super();
		this.context = context;
		this.ID = childText;
	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked)
		{
			if(ChartVisualizationSettingsModel.getSingletonInstance().isSensorPresent(ID)==false)
			{
				ChartVisualizationSettingsModel.getSingletonInstance().addSensorID(ID);
				Log.i("Onesensorchosen", ID + "added!!");
			}
		}
		else
		{
			ChartVisualizationSettingsModel.getSingletonInstance().removeSensorID(ID);
			Log.i("Onesensorchosen", ID + "removed!!");
		}


		Toast.makeText(context, ID, Toast.LENGTH_LONG).show();



	}

}
