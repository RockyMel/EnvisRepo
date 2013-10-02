package com.envisprototype.controller;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.envisprototype.model.DBHelper.SetInfoDBHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.model.set.SetModel;
import com.envisprototype.view.InputIDActivity;
import com.envisprototype.view.model.GPSTracker;

public class SetSaveOnClickController implements OnClickListener {

	String setid;
	List<SensorInterface> sli;
	Button delete;
	String flag;
	SetInterface set;
	EditText name;
	EditText notes;
	Location location;
	Context context;
	SetListInterface temp;
	Button add;

	public SetSaveOnClickController(SetInterface set, String setid, EditText name, EditText notes, List<SensorInterface> sli,
			Button delete, Button add, String flag,Location location, Context context) {
		// TODO Auto-generated constructor stub
		this.set = set;
		this.setid=setid;
		this.sli=sli;
		this.delete = delete;
		this.name=name;
		this.notes=notes;
		this.location=location;
		this.context=context;
		this.flag=flag;
		this.add=add;

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(flag.equals("new"))
		{
			flag="exist";
			set=new SetModel();
			set.setId(setid);
			set.setName(name.getText().toString());
			set.setNotes(notes.getText().toString());
			delete.setVisibility(Button.VISIBLE);
			add.setVisibility(Button.VISIBLE);
			GPSTracker gps = new GPSTracker(context);
			double latitude = 0;
			double longitude = 0;
			if(gps.canGetLocation()){

				latitude = gps.getLatitude();
				longitude = gps.getLongitude();

				//Toast.makeText(context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
			}else{
				// can't get location
				// GPS or Network is not enabled
				// Ask user to enable GPS/network in settings
				gps.showSettingsAlert();
			}

			location.setLatitude(latitude);
			location.setLongitude(longitude);
			Log.i("asljd", location.getLatitude() + " " + location.getLongitude());
			set.setLocation(location);
			SetListModel.getSingletonInstance().addSet(set);

			Thread thread = new Thread()
			{
				@Override
				public void run() {
					//System.out.println("asdsaD" + sensor.getBrand());
					SetInfoDBHelper.addSet(set);
				}
			};
			thread.start();

		}
		else
		{
			//set.setId(setid);
			set.setName(name.getText().toString());
			set.setNotes(notes.getText().toString());

		}
	}

}
