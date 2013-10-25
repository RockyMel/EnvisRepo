package com.envisprototype.view;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.envisprototype.R;
import com.envisprototype.view.model.SensorTypeArrayAdapter;

public class ChooseSensorTypeActivity extends Activity {

	SensorTypeArrayAdapter staa;
	ListView lv;
	ArrayList<Integer> listofTypes = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String setid = getIntent().getStringExtra("setid");
		Log.i("setid", setid);
		
		setContentView(R.layout.activity_choose_sensor_type);
		listofTypes.add(SensorTypeArrayAdapter.AIRQUALITY);
		listofTypes.add(SensorTypeArrayAdapter.HUMIDITY);
		listofTypes.add(SensorTypeArrayAdapter.LIGHT);
		listofTypes.add(SensorTypeArrayAdapter.MOTION);
		listofTypes.add(SensorTypeArrayAdapter.TEMPERATURE);
		listofTypes.add(SensorTypeArrayAdapter.WATERLEVEL);
		listofTypes.add(SensorTypeArrayAdapter.OTHERS);
		
		staa = new SensorTypeArrayAdapter(this,0,listofTypes,setid);
		lv = (ListView)findViewById(R.id.listView1);
		
		lv.setAdapter(staa);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_sensor_type, menu);
		return true;
	}

}
