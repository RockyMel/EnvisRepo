package com.envisprototype.view;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.envisprototype.R;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends Activity {

	ArrayList<SetInterface> setlist;
	ArrayList<SensorInterface> tempsensorlist;
	private GoogleMap map;

	private LatLng LOCATION_SENSOR;
	private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_map);
		setlist = new ArrayList<SetInterface>(SetListModel.getSingletonInstance().getSetList());

		map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		

		SetInterface tempset = null;
		for(int i=0;i<setlist.size();i++){
			
			tempset = setlist.get(i);
			Log.i("SetNameONMAPS", setlist.get(i).getName());
			Double lat = (Double)setlist.get(i).getLocation().getLatitude();
			Double lng = (Double)setlist.get(i).getLocation().getLongitude();
			Log.i("longlat;", i +" "+setlist.get(i).getName()+"  Latitude:" +lat + "Longitude:" + lng);
			LOCATION_SENSOR = new LatLng(lat,lng);
			map.addMarker(new MarkerOptions().position(LOCATION_SENSOR).title(setlist.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
				
			
			
		}
		
		CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_SENSOR);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		update = CameraUpdateFactory.newLatLngZoom(MELBOURNE, 14);
		map.animateCamera(update);
		update = CameraUpdateFactory.newLatLngZoom(LOCATION_SENSOR, 14);
		map.animateCamera(update);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.google_map, menu);
		return true;
	}

}
