package com.envisprototype.view;

import com.envisprototype.R;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;

public class RecentData extends Fragment implements ActionBar.TabListener {

	private Fragment recentDataFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from fragment2.xml
		getActivity().setContentView(R.layout.fragment_recent_data);
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// recentDataFragment = new AdminTab();
		ft.add(android.R.id.content, recentDataFragment);
		ft.attach(recentDataFragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.remove(recentDataFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}