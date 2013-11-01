package com.envisprototype.view.model;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.envisprototype.R;
import com.envisprototype.controller.MapViewButtonController;
import com.envisprototype.model.maps.MapInterface;



public class MapListAdapter extends ArrayAdapter<MapInterface>{

	Context context;
	public static final int MAP_BY_NAME = 1;
	public static final int MAP_BY_LOCATION = 2;
	public MapListAdapter(Context context, int textViewResourceId,List<MapInterface> objects) {
		super(context, textViewResourceId,objects);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(inflatedView==null){
			LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflatedView=inflater.inflate(R.layout.row_adapter_maps_with_location, null);

		}
		
		MapInterface map = getItem(position);
		TextView mapname =(TextView) inflatedView.findViewById(R.id.mapname);
		mapname.setText(map.getName());
		
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
		
		TextView distance =(TextView) inflatedView.findViewById(R.id.distance);
		double caldist = this.distFrom(latitude,longitude,map.getLocation().getLatitude(),map.getLocation().getLongitude());
		String caldiststr = String.valueOf(caldist);
		distance.setText(caldiststr);
		
		ImageButton editview = (ImageButton)inflatedView.findViewById(R.id.imageButton1);
		Log.i("adap", map.getId());
		Log.i("adap", map.getName());
		Log.i("adap", map.getRealCoordinates().toString());
		
		
		MapViewButtonController svbc=new MapViewButtonController(map.getId(),context);
		Activity act = (Activity)context;
		Bundle bundle = act.getIntent().getExtras();
		if(bundle != null){
			if(bundle.containsKey(context.getString(R.string.request_code))){
				int mode = bundle.getInt((context.getString(R.string.request_code)));
				if(mode == MapListAdapter.MAP_BY_NAME || mode == MapListAdapter.MAP_BY_LOCATION){
					svbc=new MapViewButtonController(map.getId(),context, mode);
				}
			}
		}
		editview.setOnClickListener(svbc);
				
		return inflatedView;
	}

	 public static float distFrom(double lat1, double lng1, double lat2, double lng2) {
		    double earthRadius = 3958.75;
		    double dLat = Math.toRadians(lat2-lat1);
		    double dLng = Math.toRadians(lng2-lng1);
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    double dist = earthRadius * c;

		    int meterConversion = 1609;

		    return new Double(dist * meterConversion).floatValue();
		    }
	
}
