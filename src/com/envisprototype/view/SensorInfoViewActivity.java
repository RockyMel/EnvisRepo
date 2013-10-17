package com.envisprototype.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.EnvisDBAdapter;
import com.envisprototype.controller.DeleteSensorButtonController;
import com.envisprototype.controller.SaveButtonController;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.sensor.SensorModel;
import com.envisprototype.view.processing.SetPlotPApplet;

public class SensorInfoViewActivity extends EnvisActivity {
	EditText id;
	EditText type;
	EditText brand;
	EditText name;
	EditText location;
	EditText notes;
	Button delete;
	Button save;
	//Button plotBtn;
	String sensorid;
	SensorInterface sensor;
	public static Boolean del = false;
	String flag;
	String setid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_info_view);
		sensorid = getIntent().getStringExtra("sensorid");
		setid= getIntent().getStringExtra("setid");
		Log.i("Yahhar Par 3", setid);
		flag=getIntent().getStringExtra("flag");
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		id = (EditText)findViewById(R.id.ID);
		type = (EditText)findViewById(R.id.TYPE);
		brand = (EditText)findViewById(R.id.BRAND);
		name = (EditText)findViewById(R.id.NAME);
		location = (EditText)findViewById(R.id.editText3);
		notes = (EditText)findViewById(R.id.NOTES);
		save = (Button)findViewById(R.id.Save);
		delete = (Button)findViewById(R.id.Delete);
		//plotBtn = (Button) findViewById(R.id.plotSensorBtn);

		save.setOnClickListener(new SaveButtonController(flag,id,name,location,type,brand,notes,delete,setid,this));
		
		if(flag.equals("new"))
			sensor=new SensorModel();
		else
			//sensor = SetListModel.getSingletonInstance().findSetById(setid).findSensorById(sensorid);
			sensor = SensorListModel.getSingletonInstance().findSensorById(sensorid);
		
			
		delete = (Button)findViewById(R.id.Delete);
		delete.setOnClickListener(new DeleteSensorButtonController(sensorid,setid,this));


		if(flag.equals("new"))
		{
			id.setText(sensorid);
			delete.setVisibility(Button.INVISIBLE);
		}
		else
		{
			id.setText(sensor.getId().toString());
			name.setText(sensor.getName());
			type.setText(sensor.getType());
			brand.setText(sensor.getBrand());
			notes.setText(sensor.getNotes());

		}



	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		super.onPause();
/*
		Location myloc=  new Location(LocationManager.NETWORK_PROVIDER);
		if(flag.equals("new"))
		{


			GPSTracker gps = new GPSTracker(this);
			double latitude = 0;
			double longitude = 0;

			if(gps.canGetLocation()){

				latitude = gps.getLatitude();
				longitude = gps.getLongitude();

				// \n is for new line
				//Toast.makeText(context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
			}else{
				// can't get location
				// GPS or Network is not enabled
				// Ask user to enable GPS/network in settings
				gps.showSettingsAlert();
			}

			myloc.setLatitude(latitude);
			myloc.setLongitude(longitude);

			sensor.setId(id.getText().toString());
			sensor.setName(name.getText().toString());
			sensor.setLocation(myloc);
			sensor.setBrand(brand.getText().toString());
			sensor.setNotes(notes.getText().toString());
			sensor.setType(type.getText().toString());
			sensor.setSetid(setid);
			
			SensorInterface temp = SensorListModel.getSingletonInstance().findSensorById(id.getText().toString());
			if(temp!=null)
			{
				SensorListModel.getSingletonInstance().removeSensor(temp);
				SensorListModel.getSingletonInstance().addSensor(sensor);
			}
			else
				SensorListModel.getSingletonInstance().addSensor(sensor);

		}


		if(!del){
			sensor.setId(id.getText().toString());
			sensor.setName(name.getText().toString());

			SensorListModel.getSingletonInstance().removeSensor(sensor);
			SensorListModel.getSingletonInstance().addSensor(sensor);
		}
		else
			del = false;


*/

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		EnvisDBAdapter.getSingletonInstance(this).replecateDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor_info_view, menu);
		return true;
	}

}
