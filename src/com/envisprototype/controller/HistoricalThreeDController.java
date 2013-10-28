package com.envisprototype.controller;

import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.model.processing.SensorReadingsModel;

public class HistoricalThreeDController{

	Context context;
	static List<String> sensoridlist = null;
	Calendar calfrom;
	Calendar calto;
	static String fromStr = null;
	static String toStr = null;

	private class GetSensorReadingTask extends AsyncTask<String, Void, String> {
		String sensorId;
		public GetSensorReadingTask(String sensorId, String fromstr, String toStr){
			//this.index=index;
				this.sensorId = sensorId;
		}

		/** progress dialog to show user that the backup is processing. */
		/** application context. */

		protected void onPreExecute() {
		}
		@Override
		protected String doInBackground(String... args) {
			String response = "";
			response = SensorReadingDBHelper.getDataReadingSensorByHisTimeJSON(sensorId, fromStr, toStr);
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			TreeMap<String, Float> data = new TreeMap<String, Float>();
				try {
					System.out.println("---" + result);
					JSONObject obj = new JSONObject(result);
					int i=1;
					while(true)
					{

						try{
							if(obj.getJSONArray(i+"")!=null){
								data.put(obj.getJSONArray(i+"").getString(1),
										Float.parseFloat(obj.getJSONArray(i+"").getString(2)));
//								for(BarGraphSet barSet: EnvisPApplet.getBarGraphSetList()){
//									barSet.getBarGraphList().get(0).setReadingRange(data);
//								}
								SensorReadingsModel.getSingletonInstance().addNewSensorToReadingsModel(sensorId, data);
								i++;
							}
							else
								break;

						}
						catch(Exception e){
							break;
						}
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			for(String sensorId: sensoridlist){
				GetSensorReadingTask task = new GetSensorReadingTask(sensorId, fromStr, toStr);
				task.execute("dummy");
			}

	}

}
