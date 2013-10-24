package com.envisprototype.view.model;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.envisprototype.R;
import com.envisprototype.controller.SetViewButtonController;
import com.envisprototype.controller.onSetChosenToPlot;
import com.envisprototype.model.set.SetInterface;

public class SetListAdapter extends ArrayAdapter<SetInterface>  {

	Context context;
	String mapId;
	public SetListAdapter(Context context, int textViewResourceId,
			List<SetInterface> objects, String mapId) {
		super(context, textViewResourceId, objects);
		this.context=context;
		this.mapId = mapId;
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(inflatedView==null){
			LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflatedView=inflater.inflate(R.layout.setlistactivityadapter, null);

		}
		
		SetInterface set = getItem(position);
		TextView setid =(TextView) inflatedView.findViewById(R.id.ID);
		setid.setText(set.getId());
		
		TextView setname =(TextView) inflatedView.findViewById(R.id.NAME);
		setname.setText(set.getName());
		
		CheckBox toPlotCB = (CheckBox) inflatedView.findViewById(R.id.plotCB);
		toPlotCB.setOnCheckedChangeListener(new onSetChosenToPlot(set.getId()));
		if(mapId == null)
			toPlotCB.setVisibility(Button.INVISIBLE);
		if(set.isIftoPlot())
			toPlotCB.setChecked(true);
		
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
		
		TextView distance =(TextView) inflatedView.findViewById(R.id.DISTANCE);
		//Log.i("teststst", sensor.getLocation().getLongitude()+"");
		double caldist = this.distFrom(latitude,longitude,set.getLocation().getLatitude(),set.getLocation().getLongitude());
		String caldiststr = String.valueOf(caldist);
		distance.setText(caldiststr);
		
		Button editview = (Button)inflatedView.findViewById(R.id.viewbutton);
		editview.setOnClickListener(new SetViewButtonController(set.getId(),context, mapId));
				
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
