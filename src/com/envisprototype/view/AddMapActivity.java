package com.envisprototype.view;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.envisprototype.R;
import com.envisprototype.controller.DrawMapBtnListener;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.maps.MapModel;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.model.GPSTracker;


public class AddMapActivity extends Activity {
	
	EditText id;
	EditText name;
	EditText location;
	EditText notes;
	Boolean flag;
	MapInterface map;
	Location myloc =  new Location(LocationManager.NETWORK_PROVIDER);
	Button drawMapBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_map);

		init();

	}
	
	private void init() {
		// TODO Auto-generated method stub
		id = (EditText)findViewById(R.id.editText1);
		name  = (EditText)findViewById(R.id.editText2);
		location = (EditText)findViewById(R.id.editText3);
		notes = (EditText)findViewById(R.id.editText7);
		map = new MapModel();
		drawMapBtn = (Button) findViewById(R.id.draw_map_btn);
		drawMapBtn.setOnClickListener(new DrawMapBtnListener(this));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//CODE TO SET THE TEXT FIELDS FROM QR READER
			
		
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
            Intent data){
		if(requestCode == DrawMapBtnListener.DRAW_REQUEST_CODE){
			// now we have real coordinates from the map that we've just drawn
			Coordinates realMapCoordinates = (Coordinates) data.getExtras().get("map");
			Log.i("map",realMapCoordinates.toString());
			// add it to model
			map.setRealCoordinates(realMapCoordinates);
			// + upload to the cloud??
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
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
		
		map.setId(id.getText().toString());
		map.setName(name.getText().toString());
		map.setLocation(myloc);
		
		MapInterface temp = MapListModel.getSingletonInstance().findMapById(id.getText().toString());
		if(temp!=null)
			{
			MapListModel.getSingletonInstance().removeMap(temp);
			MapListModel.getSingletonInstance().addMap(map);
			}
		else
			MapListModel.getSingletonInstance().addMap(map);
		
		
			
		
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_map, menu);
		return true;
	}

}
