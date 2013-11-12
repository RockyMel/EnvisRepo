package com.envisprototype.view.model;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.view.GoogleMapActivity;
import com.google.android.gms.maps.model.Marker;

public class GetSensorReadingTask extends AsyncTask<String, Void, String> {
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
	/** application context. 
	 * @return */

	@Override
	protected String doInBackground(String... args) {
		String response = "";
		ArrayList<SensorInterface> sensors = (ArrayList<SensorInterface>)SensorListModel.getSingletonInstance().getSensorListBySetID(tempset.getId());
			for(SensorInterface sensor: sensors){
				String sensorId = sensor.getId();
				//response = GoogleMapActivity.readingsForSensorsforSets.get(tempset.getId());
				response += SensorReadingDBHelper.getDataReadingBySensorIDJSON(sensorId, context);
				GoogleMapActivity.readingsForSensorsforSets.put(tempset.getId(), response);

			}

		return response;
	}

	@Override
	protected void onPostExecute(String response) {
		// txtType.setText(GoogleMapActivity.something);
		Log.i("snippets","1 " + GoogleMapContentHelper.snippets.get(tempset.getId()).getText().toString());
		GoogleMapContentHelper.snippets.get(tempset.getId()).setText("");
		Log.i("snippets","2 " + GoogleMapContentHelper.snippets.get(tempset.getId()).getText().toString());
		GoogleMapContentHelper.snippets.get(tempset.getId()).setText(GoogleMapActivity.readingsForSensorsforSets.get(tempset.getId()));
		Log.i("snippets","3 " + GoogleMapContentHelper.snippets.get(tempset.getId()).getText().toString());
		//txtType.setText(GoogleMapActivity.readingsForSensorsforSets.get(tempset.getId()));
		if (marker != null && marker.isInfoWindowShown()) {
			marker.showInfoWindow();
		}

	}
}