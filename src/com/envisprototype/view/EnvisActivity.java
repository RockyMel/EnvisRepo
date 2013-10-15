package com.envisprototype.view;

import android.app.Activity;

import com.envisprototype.view.navigation.NavigationMaker;

public class EnvisActivity extends Activity{

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		NavigationMaker.makeNavigationDrawer(this);
	}

	
}
