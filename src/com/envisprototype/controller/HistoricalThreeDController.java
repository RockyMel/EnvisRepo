package com.envisprototype.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.model.processing.SensorReadingsModel;

public class HistoricalThreeDController{

	Context context;
	static List<String> sensoridlist = null;
	Calendar calfrom;
	Calendar calto;
	static String fromStr = null;
	static String toStr = null;

	private class GetSensorReadingTask extends AsyncTask<List<String>, Void, HashMap<String, String>> {
		List<String> sensorIds;
		public GetSensorReadingTask(List<String> sensorIds, String fromstr, String toStr){
			//this.index=index;
			this.sensorIds = sensorIds;
		}

		/** progress dialog to show user that the backup is processing. */
		/** application context. */

		protected void onPreExecute() {

		}
		@Override
		protected HashMap<String, String> doInBackground(List<String>... args) {
			String response = "";
			HashMap<String, String> id_reading = new HashMap<String, String>(); 
			for(String sensorId: sensorIds){
				response = SensorReadingDBHelper.getDataReadingSensorByHisTimeJSON(sensorId, fromStr, toStr);
				id_reading.put(sensorId, response);
			}
			return id_reading;
		}

		@Override
		protected void onPostExecute(HashMap<String, String> id_reading) {
			//HashMap<String, Float> data = new HashMap<String, Float>();
			Iterator<String> stringIdIterator = id_reading.keySet().iterator();
			try {
				while(stringIdIterator.hasNext()){
					String sensorId = stringIdIterator.next();
					String result = id_reading.get(sensorId);
					System.out.println("---" + result);
					JSONObject obj = new JSONObject(result);
					int i=1;
					while(true)
					{
						try{
							if(obj.getJSONArray(i+"")!=null){
								String timeStamp = obj.getJSONArray(i+"").getString(1);
								Float reading = Float.parseFloat(obj.getJSONArray(i+"").getString(2));
								Log.i("loop",timeStamp + reading);
								i++;
								SensorReadingsModel.getSingletonInstance().addNewSensorToReadingsModel(sensorId, timeStamp, reading);
							}
							else
								break;

						}
						catch(Exception e){
							break;
						}
					}
					Log.i("loop",SensorReadingsModel.getSingletonInstance().getSensorReadings().size() + "");
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashSet hs = new HashSet();
			hs.addAll(SensorReadingsModel.getSingletonInstance().getTimeStamps());
			SensorReadingsModel.getSingletonInstance().getTimeStamps().clear();
			SensorReadingsModel.getSingletonInstance().getTimeStamps().addAll(hs);
			Collections.sort(SensorReadingsModel.getSingletonInstance().getTimeStamps());
			System.out.println("DONE");
		}
	}

	//FOR HISTORICAL
	public HistoricalThreeDController(
			List<String> sensoridlist, String fromStr, String toStr) {
		// TODO Auto-generated constructor stub
		HistoricalThreeDController.sensoridlist = sensoridlist;
		if(HistoricalThreeDController.fromStr == null || HistoricalThreeDController.toStr == null){
			HistoricalThreeDController.fromStr = fromStr;
			HistoricalThreeDController.toStr = toStr;
		}
	}

	public void fetchData() {
		//		new Thread(){
		//			public void run(){
		//for(String sensorId: sensoridlist){
		GetSensorReadingTask task = new GetSensorReadingTask(sensoridlist, fromStr, toStr);
		task.execute();
		//				}
		//			}
		//		}.start();
	}
}
