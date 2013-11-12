package com.envisprototype.view.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.content.Context;
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
import com.envisprototype.model.sensor.SensorInterface;
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
	public static HashMap<String,TextView> snippets = new HashMap<String, TextView>();

	public GoogleMapContentHelper(Context context,GoogleMap map,HashMap<Marker,SetInterface> hashy){
		this.context = context;
		this.map = map;
		this.hashy = hashy;
		Log.i("conty:", context+"");
		Iterator<Marker> iterator = hashy.keySet().iterator();
		while(iterator.hasNext()){
			//tasks.get(iterator.next()).start();
			Marker tempMarker = iterator.next();
			SetInterface tempset = hashy.get(tempMarker);
		}

	}

	@Override
	public View getInfoContents(Marker marker) {
		// TODO Auto-generated method stub


		return null;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		// TODO Auto-generated method stub
		GetSensorReadingTask task = new GetSensorReadingTask(context, hashy.get(marker), marker);
		task.execute();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View contents =  inflater.inflate(R.layout.googlemapmarkerdetail, null);
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
		snippets.put(hashy.get(marker).getId(), txtType);
		txtType.setText(GoogleMapActivity.readingsForSensorsforSets.get(hashy.get(marker).getId()));


		return contents;


		//return marker;
	}

}
