package com.envisprototype.controller;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.view.processing.BarGraphSet;

public class RealTimeThreeDVis  implements Runnable{
	
	//Runnable runnable;
	String sensorId;
	Context context;
	BarGraphSet barGraphSet;
	public RealTimeThreeDVis(String sensorId, Context context, BarGraphSet bargraphSet){
		//this.index=index;
		//		this.view = view;
		this.sensorId = sensorId;
		this.context = context;
		this.barGraphSet = bargraphSet;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ForAsyncStuff task = new ForAsyncStuff(this);
		task.execute("dummy");
	}
	
	class ForAsyncStuff extends AsyncTask<String, Void, String>{
		
		Runnable runnable;
		public ForAsyncStuff(Runnable runnable) {
			// TODO Auto-generated constructor stub
			this.runnable = runnable;
		}
		

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		String response = "";

		response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(sensorId, context);
		Log.i("NETCONNECTION3", response);
		return response;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// textView.setText(result);
		//String response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0));
		if(result.equals("Not"))
		{
			Toast.makeText(context, "No Internet Coonnection", Toast.LENGTH_SHORT);
		}
		else
		{
			JSONObject obj = null;
			try {
				obj = new JSONObject(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//	obj.getJSONArray();
			Double tempdata = null;
			try {
				tempdata = Double.parseDouble(obj.getString("Reading"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.i("DATAFORREALTIME",tempdata+"");
			//exampleSeries2.appendData(new GraphViewData(graph1LastXValue, tempdata ), true, 10);
				if(tempdata!=null)
			barGraphSet.getReadingsList().set(0,new Float(tempdata));
		}

		BarGraphSet.mHandler.postDelayed(runnable, 200);

	}
	
	
	public void removeCallbacks() {
		BarGraphSet.mHandler.removeCallbacks(runnable);
	}
	
	}

}
