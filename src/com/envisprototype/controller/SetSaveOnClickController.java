package com.envisprototype.controller;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.envisprototype.LocalDBHelper.SetLocalDBHelper;
import com.envisprototype.model.DBHelper.SetInfoDBHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.model.set.SetModel;
import com.envisprototype.view.model.GPSTracker;

public class SetSaveOnClickController implements OnClickListener {

	String setid;
	List<SensorInterface> sli;
	ImageButton delete;
	String flag;
	SetInterface set;
	TextView id;
	EditText name;
	EditText notes;
	Location location;
	Context context;
	SetListInterface temp;
	ImageButton add;

	//	public SetSaveOnClickController(SetInterface set, String setid, EditText name, EditText notes, List<SensorInterface> sli,
	//			Button delete, Button add, String flag,Location location, Context context) {
	//		// TODO Auto-generated constructor stub
	//		this.set = set;
	//		this.setid=setid;
	//		this.sli=sli;
	//		this.delete = delete;
	//		this.name=name;
	//		this.notes=notes;
	//		this.location=location;
	//		this.context=context;
	//		this.flag=flag;
	//		this.add=add;
	//
	//	}

	public SetSaveOnClickController(SetInterface set, TextView id2, String setid,
			EditText name, EditText notes, 
			ImageButton delete, ImageButton add, String flag,
			Location location, Context context) {
		// TODO Auto-generated constructor stub
		this.set = set;
		this.setid=setid;
		this.delete = delete;
		this.name=name;
		this.notes=notes;
		this.location=location;
		this.context=context;
		this.flag=flag;
		this.add=add;
		this.id=id2;
	}


	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(flag.equals("new"))
		{
			flag="exist";
			set=new SetModel();
			//set.setId(setid);
			set.setId(id.getText().toString());
			//Log.i("qwertyu", setid);
			set.setName(name.getText().toString());
			set.setNotes(notes.getText().toString());
			delete.setVisibility(ImageButton.VISIBLE);
			add.setVisibility(ImageButton.VISIBLE);

			GPSTracker gps = new GPSTracker(context);
			double latitude = 0;
			double longitude = 0;
			if(gps.canGetLocation())
			{
				latitude = gps.getLatitude();
				longitude = gps.getLongitude();
			}
			else
			{
				gps.showSettingsAlert();
			}

			location.setLatitude(latitude);
			location.setLongitude(longitude);
			set.setLocation(location);
			
			SetListModel.getSingletonInstance().addSet(set);
			//Log.i("hkg",SetListModel.getSingletonInstance().findSetById(setid)+"");
			Thread thread = new Thread()
			{
				@Override
				public void run() {
					//System.out.println("asdsaD" + sensor.getBrand());
					SetLocalDBHelper.getSingletonInstance(context).addSet(set);
					SetInfoDBHelper.addSet(set);
				}
			};
			thread.start();

		}
		else
		{
			// ADD CODE TO SAVE CHANGES
			set.setName(name.getText().toString());
			set.setNotes(notes.getText().toString());
			Thread thread = new Thread()
			{
				@Override
				public void run() {
					//System.out.println("asdsaD" + sensor.getBrand());
					SetLocalDBHelper.getSingletonInstance(context).editSet(set);
					SetInfoDBHelper.editSet(set);
				}
			};
			thread.start();
		}
	}

}
