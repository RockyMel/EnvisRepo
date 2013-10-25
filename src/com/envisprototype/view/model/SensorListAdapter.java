package com.envisprototype.view.model;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.envisprototype.R;
import com.envisprototype.controller.SensorViewButtonController;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.set.SetListModel;


public class SensorListAdapter extends ArrayAdapter<SensorInterface> {
	
	Context context;
	public SensorListAdapter(Context context, int textViewResourceId,List<SensorInterface> objects) {
		super(context, textViewResourceId,objects);
		this.context=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(inflatedView==null){
			LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflatedView=inflater.inflate(R.layout.row_adapter_sensors_with_location, null);

		}
		
		SensorInterface sensor = getItem(position);
		TextView sensorname =(TextView) inflatedView.findViewById(R.id.sensorname);
		sensorname.setText(sensor.getName());
		Log.i("chkthisout1", sensor.getName()+"");
		
		Log.i("chkthisout2", sensor.getLocation()+"");
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
		//Log.i("teststst", sensor.getLocation().getLongitude()+"");
		//double caldist = this.distFrom(latitude,longitude,sensor.getLocation().getLatitude(),sensor.getLocation().getLongitude());
		double caldist = this.distFrom(latitude, longitude, SetListModel.getSingletonInstance().findSetById(sensor.getSetid()).getLocation().getLatitude(), SetListModel.getSingletonInstance().findSetById(sensor.getSetid()).getLocation().getLatitude());
		String caldiststr = String.valueOf(caldist);
		distance.setText(caldiststr);
		
		ImageButton editview = (ImageButton)inflatedView.findViewById(R.id.imageButton1);
		Log.i("234567", sensor.getId());
		Log.i("234567", context.toString());
		
		//new SensorViewButtonController(sensor.getId(),sensor.getId(),context);
		editview.setOnClickListener(new SensorViewButtonController(sensor.getId(),sensor.getSetid(),context));
				
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
