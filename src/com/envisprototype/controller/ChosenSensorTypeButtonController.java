package com.envisprototype.controller;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.model.DBHelper.SensorInfoDBHelper;
import com.envisprototype.view.NewSensorInfoViewActivity;


public class ChosenSensorTypeButtonController implements OnClickListener{
	private String sensorid;
	private Activity activity;
	private String setid;
	private int type;
	public ChosenSensorTypeButtonController(String setid, int type,
			Activity activity) {
		// TODO Auto-generated constructor stub
		this.setid=setid;
		this.type=type;
		this.activity=activity;


	}
	class GenerateSensorTask extends AsyncTask<String, Void, String> {

		private ProgressDialog dialog = new ProgressDialog(activity);
		View view;
		public GenerateSensorTask(View view) {
			// TODO Auto-generated constructor stub
			this.view = view;
		}
		protected void onPreExecute() {
			this.dialog.setMessage("Please wait while we generate an ID for your sensor..!!");
			this.dialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			String response = SensorInfoDBHelper.generateSensorID();
			return response;


		}

		@Override
		protected void onPostExecute(String result) {
	
			sensorid = result;
			this.dialog.dismiss();
			Intent intent=new Intent(view.getContext(),NewSensorInfoViewActivity.class);
			intent.putExtra("setid", setid);
			intent.putExtra("type", type+"");
			Log.i("sadasd", sensorid);
			intent.putExtra("sensorid", sensorid);
			intent.putExtra("flag","new");
			view.getContext().startActivity(intent);
			activity.finish();
		}
	}


	

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		GenerateSensorTask gst = new GenerateSensorTask(view);
		gst.execute();
		
	}

}
