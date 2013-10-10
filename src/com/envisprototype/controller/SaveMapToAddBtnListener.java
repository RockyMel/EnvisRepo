package com.envisprototype.controller;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.maps.MapModel;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.model.GPSTracker;

public class SaveMapToAddBtnListener implements OnClickListener{
	
	Context context;
	MapInterface map;
	Location myloc;
	boolean ifAdd; // true - for add, false for edit
	EditText id, name;
	
	public SaveMapToAddBtnListener(Context context, boolean ifAdd, EditText id, EditText name){
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
		CoordinatesReader coorReader = new CoordinatesReader(context);
		Coordinates realCoors = coorReader.prepareMapCoordinates("map.txt");
		Log.i("on Res add map act", realCoors.toString());
		map.setRealCoordinates(realCoors);
		
		myloc.setLatitude(latitude);
		myloc.setLongitude(longitude);
		
		map.setId(id.getText().toString());
		map.setName(name.getText().toString());
		map.setLocation(myloc);
		if(ifAdd) //adding
			MapLocalDBHelper.getSingletonInstance(context).addMap(map);
		else{
			MapLocalDBHelper.getSingletonInstance(context).editMap(map);
			//MapListModel.getSingletonInstance().editMap(map);
		}
		
		
			
		Log.i("on res", Integer.toString(MapListModel.getSingletonInstance().getMapList().size()));
	
	}

}
