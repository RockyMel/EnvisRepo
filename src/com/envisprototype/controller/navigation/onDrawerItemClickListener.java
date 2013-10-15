package com.envisprototype.controller.navigation;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.envisprototype.view.AdminkaActivity;
import com.envisprototype.view.ChartVisualizationSettingsActivity;
import com.envisprototype.view.ChooseMapActivity;
import com.envisprototype.view.FavoritesTab;
import com.envisprototype.view.LineChart;
import com.envisprototype.view.AdminAuthActivity;
import com.envisprototype.view.ThreeDVisualisation;

public class onDrawerItemClickListener implements OnItemClickListener {
	DrawerLayout drawer;

	public onDrawerItemClickListener(DrawerLayout drawer) {
		this.drawer = drawer;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long arg3) {
		// TODO Auto-generated method stub
		// 0 - Favourites
		// 1 - Admin
		// 2 - 2D charts
		// 3 - 3D Visual
		// 4 - Settings
		Intent intent = null;
		switch (pos) {
		case 0: {
			intent = new Intent(v.getContext(), FavoritesTab.class);
		}
			break;
		case 1: {
			//intent = new Intent(v.getContext(), AdminTab.class);
			intent = new Intent(v.getContext(), AdminAuthActivity.class);
		}
			break;
		case 2: {
			intent = new Intent(v.getContext(), ChartVisualizationSettingsActivity.class);
		}
			break;
		case 3: {
			intent = new Intent(v.getContext(), ChooseMapActivity.class);
		}
			break;
		case 4: {
		}
		}
		if (intent != null) {
			Log.i("Ok", "about to start activity");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
			v.getContext().startActivity(intent);
		}
		drawer.closeDrawers();
	}

}