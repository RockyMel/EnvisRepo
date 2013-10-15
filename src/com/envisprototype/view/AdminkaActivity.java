package com.envisprototype.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.envisprototype.R;
import com.envisprototype.controller.ExpandableAdminAdapter;
import com.envisprototype.controller.ExpandableListAdapter;
import com.envisprototype.view.navigation.NavigationMaker;
//@Override
//public void onCreate(Bundle savedInstanceState) {
//	super.onCreate(savedInstanceState);
//	// Get the view from fragment2.xml
//	setContentView(R.layout.activity_admin);
//	Button drawMapBtn = (Button) findViewById(R.id.drawMapBtn);
//	drawMapBtn.setOnClickListener(new onDrawMapClickListener());
//	NavigationMaker.makeNavigationDrawer(this);
//}

public class AdminkaActivity extends EnvisActivity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminka);
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.sensorTypes);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableAdminAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Sensors");
		listDataHeader.add("Maps");

		// Adding child data
		List<String> firstGroup = new ArrayList<String>();
		String[] menuOptions = getResources().getStringArray(R.array.sensors_admin_options);
		for(int i = 0; i < menuOptions.length; i++){
			firstGroup.add(menuOptions[i]);
		}

		List<String> secondGroup = new ArrayList<String>();
		menuOptions = getResources().getStringArray(R.array.map_admin_options);
		for(int i = 0; i < menuOptions.length; i++){
			secondGroup.add(menuOptions[i]);
		}

		listDataChild.put(listDataHeader.get(0), firstGroup); // Header, Child
																// data
		listDataChild.put(listDataHeader.get(1), secondGroup);
	}
}
