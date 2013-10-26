package com.envisprototype.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.EnvisDBAdapter;
import com.envisprototype.controller.DeleteSensorButtonController;
import com.envisprototype.controller.SaveButtonController;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.sensor.SensorModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class NewSensorInfoViewActivity extends Activity{
	private EditText id;
	private TextView sensortype;
	private EditText brand;
	private EditText name;
	private EditText notes;

	private ImageView typeImage;

	private ImageButton delete;
	private ImageButton save;

	private GoogleMap map;

	private LatLng LOCATION_SENSOR;
	private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);


	String sensorid;
	SensorInterface sensor;
	public static Boolean del = false;
	String flag;
	String setid;
	int typercvd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_sensor_info_view);
		setid= getIntent().getStringExtra("setid");
		Log.i("nsiva", setid);
		flag=getIntent().getStringExtra("flag");
		if(flag.equals("new"))
			typercvd = Integer.parseInt(getIntent().getStringExtra("type"));
		else
			sensorid= getIntent().getStringExtra("sensorid");
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		id = (EditText)findViewById(R.id.ID);
		brand = (EditText)findViewById(R.id.BRAND);
		name = (EditText)findViewById(R.id.NAME);
		notes = (EditText)findViewById(R.id.NOTES);
		typeImage = (ImageView)findViewById(R.id.TypeImage);
		sensortype = (TextView)findViewById(R.id.SensorTypeInfo);
		SetInterface set=SetListModel.getSingletonInstance().findSetById(setid);

		Double lat = (Double)set.getLocation().getLatitude();
		Double lng = (Double)set.getLocation().getLongitude();

		LOCATION_SENSOR = new LatLng(lat,lng);
		map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.addMarker(new MarkerOptions().position(LOCATION_SENSOR).title("Your New Sensor").icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
		CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_SENSOR);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		update = CameraUpdateFactory.newLatLngZoom(MELBOURNE, 14);
		map.animateCamera(update);
		update = CameraUpdateFactory.newLatLngZoom(LOCATION_SENSOR, 14);
		map.animateCamera(update);

		if(typercvd == 1){
			typeImage.setImageResource(R.drawable.airquality);
			sensortype.setText("New Air Quality Sensor");
		}
		if(typercvd == 2){
			typeImage.setImageResource(R.drawable.humidity);
			sensortype.setText("New Humidity Sensor");
		}
		if(typercvd == 3)
		{
			typeImage.setImageResource(R.drawable.light);
			sensortype.setText("New Light Sensor");
		}
		if(typercvd == 4){
			typeImage.setImageResource(R.drawable.temp);
			sensortype.setText("New Temperature Sensor");	
		}
		if(typercvd == 5){
			typeImage.setImageResource(R.drawable.ir);
			sensortype.setText("New Motion Sensor");	
		}
		if(typercvd == 6){
			typeImage.setImageResource(R.drawable.waterlevel);
			sensortype.setText("New Water Level Sensor");
		}
		if(typercvd == 7){
			typeImage.setImageResource(R.drawable.others);
			sensortype.setText("New Sensor");
		}


		save = (ImageButton)findViewById(R.id.Save);
		delete = (ImageButton)findViewById(R.id.Delete);

		save.setOnClickListener(new SaveButtonController(flag,id,name,brand,notes,delete,setid,typercvd,this));

		if(flag.equals("new"))
			sensor=new SensorModel();
		else
			sensor = SensorListModel.getSingletonInstance().findSensorById(sensorid);


		delete = (ImageButton)findViewById(R.id.Delete);
		delete.setOnClickListener(new DeleteSensorButtonController(sensorid,setid,this));


		if(flag.equals("new"))
		{
			id.setText("S1ZZZ");
			sensor.setSetid(setid);
			//	type.setText(typercvd+"");
			delete.setVisibility(ImageButton.INVISIBLE);
		}
		else
		{
			id.setText(sensor.getId().toString());
			name.setText(sensor.getName());
			//type.setText(sensor.getType());
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
