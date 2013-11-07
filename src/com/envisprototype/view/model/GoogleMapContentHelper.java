package com.envisprototype.view.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TextView;

import com.envisprototype.R;

import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.view.GoogleMapActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;


public class GoogleMapContentHelper implements InfoWindowAdapter{

	Context context;
	GoogleMap map;
	HashMap<Marker,SetInterface> hashy;
	TextView txtType;
	String response;
	
	private class GetSensorReadingTask extends AsyncTask<String, Void, String> {
		//int index;
		//List<String> tempsensoridlist = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs();
		Context context ;
		SetInterface  tempset;
		Marker marker;
		GetSensorReadingTask(Context context,SetInterface tempset, Marker marker){
			//this.index=index;
			this.context = context;
			this.tempset = tempset;
			this.marker = marker;
		}
			/** progress dialog to show user that the backup is processing. */
		/** application context. */

		
		
		
		@Override
		protected String doInBackground(String... args) {
			String response = "";
			for(int i=0;i<SensorListModel.getSingletonInstance().getSensorListBySetID(tempset.getId()).size();i++){
				response = response + "\n" + SensorReadingDBHelper.getDataReadingBySensorIDJSON(SensorListModel.getSingletonInstance().getSensorListBySetID(tempset.getId()).get(i).getId(), context);
				GoogleMapActivity.something = response;
			}
			Log.i("asdsadast43trefsdv", response);
			return response;
		}


		@Override
		protected void onPostExecute(String result) {
            txtType.setText(GoogleMapActivity.something);
           
			//txtType.setText(result);
			
			if (marker != null && marker.isInfoWindowShown()) {
                marker.showInfoWindow();
            }
			//marker.showInfoWindow();
			//marker.
			//marker.
		//	marker.notify();
			
		}
	}

	
	
	
	
	public GoogleMapContentHelper(Context context,GoogleMap map,HashMap<Marker,SetInterface> hashy){
		this.context = context;
		this.map = map;
		this.hashy = hashy;
		Log.i("conty:", context+"");
		
	}
	
	@Override
	public View getInfoContents(Marker marker) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		// TODO Auto-generated method stub
	
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View contents =  inflater.inflate(R.layout.googlemapmarkerdetail, null);
		
		final SetInterface tempset = hashy.get(marker);

	    

	    String title = marker.getTitle();

	             TextView txtTitle = ((TextView) contents.findViewById(R.id.SetNAME));

	             if (title != null) {

	                 // Spannable string allows us to edit the formatting of the text.

	                 SpannableString titleText = new SpannableString(title);

	                 titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);

	                 txtTitle.setText(titleText);

	             } else {

	                 txtTitle.setText("");

	             }

	             

	             txtType = ((TextView) contents.findViewById(R.id.SetREADINGS));
	           //  GoogleMapActivity.something = "Loading..";
	             txtType.setText(GoogleMapActivity.something);
	             //response = "agaggagaga";
				 //txtType.setText(response);
					 
	             //txtType.setText(tempset);
//	             Runnable runnable  = new Runnable(){
//
//					@Override
//					public void run() {
//						Activity blah = (Activity)context;
//						response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(SensorListModel.getSingletonInstance().getSensorListBySetID(tempset.getId()).get(0).getId(), context);
//						blah.runOnUiThread(new Runnable(){
//
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								Log.i("deep", response);
//							}
//							
//						});
//					}
//	            	 
//	             };
//	             Thread t = new Thread(runnable);
//	             t.start();
	             
	    GetSensorReadingTask task = new GetSensorReadingTask(context,tempset,marker);
	 	task.execute();
	             

	    return contents;

		
		//return marker;
	}

}
