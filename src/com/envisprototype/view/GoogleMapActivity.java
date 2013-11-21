package com.envisprototype.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.envisprototype.R;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.model.GetSensorReadingTask;
import com.envisprototype.view.model.GoogleMapContentHelper;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends Activity {

	ArrayList<SetInterface> setlist;
	ArrayList<SensorInterface> tempsensorlist;
	private GoogleMap map;
	HashMap<Marker,SetInterface> hashy = new HashMap<Marker,SetInterface>();
	public static HashMap<String, String> readingsForSensorsforSets = new HashMap<String, String>();
	
	private LatLng LOCATION_SENSOR;
	private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
	
	public static String something = "Loading....";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		readingsForSensorsforSets.clear();
		setContentView(R.layout.activity_google_map);
		setlist = new ArrayList<SetInterface>(SetListModel.getSingletonInstance().getSetList());

		map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		

		for(SetInterface tempset: setlist){
			
			Log.i("SetNameONMAPS", tempset.getName());
			Double lat = (Double)tempset.getLocation().getLatitude();
			Double lng = (Double)tempset.getLocation().getLongitude();
			Log.i("longlat;",tempset.getName()+"  Latitude:" +lat + "Longitude:" + lng);
			LOCATION_SENSOR = new LatLng(lat,lng);
			MarkerOptions tempmarker = new MarkerOptions();
			tempmarker.position(LOCATION_SENSOR);
			tempmarker.title(tempset.getName());
			tempmarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow));
			tempmarker.snippet("TEST ME\nBLAHBLAHBLHA\nGAGAGAG\namshdbhkjas");
			ArrayList<SensorInterface> sensors = (ArrayList)SensorListModel.getSingletonInstance().getSensorListBySetID(tempset.getId());
			readingsForSensorsforSets.put(tempset.getId(), "");
			
			//Marker tempmarker_tt = map.addMarker(tempmarker);
			//tempmarker.
			
			//map.addMarker(tempmarker);
			
			hashy.put(map.addMarker(tempmarker), tempset);	
			//tasks.put(new GetSensorReadingTask(this, tempset),"w");
			
			
			
		}
		
		CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_SENSOR);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		update = CameraUpdateFactory.newLatLngZoom(MELBOURNE, 14);
		map.animateCamera(update);
		update = CameraUpdateFactory.newLatLngZoom(LOCATION_SENSOR, 14);
		map.animateCamera(update);
		map.setInfoWindowAdapter(new GoogleMapContentHelper(this, map, hashy) );
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.google_map, menu);
		return true;
	}

}