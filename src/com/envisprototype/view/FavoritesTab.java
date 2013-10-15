package com.envisprototype.view;

import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.view.navigation.NavigationMaker;
import com.envisprototype.R;

import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;

public class FavoritesTab extends EnvisActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MapLocalDBHelper.getSingletonInstance(this).ReplicateMapList();

		setContentView(R.layout.activity_favorites);
	}

}
