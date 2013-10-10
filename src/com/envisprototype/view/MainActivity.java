package com.envisprototype.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.view.navigation.NavigationMaker;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NavigationMaker.makeNavigationDrawer(this);
		MapLocalDBHelper.getSingletonInstance(this).ReplicateMapList();

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		NavigationMaker.drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		NavigationMaker.drawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.threeDVisualisationItem: {
			Intent intentFor3DVis = new Intent(MainActivity.this,
					ThreeDVisualisation.class);
			MainActivity.this.startActivity(intentFor3DVis);
		}
			break;
		}
		if (NavigationMaker.drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
