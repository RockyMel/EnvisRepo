package com.envisprototype.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.envisprototype.R;
import com.envisprototype.view.navigation.NavigationMaker;

public class ThreeDVisualisation extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_3d_visualisation);
		NavigationMaker.makeNavigationDrawer(this);
		NavigationMaker.makeDropDownMenu(this,R.array.threed_drop_down);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.visualisation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.chooseSensors: {
			// will launch an activity with ependable Lists for user to
			// choose sensors
			Intent intentForSensorsList = new Intent(ThreeDVisualisation.this,
					SensorsExpandableListView.class);
			ThreeDVisualisation.this.startActivity(intentForSensorsList);
		}
		}
		return true;
	}
}
