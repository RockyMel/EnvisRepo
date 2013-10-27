package com.envisprototype.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.view.NewChartActivity;
import com.envisprototype.view.NewRealTimeChartActivity;
import com.envisprototype.view.model.ChartDataByTypes;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
import com.jjoe64.graphview.GraphView.GraphViewData;

public class NewShowChartVisualizationButtonController implements OnClickListener {

	Context context;
	List<String> setIds;
	List<String> sensorIds;
	int MODE;
	Calendar calfrom;
	Calendar calto;

	private class GetSensorReadingTask extends AsyncTask<Void, Void, String> {
		//int index;
		//List<String> tempsensoridlist = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs();
		LinearLayout layout;
		View view;
		String fromstr;
		String tostr;
		GetSensorReadingTask(View view){
			//this.index=index;
			this.view = view;
		}
		private ProgressDialog dialog = new ProgressDialog(context);

		/** progress dialog to show user that the backup is processing. */
		/** application context. */

		protected void onPreExecute() {
			this.dialog.setMessage("Please wait..Building Charts...!!");
			this.dialog.show();
			Calendar from = ChartVisualizationSettingsModel.getSingletonInstance().getFrom();
			Calendar to = ChartVisualizationSettingsModel.getSingletonInstance().getTo();
			fromstr = from.get(Calendar.YEAR) + "-" + from.get(Calendar.MONTH) + "-" + from.get(Calendar.DAY_OF_MONTH)+ " " + from.get(Calendar.HOUR_OF_DAY) + ":" + from.get(Calendar.MINUTE) + ":10";
			tostr = to.get(Calendar.YEAR) + "-" + (to.get(Calendar.MONTH)+1) + "-" + to.get(Calendar.DAY_OF_MONTH)+ " " + to.get(Calendar.HOUR_OF_DAY) + ":" + to.get(Calendar.MINUTE) + ":10";

		}
		@Override
		protected String doInBackground(Void... args) {
			String response = "";
			ChartDataByTypes.getSingletonInstance();
			for(int j=1;j<8;j++)
			{
				List<String> tempsensoridlist = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDListByType(j);
				int index = 0;
				response = "";
				for(int i=0;i<tempsensoridlist.size();i++)
				{
					ChartDataByTypes.addIDForType(tempsensoridlist.get(i),j);
					if(i>0)
						response = response + "###" + SensorReadingDBHelper.getDataReadingSensorByHisTimeJSON(tempsensoridlist.get(i), fromstr, tostr);
					else
						response = SensorReadingDBHelper.getDataReadingSensorByHisTimeJSON(tempsensoridlist.get(i), fromstr, tostr);
				}
				ChartDataByTypes.getSingletonInstance().get(j-1).data=new GraphViewData[tempsensoridlist.size()][];

				ArrayList<Double> data = new ArrayList<Double>();
				//List<ParameterConstruct> pclist = new ArrayList<ParameterConstruct>();
				StringTokenizer strtok = new StringTokenizer(response,"###");
				while(strtok.hasMoreElements()){
					Log.i("asdasD", response);
					try {
						String temp = strtok.nextToken();
						System.out.println("---" + temp);
						JSONObject obj = new JSONObject(temp);
						int i=1;
						while(true)
						{

							try{
								if(obj.getJSONArray(i+"")!=null){
									//GraphViewData temp = new GraphViewData(i-1,Double.parseDouble(obj.getJSONArray(i+"").getString(2)));
									//data[i-1]=new GraphViewData(i-1,Double.parseDouble(obj.getJSONArray(i+"").getString(2)));
									data.add(Double.parseDouble(obj.getJSONArray(i+"").getString(2)));
									//Log.i("chkthis",data[i-1]+"");
								}
								else
									break;
								i++;

							}
							catch(Exception e){
								break;
							}
						}
						GraphViewData[] tempd=new GraphViewData[data.size()];
						for(int k=0;k<data.size();k++)
						{
							tempd[k]=new GraphViewData(k,data.get(k));
						}
						 ChartDataByTypes.addDataForType(tempd,j,index);
						Log.i("SHOW1", data.size()+"");

						//						ChartData.getSingletonInstance().data[index]=tempd;
						//						Log.i("SHOW2", ChartData.getSingletonInstance().data[index].length+"");
						index++;
						data.clear();
						//ChartDataByTypes.getSingletonInstance().addDataForType()
					}catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				}
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {



			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			Intent intent = new Intent(context,NewChartActivity.class);
			view.getContext().startActivity(intent);
		}
	}

	//FOR HISTORICAL
	public NewShowChartVisualizationButtonController(
			Context context,
			List<String> setIds, List<String> sensorIds,int MODE,Calendar calfrom,Calendar calto) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.setIds = setIds;
		this.sensorIds = sensorIds;
		this.MODE = MODE;
		this.calfrom = calfrom;
		this.calto = calto;



	}

	//FOR REAL TIME
	public NewShowChartVisualizationButtonController(
			Context context,
			List<String> setIds, List<String> sensorIds,int MODE) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.setIds = setIds;
		this.sensorIds = sensorIds;
		this.MODE = MODE;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(MODE==0){
			GetSensorReadingTask task = new GetSensorReadingTask(view);
			task.execute();

		}
		if(MODE==1){
			Intent intent = new Intent(context,NewRealTimeChartActivity.class);
			view.getContext().startActivity(intent);
		}



	}

}
