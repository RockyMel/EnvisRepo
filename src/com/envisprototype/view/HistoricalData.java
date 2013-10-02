package com.envisprototype.view;

import com.envisprototype.R;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;

public class HistoricalData extends Fragment implements ActionBar.TabListener {

	private Fragment historicalDataFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from fragment2.xml
		getActivity().setContentView(R.layout.fragment_historical_data);
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// historicalDataFragment = new AdminTab();
		ft.add(android.R.id.content, historicalDataFragment);
		ft.attach(historicalDataFragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.remove(historicalDataFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
