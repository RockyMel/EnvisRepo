package com.envisprototype.controller.navigation;

import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.envisprototype.R;
import com.envisprototype.controller.QRreaderButtonController;
import com.envisprototype.view.MapListActivity;
import com.envisprototype.view.SensorListActivity;
import com.envisprototype.view.SensorsExpandableListView;

public class onDropDownItemSelectedListener implements OnNavigationListener{
	Activity activity;
	int dropDownID;
	public onDropDownItemSelectedListener(Activity activity, int dropDownID){
		this.activity = activity;
		this.dropDownID = dropDownID;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		
		Log.i("DropDown", "Position " + itemPosition);
		Intent intent = new Intent();
		switch(dropDownID){
		case R.array.twod_drop_down:{
			/*
			 * This is where to work with 2D vis drop down menu options
			 * Presumably with sensors
			 * Position 0 - 	nothing
			 * Position 1 - 	by type
			 * Position 2 - 	by name
			 * Position 3 - 	nearest
			 * Position 4 - 	scan QR code
			 */
			switch(itemPosition){
			case 0:{
				//intent = new Intent(activity,SortByLoc.class);
			}
			break;
			case 1:{
				intent = new Intent(activity,SensorsExpandableListView.class);
				activity.startActivity(intent);
			}
			break;
			case 2:{
				intent = new Intent(activity,SensorListActivity.class);
				activity.startActivity(intent);
			}
			break;
			case 3:{
				intent = new Intent(activity,SensorListActivity.class);
				activity.startActivity(intent);
			}
			break;
			case 4:{
				ImageButton qrBtn = new ImageButton(activity);
				qrBtn.setOnClickListener(new QRreaderButtonController(activity));
				qrBtn.performClick();
			}
			break;
			}
		}
		break;
		case R.array.map_drop_down:{
			/*
			 * intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			 * This is where to work with 3D vis drop down menu options
			 * Presumably with maps
			 * Position 0 - nothing
			 * Position 1 - scan QR code
			 * Position 2 - by name
			 * Position 3 - nearest
			 */
			Log.i("3D", "drop down for maps");
			switch(itemPosition){
			case 0:{
			}
			break;
			case 1:{
				ImageButton qrBtn = new ImageButton(activity);
				qrBtn.setOnClickListener(new QRreaderButtonController(activity));
				qrBtn.performClick();
			}
			break;
			case 2:{

				intent = new Intent(activity,MapListActivity.class);
				activity.startActivity(intent);
				// !!! ADD FLAG HERE SAYING THAT THUS US 
			}
			break;
			case 3:{
				intent = new Intent(activity,MapListActivity.class); 
				activity.startActivity(intent);
			}
			}
		}
		}
		return false;
	}

}
