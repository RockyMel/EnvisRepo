package com.envisprototype.controller;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.R;
import com.envisprototype.view.AddMapActivity;
import com.envisprototype.view.ChooseMapActivity;
import com.envisprototype.view.MapInfoViewActivity;
import com.envisprototype.view.NewSetInfoViewActivity;
import com.envisprototype.view.SetListActivity;

public class AdminExpandableItemPickedListener implements OnClickListener{
	String tag;
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
			intent = new Intent(adminkaActivity, NewSetInfoViewActivity.class);
			//intent.putExtra("mode", "set");
			intent.putExtra("flag", "new");
			
		}
		if(tag.equals(menuOptions[1])){
			intent = new Intent(adminkaActivity, SetListActivity.class);
		}
		if(tag.equals(menuOptions[2])){
			//intent = new Intent(adminkaActivity, SetListActivity.class);
			intent = new Intent(adminkaActivity, ChooseMapActivity.class);
			intent.putExtra(adminkaActivity.getString(R.string.flags),
					adminkaActivity.getString(R.string.plot_flag_extra));
		}
		menuOptions = adminkaActivity.getResources().getStringArray(R.array.map_admin_options);
		if(tag.equals(menuOptions[0])){
			intent = new Intent(adminkaActivity, AddMapActivity.class);
		}
		if(tag.equals(menuOptions[1])){
			intent = new Intent(adminkaActivity, MapInfoViewActivity.class);
		}
		adminkaActivity.startActivity(intent);
	}
	
	

}
