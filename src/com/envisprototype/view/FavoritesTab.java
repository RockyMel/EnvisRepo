package com.envisprototype.view;

import com.envisprototype.LocalDBHelper.EnvisDBAdapter;
import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.LocalDBHelper.SensorLocalDBHelper;
import com.envisprototype.LocalDBHelper.SetLocalDBHelper;
import com.envisprototype.LocalDBHelper.SetSensorAssociationLocalDBHelper;
import com.envisprototype.model.DBHelper.SynchronizeWithCloud;
import com.envisprototype.view.navigation.NavigationMaker;
import com.envisprototype.R;

import android.os.Bundle;
import android.util.Log;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;

public class FavoritesTab extends EnvisActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Runnable runnable = new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SynchronizeWithCloud.SyncSets(FavoritesTab.this);
				SynchronizeWithCloud.SyncSensors(FavoritesTab.this);
				SynchronizeWithCloud.SyncSetAndSensorAssociation(FavoritesTab.this);
				SynchronizeWithCloud.SyncMaps(FavoritesTab.this);
				SynchronizeWithCloud.SyncSetAndMapAssociation(FavoritesTab.this);
				EnvisDBAdapter.getSingletonInstance(FavoritesTab.this).replecateDB();
				Log.i("done", "done");
				
			}
			
		};
		Thread t =new Thread(runnable);
		t.start();

		setContentView(R.layout.activity_favorites);
	}

}
