package com.envisprototype.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.envisprototype.R;
import com.envisprototype.controller.ExpandableListAdapter;
import com.envisprototype.model.sensor.SensorListModel;

public class SensorsExpandableListView extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensors_exp_view);
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.sensorTypes);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Air Quality");
		listDataHeader.add("Humidity");
		listDataHeader.add("Light");
		listDataHeader.add("Motion");
		listDataHeader.add("Temperature");
		listDataHeader.add("Water Level");
		listDataHeader.add("Others");
		

		// Adding child data
		List<String> AQ = SensorListModel.getSingletonInstance().getSensorListByType(1);
		List<String> H = SensorListModel.getSingletonInstance().getSensorListByType(2);
		List<String> L = SensorListModel.getSingletonInstance().getSensorListByType(3);
		List<String> M = SensorListModel.getSingletonInstance().getSensorListByType(4);
		List<String> T = SensorListModel.getSingletonInstance().getSensorListByType(5);
		List<String> WL = SensorListModel.getSingletonInstance().getSensorListByType(6);
		List<String> O = SensorListModel.getSingletonInstance().getSensorListByType(7);
		
		
		listDataChild.put(listDataHeader.get(0), AQ); // Header, Child
		listDataChild.put(listDataHeader.get(1), H); // Header, Child
		listDataChild.put(listDataHeader.get(2), L); // Header, Child
		listDataChild.put(listDataHeader.get(3), M); // Header, Child
		listDataChild.put(listDataHeader.get(4), T); // Header, Child
		listDataChild.put(listDataHeader.get(5), WL); // Header, Child
		listDataChild.put(listDataHeader.get(6), O); // Header, Child

		
	}
}
