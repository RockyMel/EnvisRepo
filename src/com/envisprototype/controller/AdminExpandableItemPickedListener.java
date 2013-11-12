package com.envisprototype.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.R;
import com.envisprototype.model.DBHelper.MapInfoDBHelper;
import com.envisprototype.model.DBHelper.SetInfoDBHelper;
import com.envisprototype.view.AddMapActivity;
import com.envisprototype.view.MapInfoViewActivity;
import com.envisprototype.view.MapListActivity;
import com.envisprototype.view.NewSetInfoViewActivity;
import com.envisprototype.view.SetListActivity;

public class AdminExpandableItemPickedListener implements OnClickListener{
	String tag;
	class GenerateSetTask extends AsyncTask<String, Void, String> {

		private ProgressDialog dialog;
		Context context;
		String setid;
		View view;
		
		public GenerateSetTask(Context context,View view) {
			// TODO Auto-generated constructor stub
			this.context = context;
			dialog = new ProgressDialog(context);
			this.view = view;
		}
		protected void onPreExecute() {
			this.dialog.setMessage("Please wait while we generate an ID for your set..!!");
			this.dialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			String response = SetInfoDBHelper.generateSetID();
			return response;


		}

		@Override
		protected void onPostExecute(String result) {
	
			setid = result;
			this.dialog.dismiss();
			Intent intent = new Intent(context, NewSetInfoViewActivity.class);
			intent.putExtra("flag", "new");
			intent.putExtra("setid", setid);
			view.getContext().startActivity(intent);
			
		}
	}

	class GenerateMapTask extends AsyncTask<String, Void, String> {

		private ProgressDialog dialog;
		Context context;
		String mapid;
		View view;
		
		public GenerateMapTask(Context context,View view) {
			// TODO Auto-generated constructor stub
			this.context = context;
			dialog = new ProgressDialog(context);
			this.view = view;
		}
		protected void onPreExecute() {
			this.dialog.setMessage("Please wait while we generate an ID for your map..!!");
			this.dialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			String response = MapInfoDBHelper.generateMapID();
			return response;


		}

		@Override
		protected void onPostExecute(String result) {
	
			mapid = result;
			this.dialog.dismiss();
			Intent intent = new Intent(context, AddMapActivity.class);
			intent.putExtra("flag", "new");
			intent.putExtra("mapid", mapid);
			view.getContext().startActivity(intent);
			
		}
	}
	
	
	public AdminExpandableItemPickedListener(String tag){
		this.tag = tag;
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Activity adminkaActivity = (Activity) v.getContext();
		Log.i("listener",tag);
		//String[] menuOptions = adminkaActivity.getResources().getStringArray(R.array.sensors_admin_options);
		/*
		 * for sensor options array
		 * 0 - add sensor
		 * 1 - edit/delete sensor
		 * 2 - plot sensor
		 * 
		 * for map options
		 * 
		 * 0 - add map
		 * 1 - edit/delete map
		 * 
		 */
		Intent intent = new Intent();
		String[] menuOptions = adminkaActivity.getResources().getStringArray(R.array.sensors_admin_options);
		if(tag.equals(menuOptions[0])){
			GenerateSetTask gst = new GenerateSetTask(adminkaActivity,v);
			gst.execute();
			
			
		}
		if(tag.equals(menuOptions[1])){
			intent = new Intent(adminkaActivity, SetListActivity.class);
			adminkaActivity.startActivity(intent);

		}
		if(tag.equals(menuOptions[2])){
			//intent = new Intent(adminkaActivity, SetListActivity.class);
			//intent = new Intent(adminkaActivity, ChooseMapActivity.class);
			intent = new Intent(adminkaActivity, MapListActivity.class);
			intent.putExtra(adminkaActivity.getString(R.string.flags),
					adminkaActivity.getString(R.string.plot_flag_extra));
			adminkaActivity.startActivity(intent);

		}
		menuOptions = adminkaActivity.getResources().getStringArray(R.array.map_admin_options);
		if(tag.equals(menuOptions[0])){
			GenerateMapTask gmt = new GenerateMapTask(adminkaActivity, v);
			gmt.execute();

		}
		if(tag.equals(menuOptions[1])){
			intent = new Intent(adminkaActivity, MapInfoViewActivity.class);
			adminkaActivity.startActivity(intent);

		}
		//adminkaActivity.startActivity(intent);
	}
	
	

}
