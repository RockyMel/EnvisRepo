package com.envisprototype.view;

import com.envisprototype.view.navigation.NavigationMaker;
import com.envisprototype.R;

import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;

public class FavoritesTab extends Activity {

	private Fragment favFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		NavigationMaker.makeNavigationDrawer(this);
	}

}
