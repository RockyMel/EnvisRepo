package com.envisprototype.controller;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.model.DBHelper.MapInfoDBHelper;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.maps.MapModel;
import com.envisprototype.view.model.GPSTracker;

public class SaveMapToAddBtnListener implements OnClickListener{
	
	Context context;
	MapInterface map;
	Location myloc;
	boolean ifAdd; // true - for add, false for edit
	TextView id;
	EditText name;
	
	public SaveMapToAddBtnListener(Context context, boolean ifAdd, TextView id, EditText name){
		this.context = context;
		this.ifAdd = ifAdd;
		this.map = new MapModel();
		this.myloc =  new Location(LocationManager.NETWORK_PROVIDER);
		this.id = id;
		this.name = name;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		GPSTracker gps = new GPSTracker(context);
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
//		CoordinatesReader coorReader = new CoordinatesReader(context);
//		Coordinates realCoors = coorReader.prepareMapCoordinates("map.txt");
//		Log.i("on Res add map act", realCoors.toString());
//		map.setRealCoordinates(realCoors);
		
		myloc.setLatitude(latitude);
		myloc.setLongitude(longitude);
		
		map.setId(id.getText().toString());
		map.setName(name.getText().toString());
		map.setLocation(myloc);
		if(ifAdd){ //adding
			Thread thread = new Thread()
			{
				@Override
				public void run() {
					MapLocalDBHelper.getSingletonInstance(context).addMap(map);
					MapInfoDBHelper.addMap(map);
				}
			};
			thread.start();
		}
		else{
			// getting coors from model
			map.setRealCoordinates(MapListModel.getSingletonInstance().findMapById(map.getId()).getRealCoordinates());
			map.setzCoordinate(MapListModel.getSingletonInstance().findMapById(map.getId()).getzCoordinate());
			MapLocalDBHelper.getSingletonInstance(context).editMap(map);
			Thread thread = new Thread()
			{
				@Override
				public void run() {
					
					MapInfoDBHelper.editMap(map);
				}
			};
			thread.start();
			//MapListModel.getSingletonInstance().editMap(map);
		}
		
		
			
		Log.i("on res", Integer.toString(MapListModel.getSingletonInstance().getMapList().size()));
	
	}

}
