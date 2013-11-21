package com.envisprototype.controller;

import java.util.HashMap;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.view.processing.BarGraphSet;
import com.envisprototype.view.processing.EnvisPApplet;
import com.envisprototype.view.processing.SensorSet;
import com.envisprototype.view.processing.SphereGraphSet;
import com.envisprototype.view.processing.ThreeDVis;

public class RealTimeThreeDVis  implements Runnable{
	
	public static HashMap<String, Integer> xbeeTokens = new HashMap<String, Integer>();
	
	//Runnable runnable;
	String sensorId;
	Context context;
	//SensorSet sensor;
	public RealTimeThreeDVis(String sensorId, Context context){
		//this.index=index;
		//		this.view = view;
		this.sensorId = sensorId;
		this.context = context;
		//this.sensor = sensor;
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

		response = SensorReadingDBHelper.getSensorReadingByRealTime(sensorId, context ,1);//getDataReadingBySensorIDJSON(sensorId, context);
		if(response != null)
		Log.i("NETCONNECTION3", response);
		
		
		
		
		
		
		
		
		
		if(response == null || response.equals("Not"))
		{
			Toast.makeText(context, "No Internet Coonnection", Toast.LENGTH_SHORT);
		}
		else
		{
			JSONObject obj = null;
			try {
				obj = new JSONObject(response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//	obj.getJSONArray();
			Double tempdata = null;
			try {
				if(obj.getString("Reading").contains(",")){
						String tempDataToParse = obj.getString("Reading");
						StringTokenizer st = new StringTokenizer(tempDataToParse,",");
						tempdata = Double.parseDouble(st.nextToken());
						Integer xbeeId = Integer.parseInt(st.nextToken());
						xbeeTokens.put(sensorId, xbeeId);
						//Log.i("reading",sensorId + " " + tempdata + " " + xbeeId);
				}
				else{
					tempdata = Double.parseDouble(obj.getString("Reading"));
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.i("DATAFORREALTIME",tempdata+"");
			//exampleSeries2.appendData(new GraphViewData(graph1LastXValue, tempdata ), true, 10);
				if(tempdata!=null){
					//ThreeDVis.getSensorReadings().put(sensorId, tempdata.floatValue());
					for(SphereGraphSet spehereSet: EnvisPApplet.getSphereGraphList()){
						if(spehereSet.getSensorID().equals(sensorId))
							spehereSet.setReadingForSphere(tempdata.floatValue());
					}
					for(BarGraphSet barSet: EnvisPApplet.getBarGraphSetList()){
						if(barSet.getSensorID().equals(sensorId)){
							barSet.getBarGraphList().get(0).setReading(tempdata.floatValue());
						}
					}
				}
			//barGraphSet.getReadingsList().set(0,new Float(tempdata));
		}

		SensorSet.mHandler.postDelayed(runnable, 200);

		
		
		
		
		
		
		return response;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// textView.setText(result);
		//String response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0));
//		if(result == null || result.equals("Not"))
//		{
//			Toast.makeText(context, "No Internet Coonnection", Toast.LENGTH_SHORT);
//		}
//		else
//		{
//			JSONObject obj = null;
//			try {
//				obj = new JSONObject(result);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//	obj.getJSONArray();
//			Double tempdata = null;
//			try {
//				if(obj.getString("Reading").contains(",")){
//						String tempDataToParse = obj.getString("Reading");
//						StringTokenizer st = new StringTokenizer(tempDataToParse,",");
//						tempdata = Double.parseDouble(st.nextToken());
//						Integer xbeeId = Integer.parseInt(st.nextToken());
//						xbeeTokens.put(sensorId, xbeeId);
//						Log.i("reading",sensorId + " " + tempdata + " " + xbeeId);
//				}
//				tempdata = Double.parseDouble(obj.getString("Reading"));
//			} catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Log.i("DATAFORREALTIME",tempdata+"");
//			//exampleSeries2.appendData(new GraphViewData(graph1LastXValue, tempdata ), true, 10);
//				if(tempdata!=null){
//					//ThreeDVis.getSensorReadings().put(sensorId, tempdata.floatValue());
//					for(SphereGraphSet spehereSet: EnvisPApplet.getSphereGraphList()){
//						if(spehereSet.getSensorID().equals(sensorId))
//							spehereSet.setReadingForSphere(tempdata.floatValue());
//					}
//					for(BarGraphSet barSet: EnvisPApplet.getBarGraphSetList()){
//						if(barSet.getSensorID().equals(sensorId)){
//							barSet.getBarGraphList().get(0).setReading(tempdata.floatValue());
//						}
//					}
//				}
//			//barGraphSet.getReadingsList().set(0,new Float(tempdata));
//		}
//
//		SensorSet.mHandler.postDelayed(runnable, 200);

	}
	
	
	public void removeCallbacks() {
		SensorSet.mHandler.removeCallbacks(runnable);
	}
	
	}

}
