package com.envisprototype.view;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.envisprototype.controller.QRreaderButtonController;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.maps.MapModel;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.view.model.GPSTracker;
import com.envisprototype.zxing.integration.android.IntentIntegrator;
import com.envisprototype.zxing.integration.android.IntentResult;
import com.envisprototype.R;


public class AddMapActivity extends Activity {
	
	EditText id;
	EditText name;
	EditText location;
	EditText notes;
	ImageButton QRreader;
	Boolean flag;
	MapInterface map;
	Location myloc =  new Location(LocationManager.NETWORK_PROVIDER);
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
		QRreader = (ImageButton)findViewById(R.id.imageButton1);
		QRreader.setOnClickListener(new QRreaderButtonController(this));
		map = new MapModel();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//CODE TO SET THE TEXT FIELDS FROM QR READER
			
		
		
	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		//check we have a valid result
		if (scanningResult != null) {
			//get content from Intent Result
			String scanContent = scanningResult.getContents();
			//get format name of data scanned
			StringTokenizer st = new StringTokenizer(scanContent,";");
			 

			id.setText(st.nextElement().toString());
			name.setText(st.nextElement().toString());
	 
			//output to UI
			Toast toast = Toast.makeText(this, 
					scanContent, Toast.LENGTH_SHORT);
			toast.show();
		}
		else{
			//invalid scan data or scan canceled
			Toast toast = Toast.makeText(this, 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
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
