package com.envisprototype.controller;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.envisprototype.LocalDBHelper.SensorLocalDBHelper;
import com.envisprototype.LocalDBHelper.SetSensorAssociationLocalDBHelper;
import com.envisprototype.model.DBHelper.SensorInfoDBHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.sensor.SensorModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;

public class SaveButtonController implements OnClickListener {

	String flag;
	EditText id;
	EditText name;
	EditText brand;
	EditText notes;
	ImageButton delete;
	String setid;
	Context context;


//	public SaveButtonController(String flag, EditText id, EditText name,
//			EditText location,EditText type,EditText brand,
//			EditText notes,Button delete, String setid, Context context) {
//		// TODO Auto-generated constructor stub
//		this.flag=flag;
//		this.id=id;
//		this.name=name;
//		this.location=location;
//		this.type=type;
//		this.brand=brand;
//		this.notes=notes;
//		this.delete=delete;
//		this.context=context;
//		this.setid=setid;
//
//	}

	public SaveButtonController(String flag, EditText id, EditText name,
			EditText brand, EditText notes, ImageButton delete,
			String setid, Context context) {
		// TODO Auto-generated constructor stub
		this.flag = flag;
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.notes = notes;
		this.delete = delete;
		this.setid = setid;
		this.context = context;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub



		Location myloc=null;
		SetInterface set=SetListModel.getSingletonInstance().findSetById(setid);

		myloc=set.getLocation();
		Location location=new Location(LocationManager.NETWORK_PROVIDER);
		location.setLatitude(myloc.getLatitude());
		location.setLongitude(myloc.getLongitude());
		
		Log.i("sadadsf1", myloc.getLatitude()+" "+myloc.getLongitude());
		Log.i("sadadsf2", location.getLatitude()+" "+location.getLongitude());

		final SensorInterface sensor = new SensorModel();
		sensor.setId(id.getText().toString());
		sensor.setName(name.getText().toString());
		sensor.setLocation(location);
		sensor.setBrand(brand.getText().toString());
		sensor.setNotes(notes.getText().toString());
		Log.i("wqertytuyio", setid);
		sensor.setSetid(setid);
		
		SensorInterface temp = SensorListModel.getSingletonInstance().findSensorById(sensor.getId());

		if(temp!=null)
		{
//			SensorListModel.getSingletonInstance().removeSensor(temp);
//			SensorListModel.getSingletonInstance().addSensor(sensor);
			SensorListModel.getSingletonInstance().editSensor(sensor);
			Thread thread = new Thread()
			{
				@Override
				public void run() {
					System.out.println("asdsaD" + sensor.getBrand());
					SensorLocalDBHelper.getSingletonInstance(context).editSensor(sensor);
				}
			};
			thread.start();

		}
		else
		{
			SensorListModel.getSingletonInstance().addSensor(sensor);
		
			Log.i("jdsha.f", SensorListModel.getSingletonInstance().findSensorById(id.getText().toString()).getLocation()+"");
			
			Thread thread = new Thread()
			{
				@Override
				public void run() {
					System.out.println("asdsaD" + sensor.getBrand());
					SensorLocalDBHelper.getSingletonInstance(context).addSensor(sensor);
					SetSensorAssociationLocalDBHelper.getSingletonInstance(context).associateSensorWithSet(id.getText().toString(), setid);
					SensorInfoDBHelper.addSensor(sensor);
				}
			};
			thread.start();
			 
		}
		delete.setVisibility(Button.VISIBLE);

	}




}


