package com.envisprototype.view;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.view.model.ChartDataByTypes;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class NewRealTimeChartActivity extends Activity {

	private final Handler mHandler = new Handler();
	private Runnable mTimer1; //Air Quality
	private Runnable mTimer2; //Humidity
	private Runnable mTimer3; //Light
	private Runnable mTimer4; //Temp
	private Runnable mTimer5; //Water Level

	private GraphView graphView1;

	private GraphView graphView2;
	private GraphView graphView3;
	private GraphView graphView4;
	private GraphView graphView5;
	boolean killMe = false;

	//	private GraphViewSeries exampleSeries1;
	//	private GraphViewSeries exampleSeries2;
	private GraphViewSeries	seriesforaq[];
	private GraphViewSeries	seriesforh[];
	private GraphViewSeries	seriesforl[];
	private GraphViewSeries	seriesfort[];
	private GraphViewSeries	seriesforwl[];

	private double graphaqlastXValue[];
	private double graphhlastXValue[];
	private double graphllastXValue[];
	private double graphtlastXValue[];
	private double graphwllastXValue[];

	ChartDataByTypes tempaq=null; 
	ChartDataByTypes temph=null; 
	ChartDataByTypes templ=null; 
	ChartDataByTypes tempt=null; 
	ChartDataByTypes tempwl=null; 


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_real_time_chart);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.BLACK);

		int index=0;

		LinearLayout layout1 = (LinearLayout) findViewById(R.id.graph1);
		LinearLayout layout2 = (LinearLayout) findViewById(R.id.graph2);
		LinearLayout layout3 = (LinearLayout) findViewById(R.id.graph3);
		LinearLayout layout4 = (LinearLayout) findViewById(R.id.graph4);
		LinearLayout layout5 = (LinearLayout) findViewById(R.id.graph5);

		Log.i("asdqwe SIZE", ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(5).size()+"");

		if(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(1).size()>0)
		{
			tempaq	=  new ChartDataByTypes();
			tempaq.type=1;
			tempaq.list = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(1);
			graphView1 = new LineGraphView(
					this
					, "AIR QUALITY"
					);
			((LineGraphView) graphView1).setDrawBackground(true);
			graphView1.setViewPort(1, 8);
			graphView1.setScalable(true);
			graphView1.setShowLegend(true);
			graphView1.setLegendAlign(LegendAlign.BOTTOM);
			graphView1.setLegendWidth(200);
			layout1.addView(graphView1);
			seriesforaq=new GraphViewSeries[tempaq.list.size()];
			for(int i=0;i<tempaq.list.size();i++)
			{
				int temp = (int)(Math.random()*100);

				seriesforaq[i] = new GraphViewSeries( SensorListModel.getSingletonInstance().findSensorById(tempaq.list.get(i)).getName(),new GraphViewSeriesStyle(Color.rgb(temp, 50, 100), 3),new GraphViewData[] {
						new GraphViewData(1, 1.0d)

				});
				graphView1.addSeries(seriesforaq[i]);

			}

			graphaqlastXValue = new double[tempaq.list.size()];
			for(int k=0;k<graphaqlastXValue.length;k++)
			{
				graphaqlastXValue[k]=1.0d;
			}
		}
		if(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(2).size()>0)
		{
			temph	=  new ChartDataByTypes();
			temph.type=1;
			temph.list = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(2);
			graphView2 = new LineGraphView(
					this
					, "HUMIDITY"
					);
			((LineGraphView) graphView1).setDrawBackground(true);
			graphView2.setViewPort(1, 8);
			graphView2.setScalable(true);
			graphView2.setShowLegend(true);
			graphView2.setLegendAlign(LegendAlign.BOTTOM);
			graphView2.setLegendWidth(200);
			layout2.addView(graphView2);
			seriesforh=new GraphViewSeries[temph.list.size()];
			for(int i=0;i<temph.list.size();i++)
			{
				int temp = (int)(Math.random()*100);

				seriesforh[i] = new GraphViewSeries(SensorListModel.getSingletonInstance().findSensorById(temph.list.get(i)).getName(),new GraphViewSeriesStyle(Color.rgb(temp, 50, 100), 3),new GraphViewData[] {
						new GraphViewData(1, 1.0d)

				});
				graphView2.addSeries(seriesforh[i]);

			}
			graphhlastXValue = new double[temph.list.size()];
			for(int k=0;k<graphhlastXValue.length;k++)
			{
				graphhlastXValue[k]=1.0d;
			}
		}
		if(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(3).size()>0)
		{
			templ	=  new ChartDataByTypes();
			templ.type=1;
			templ.list = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(3);
			graphView3 = new LineGraphView(
					this
					, "LIGHT"
					);
			((LineGraphView) graphView3).setDrawBackground(true);
			graphView3.setViewPort(1, 8);
			graphView3.setScalable(true);
			graphView3.setShowLegend(true);
			graphView3.setLegendAlign(LegendAlign.BOTTOM);
			graphView3.setLegendWidth(200);
			layout3.addView(graphView3);
			seriesforl=new GraphViewSeries[templ.list.size()];
			for(int i=0;i<templ.list.size();i++)
			{
				int temp = (int)(Math.random()*100);

				seriesforl[i] = new GraphViewSeries( SensorListModel.getSingletonInstance().findSensorById(templ.list.get(i)).getName(),new GraphViewSeriesStyle(Color.rgb(temp, 50, 100), 3),new GraphViewData[] {
						new GraphViewData(1, 1.0d)

				});
				graphView3.addSeries(seriesforl[i]);

			}
			graphllastXValue = new double[templ.list.size()];
			for(int k=0;k<graphllastXValue.length;k++)
			{
				graphllastXValue[k]=1.0d;
			}

		}
		if(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(5).size()>0)
		{
			tempt	=  new ChartDataByTypes();
			tempt.type=1;
			tempt.list = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(5);
			graphView4 = new LineGraphView(
					this
					, "TEMPERATURE"
					);
			((LineGraphView) graphView4).setDrawBackground(true);
			graphView4.setViewPort(1, 8);
			graphView4.setScalable(true);
			graphView4.setShowLegend(true);
			graphView4.setLegendAlign(LegendAlign.BOTTOM);
			graphView4.setLegendWidth(200);
			layout4.addView(graphView4);
			seriesfort=new GraphViewSeries[tempt.list.size()];
			for(int i=0;i<tempt.list.size();i++)
			{
				int temp = (int)(Math.random()*100);

				seriesfort[i] = new GraphViewSeries(SensorListModel.getSingletonInstance().findSensorById(tempt.list.get(i)).getName(),new GraphViewSeriesStyle(Color.rgb(temp, 50, 100), 3),new GraphViewData[] {
						new GraphViewData(1, 1.0d)

				});
				graphView4.addSeries(seriesfort[i]);

			}
			graphtlastXValue = new double[tempt.list.size()];
			for(int k=0;k<graphtlastXValue.length;k++)
			{
				graphtlastXValue[k]=1.0d;
			}

		}
		if(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(6).size()>0)
		{
			tempwl	=  new ChartDataByTypes();
			tempwl.type=1;
			tempwl.list = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(6);

			graphView5 = new LineGraphView(
					this
					, "WATER LEVEL"
					);
			((LineGraphView) graphView5).setDrawBackground(true);
			graphView5.setViewPort(1, 8);
			graphView5.setScalable(true);
			graphView5.setShowLegend(true);
			graphView5.setLegendAlign(LegendAlign.BOTTOM);
			graphView5.setLegendWidth(200);
			layout5.addView(graphView5);

			seriesforwl=new GraphViewSeries[tempwl.list.size()];
			for(int i=0;i<tempwl.list.size();i++)
			{
				int temp = (int)(Math.random()*100);

				seriesforwl[i] = new GraphViewSeries(SensorListModel.getSingletonInstance().findSensorById(tempwl.list.get(i)).getName(),new GraphViewSeriesStyle(Color.rgb(temp, 50, 100), 3),new GraphViewData[] {
						new GraphViewData(1, 1.0d)

				});
				graphView5.addSeries(seriesforwl[i]);

			}
			graphwllastXValue = new double[tempwl.list.size()];
			for(int k=0;k<graphwllastXValue.length;k++)
			{
				graphwllastXValue[k]=1.0d;
			}

		}







		if(tempaq==null)
			layout1.setVisibility(View.GONE);
		if(temph==null)
			layout2.setVisibility(View.GONE);
		if(templ==null)
			layout3.setVisibility(View.GONE);
		if(tempt==null)
			layout4.setVisibility(View.GONE);
		if(tempwl==null)
			layout5.setVisibility(View.GONE);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.real_time_chart, menu);
		return true;
	}

	@Override
	protected void onPause() {

		killMe=true;
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("in resume1", "yes");

		if(tempaq!=null){
			Log.i("in resume2", "yes");

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


						for(int i=0;i<tempaq.list.size();i++)
						{
							if(i==0)
								response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(tempaq.list.get(i),NewRealTimeChartActivity.this); 
							else
								response = response+"###" + SensorReadingDBHelper.getDataReadingBySensorIDJSON(tempaq.list.get(i),NewRealTimeChartActivity.this);
						}

						Log.i("NETCONNECTION3", response);
						return response;
					}

					@Override
					protected void onPostExecute(String result) {
						// textView.setText(result);
						//graph2LastXValue += 1d;
						//String response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0));
						if(result!=null)
						{
							StringTokenizer tempstrtok = new StringTokenizer(result,"###");
							int index=0;
							while(tempstrtok.hasMoreElements())
							{
								String gotone = tempstrtok.nextToken();
								Log.i("now", gotone);
								JSONObject obj = null;
								try {
									obj = new JSONObject(gotone);
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
								Log.i("LAST THINGY",tempdata+" -- "+seriesforaq[index]+" -- "+ graphaqlastXValue[index]);
								graphaqlastXValue[index] +=1d;
								if(tempdata!=null)
								{
									seriesforaq[index].appendData(new GraphViewData(graphaqlastXValue[index], tempdata ), true, 10);
									index++;
								}
							}
							mHandler.postDelayed(runnable, 200);


						}
					}
				}

				@Override
				public void run() {
					if(killMe)
						return;
					GetRTSensorReadingTask task = new GetRTSensorReadingTask(this);
					task.execute("dummy");

				}

			};
			mHandler.postDelayed(mTimer1, 200);

		}

		if(temph!=null){
			Log.i("in resume2", "yesh");

			mTimer2 = new Runnable() {


				class GetRTSensorReadingTask extends AsyncTask<String, Void, String> {
					//int index;

					Runnable runnable;
					GetRTSensorReadingTask(Runnable runnable){

						this.runnable = runnable;
					}
					@Override
					protected String doInBackground(String... args) {

						String response = "";


						for(int i=0;i<temph.list.size();i++)
						{
							if(i==0)
								response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(temph.list.get(i),NewRealTimeChartActivity.this); 
							else
								response = response+"###" + SensorReadingDBHelper.getDataReadingBySensorIDJSON(temph.list.get(i),NewRealTimeChartActivity.this);
						}

						Log.i("NETCONNECTION3", response);
						return response;
					}

					@Override
					protected void onPostExecute(String result) {

						if(result!=null)
						{
							StringTokenizer tempstrtok = new StringTokenizer(result,"###");
							int index=0;
							while(tempstrtok.hasMoreElements())
							{
								String gotone = tempstrtok.nextToken();
								Log.i("now", gotone);
								JSONObject obj = null;
								try {
									obj = new JSONObject(gotone);
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
								Log.i("LAST THINGY",tempdata+" -- "+seriesforh[index]+" -- "+ graphaqlastXValue[index]);
								graphhlastXValue[index] +=1d;
								if(tempdata!=null)
								{
									seriesforh[index].appendData(new GraphViewData(graphhlastXValue[index], tempdata ), true, 10);
									index++;
								}
							}
							mHandler.postDelayed(runnable, 200);


						}
					}
				}

				@Override
				public void run() {
					if(killMe)
						return;
					GetRTSensorReadingTask task = new GetRTSensorReadingTask(this);
					task.execute("dummy");

				}

			};
			mHandler.postDelayed(mTimer1, 200);

		}
		if(templ!=null){
			Log.i("in resume2", "yes");

			mTimer3 = new Runnable() {


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


						for(int i=0;i<templ.list.size();i++)
						{
							if(i==0)
								response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(templ.list.get(i),NewRealTimeChartActivity.this); 
							else
								response = response+"###" + SensorReadingDBHelper.getDataReadingBySensorIDJSON(templ.list.get(i),NewRealTimeChartActivity.this);
						}

						Log.i("NETCONNECTION3", response);
						return response;
					}

					@Override
					protected void onPostExecute(String result) {
						// textView.setText(result);
						//graph2LastXValue += 1d;
						//String response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0));
						if(result!=null)
						{
							StringTokenizer tempstrtok = new StringTokenizer(result,"###");
							int index=0;
							while(tempstrtok.hasMoreElements())
							{
								String gotone = tempstrtok.nextToken();
								Log.i("now", gotone);
								JSONObject obj = null;
								try {
									obj = new JSONObject(gotone);
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
								//Log.i("DATAFORREALTIME",tempdata+"");
								//Log.i("LAST THINGY",tempdata+" -- "+seriesforl[index]+" -- "+ graphaqlastXValue[index]);
								graphllastXValue[index] +=1d;
								if(tempdata!=null)
								{
								seriesforl[index].appendData(new GraphViewData(graphllastXValue[index], tempdata ), true, 10);
								index++;
								}
							}
							mHandler.postDelayed(runnable, 200);


						}
					}
				}

				@Override
				public void run() {
					if(killMe)
						return;
					GetRTSensorReadingTask task = new GetRTSensorReadingTask(this);
					task.execute("dummy");

				}

			};
			mHandler.postDelayed(mTimer3, 200);

		}
		if(tempt!=null){
			Log.i("in resume2", "yes");

			mTimer4 = new Runnable() {


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


						for(int i=0;i<tempt.list.size();i++)
						{
							if(i==0)
								response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(tempt.list.get(i),NewRealTimeChartActivity.this); 
							else
								response = response+"###" + SensorReadingDBHelper.getDataReadingBySensorIDJSON(tempt.list.get(i),NewRealTimeChartActivity.this);
						}

						Log.i("NETCONNECTION3", response);
						return response;
					}

					@Override
					protected void onPostExecute(String result) {
						// textView.setText(result);
						//graph2LastXValue += 1d;
						//String response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0));
						if(result!=null)
						{
							StringTokenizer tempstrtok = new StringTokenizer(result,"###");
							int index=0;
							while(tempstrtok.hasMoreElements())
							{
								String gotone = tempstrtok.nextToken();
								Log.i("now", gotone);
								JSONObject obj = null;
								try {
									obj = new JSONObject(gotone);
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
								Log.i("LAST THINGY",tempdata+" -- "+seriesfort[index]+" -- "+ graphtlastXValue[index]);
								graphtlastXValue[index] +=1d;
								if(tempdata!=null)
								{
								seriesfort[index].appendData(new GraphViewData(graphtlastXValue[index], tempdata ), true, 10);
								index++;
								}
							}
							mHandler.postDelayed(runnable, 200);


						}
					}
				}

				@Override
				public void run() {
					if(killMe)
						return;
					GetRTSensorReadingTask task = new GetRTSensorReadingTask(this);
					task.execute("dummy");

				}

			};
			mHandler.postDelayed(mTimer4, 200);

		}
		if(tempwl!=null){
			Log.i("in resume2", "yes");

			mTimer5 = new Runnable() {


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


						for(int i=0;i<tempwl.list.size();i++)
						{
							if(i==0)
								response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(tempwl.list.get(i),NewRealTimeChartActivity.this); 
							else
								response = response+"###" + SensorReadingDBHelper.getDataReadingBySensorIDJSON(tempwl.list.get(i),NewRealTimeChartActivity.this);
						}

						Log.i("NETCONNECTION3", response);
						return response;
					}

					@Override
					protected void onPostExecute(String result) {
						// textView.setText(result);
						//graph2LastXValue += 1d;
						//String response = SensorReadingDBHelper.getDataReadingBySensorIDJSON(ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().get(0));
						if(result!=null)
						{
							StringTokenizer tempstrtok = new StringTokenizer(result,"###");
							int index=0;
							while(tempstrtok.hasMoreElements())
							{
								String gotone = tempstrtok.nextToken();
								Log.i("now", gotone);
								JSONObject obj = null;
								try {
									obj = new JSONObject(gotone);
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
								Log.i("LAST THINGY",tempdata+" -- "+seriesforwl[index]+" -- "+ graphwllastXValue[index]);
								graphwllastXValue[index] +=1d;
								if(tempdata!=null)
								{
								seriesforwl[index].appendData(new GraphViewData(graphwllastXValue[index], tempdata ), true, 10);
								index++;
								}
							}
							mHandler.postDelayed(runnable, 200);


						}
					}
				}

				@Override
				public void run() {
					if(killMe)
						return;
					GetRTSensorReadingTask task = new GetRTSensorReadingTask(this);
					task.execute("dummy");

				}

			};
			mHandler.postDelayed(mTimer5, 200);

		}


	}

}
