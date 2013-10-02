package com.envisprototype.view.model;

import java.util.Comparator;

import android.content.Context;

import com.envisprototype.model.sensor.SensorInterface;

public class SortByLoc implements Comparator{

	Context context;

	public SortByLoc(Context context){
		this.context=context;
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		SensorInterface s1=(SensorInterface)o1;
		SensorInterface s2=(SensorInterface)o2;
		double lhslat = s1.getLocation().getLatitude();
		double lhslng = s1.getLocation().getLongitude();
		double rhslat = s2.getLocation().getLatitude();
		double rhslng = s2.getLocation().getLongitude();
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

		double lhsdist = distFrom (latitude,longitude,lhslat,lhslng);
		double rhsdist = distFrom (latitude,longitude,rhslat,rhslng);

		if(lhsdist>=rhsdist){
			return 1;

		}
		else
			return -1;

	}

	
	public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
				Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = earthRadius * c;

		int meterConversion = 1609;

		return new Double(dist * meterConversion).doubleValue();
	}
}
