package com.envisprototype.controller;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.envisprototype.LocalDBHelper.SensorLocalDBHelper;
import com.envisprototype.LocalDBHelper.SetLocalDBHelper;
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
	EditText location;
	EditText type;
	EditText brand;
	EditText notes;
	Button delete;
	String setid;
	Context context;


	public SaveButtonController(String flag, EditText id, EditText name,
			EditText location,EditText type,EditText brand,
			EditText notes,Button delete, String setid, Context context) {
		// TODO Auto-generated constructor stub
		this.flag=flag;
		this.id=id;
		this.name=name;
		this.location=location;
		this.type=type;
		this.brand=brand;
		this.notes=notes;
		this.delete=delete;
		this.context=context;
		this.setid=setid;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub



		Location myloc=null;
		SetInterface set=SetListModel.getSingletonInstance().findSetById(setid);

//		Log.i("SETID@SAVEBUTTONCONTROLLER", setid);
//		Log.i("SETID@SAVEBUTTONCONTROLLER", set.getId());
//		Log.i("SETID@SAVEBUTTONCONTROLLER", set.getName());
//		Log.i("SETID@SAVEBUTTONCONTROLLER", set.getNotes());
//		Log.i("SETID@SAVEBUTTONCONTROLLER", set.getLocation().getLongitude()+"");
		myloc=set.getLocation();

		//Log.i("testing location1", myloc.getLongitude()+myloc.getLongitude()+"");

		final SensorInterface sensor = new SensorModel();

		sensor.setId(id.getText().toString());
		sensor.setName(name.getText().toString());
		sensor.setLocation(myloc);

		sensor.setBrand(brand.getText().toString());
		sensor.setNotes(notes.getText().toString());
		try{
		sensor.setType(Integer.parseInt(type.getText().toString()));
		}catch(NumberFormatException nfe){
			sensor.setType(0);
		}
		sensor.setSetid(setid);
		
		//SensorInterface temp = set.getSensor(id.getText().toString());
		SensorInterface temp = SensorListModel.getSingletonInstance().findSensorById(sensor.getId());

		if(temp!=null)
		{
			//set.removeSensor(temp);
			//set.addSensor(sensor);
			SensorListModel.getSingletonInstance().removeSensor(temp);
			SensorListModel.getSingletonInstance().addSensor(sensor);
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

			
			Thread thread = new Thread()
			{
				@Override
				public void run() {
					System.out.println("asdsaD" + sensor.getBrand());
					SensorLocalDBHelper.getSingletonInstance(context).addSensor(sensor);
					// add sensor set association
					SetSensorAssociationLocalDBHelper.getSingletonInstance(context).associateSensorWithSet(id.getText().toString(), setid);
					// also if set has already been plotted, set sensor's xyz to the one of the set
					SetInterface tempSet = SetLocalDBHelper.getSingletonInstance(context).findSetById(setid);
					SensorInterface sensorToPlot = SensorLocalDBHelper.getSingletonInstance(context).findSensorById(sensor.getId());
					sensorToPlot.setX(tempSet.getX());
					sensorToPlot.setY(tempSet.getY());
					sensorToPlot.setZ(tempSet.getZ());
					SensorLocalDBHelper.getSingletonInstance(context).editSensor(sensorToPlot);
				}
			};
			thread.start();
			 
		}
		delete.setVisibility(Button.VISIBLE);

	}




}


