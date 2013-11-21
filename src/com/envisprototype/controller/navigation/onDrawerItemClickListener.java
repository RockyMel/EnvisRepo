package com.envisprototype.controller.navigation;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.envisprototype.R;
import com.envisprototype.controller.Checks;
import com.envisprototype.view.AdminkaActivity;
import com.envisprototype.view.ChartVisualizationSettingsActivity;
import com.envisprototype.view.FavoritesTab;
import com.envisprototype.view.GoogleMapActivity;
import com.envisprototype.view.MapListActivity;
import com.envisprototype.view.TagViewActivity;
import com.envisprototype.view.model.MapListAdapter;

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
			intent = new Intent(v.getContext(), AdminkaActivity.class);
		}
		break;
		case 2: {
			intent = new Intent(v.getContext(), ChartVisualizationSettingsActivity.class);
		}
		break;
		case 3: {
			// !!! replace choosemapactivity with the one to pick maps
			//intent = new Intent(v.getContext(), ChooseMapActivity.class);
			intent = new Intent(v.getContext(), MapListActivity.class);
			intent.putExtra(v.getContext().getString(R.string.request_code), MapListAdapter.MAP_BY_NAME);
		}
		break;
		case 4: {
			intent = new Intent(v.getContext(), TagViewActivity.class);
			break;
		}

		case 5: {
			if(Checks.isOnline(v.getContext()))
				intent = new Intent(v.getContext(), GoogleMapActivity.class);
			break;
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