package com.envisprototype.view;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.envisprototype.R;
import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class RealTimeChartActivity extends Activity {
	private final Handler mHandler = new Handler();
	private Runnable mTimer1;
	private Runnable mTimer2;
	private GraphView graphView;
	private GraphViewSeries exampleSeries1;
	private GraphViewSeries exampleSeries2;
	private double graph2LastXValue = 0.0d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_real_time_chart);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.BLACK);
		LinearLayout layout;// = (LinearLayout) findViewById(R.id.graph1);
		//		layout.addView(graphView);

		// ----------
		exampleSeries1 = new GraphViewSeries(new GraphViewData[] {
				new GraphViewData(1, 0.0d)

		});

		// graph with custom labels and drawBackground
		//		if (getIntent().getStringExtra("type").equals("bar")) {
		//			graphView = new BarGraphView(
		//					this
		//					, "GraphViewDemo"
		//			);
		//		} else {
		graphView = new LineGraphView(
				this
				, "GraphViewDemo"
				);
		((LineGraphView) graphView).setDrawBackground(true);

		graphView.addSeries(exampleSeries1); // data
		graphView.setViewPort(1, 8);
		graphView.setScalable(true);

		layout = (LinearLayout) findViewById(R.id.graph2);
		layout.addView(graphView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.real_time_chart, menu);
		return true;
	}

	@Override
	protected void onPause() {
		mHandler.removeCallbacks(mTimer1);
		mHandler.removeCallbacks(mTimer2);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mTimer1 = new Runnable() {
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

					response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0),RealTimeChartActivity.this);
					Log.i("NETCONNECTION3", response);
					return response;
				}

				@Override
				protected void onPostExecute(String result) {
					// textView.setText(result);
					graph2LastXValue += 1d;
					//String response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0));
					if(result.equals("Not"))
					{
						Toast.makeText(RealTimeChartActivity.this, "No Internet Coonnection", Toast.LENGTH_SHORT);
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
						exampleSeries1.appendData(new GraphViewData(graph2LastXValue, tempdata ), true, 10);
					}
					mHandler.postDelayed(runnable, 200);


				}
			}

			@Override
			public void run() {
				GetRTSensorReadingTask task = new GetRTSensorReadingTask(this);
				task.execute("dummy");

			}
		};
		mHandler.postDelayed(mTimer1, 1000);
	}


}
