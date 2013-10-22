package com.envisprototype.view.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.view.RealTimeChartListActivity;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;

public class GetDataThread implements Runnable{

	double graph2LastXValue;
	GraphViewSeries exampleSeries2;
	Context context;
	private final Handler mHandler = new Handler();

	class GetRTSensorReadingTask extends AsyncTask<String, Void, String> {
		//int index;
		Runnable runnable;
		GetRTSensorReadingTask(Runnable runnable){
			//this.index=index;
			//		this.view = view;
			this.runnable = runnable;
		}
		@Override
		protected String doInBackground(String... args) {
			String response = "";

			response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0),context);
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			// textView.setText(result);
			graph2LastXValue += 1d;
			//String response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0));
			if(result.equals("Not"))
			{
				//DO NOTHING
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
				Log.i("DATAFORREALTIME",tempdata+" " + exampleSeries2);
				exampleSeries2.appendData(new GraphViewData(graph2LastXValue, tempdata ), true, 10);
				exampleSeries2.appendData(new GraphViewData(graph2LastXValue, tempdata ), true, 10);
				exampleSeries2.appendData(new GraphViewData(graph2LastXValue, tempdata ), true, 10);
				
				RealTimeChartListActivity.updateadpt();
			}
			mHandler.postDelayed(runnable, 200);


		}
	}
	
	
	
	public GetDataThread(double graph2LastXValue, GraphViewSeries exampleSeries2,Context context) {
		// TODO Auto-generated constructor stub
		this.exampleSeries2 = exampleSeries2;
		this.graph2LastXValue = graph2LastXValue;
		this.context = context;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.i("its here", "yes");
		GetRTSensorReadingTask task = new GetRTSensorReadingTask(this);
		task.execute("dummy");

	}

	
}
