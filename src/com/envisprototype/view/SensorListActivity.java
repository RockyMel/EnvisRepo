package com.envisprototype.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.EnvisDBAdapter;
import com.envisprototype.controller.CustomSortByOnItemSelectedListenerForSensors;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.sensor.SensorModel;
import com.envisprototype.view.model.SensorListAdapter;
import com.envisprototype.view.model.SortByAlph;


public class SensorListActivity extends Activity {
	public List<SensorInterface> sensors;
	public static SensorListAdapter sla;
	public static ListView lv;
	private Spinner spinner;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_temp);
		
		//WILL BE REMOVED (TEMPORARY TO DEMO ADD SENSOR)
		Button addsensor = (Button)findViewById(R.id.map_by_name_btn);
		addsensor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
//				Intent intent=new Intent(view.getContext(),InputIDActivity.class);
//				intent.putExtra("mode", "sensor");
//				view.getContext().startActivity(intent); 
			}
		});
		
		sensors = SensorListModel.getSingletonInstance().getSensorList();
		//CODE TO FETCH THE LIST OF SENSORS FROM A HELPER CLASS
		/*
		sensors = new ArrayList(10);
		Location loc1 = new Location(LocationManager.NETWORK_PROVIDER);
		loc1.setLatitude(24.8167);
		loc1.setLongitude(143.9667);
		Sensor s1 = new Sensor("A",loc1);
		sensors.add(s1);
		
		Location loc2 = new Location(LocationManager.NETWORK_PROVIDER);
		loc2.setLatitude(1.8167);
		loc2.setLongitude(145.9667);
		Sensor s2 = new Sensor("Z",loc2);
		sensors.add(s2);
		
		Location loc3 = new Location(LocationManager.NETWORK_PROVIDER);
		loc3.setLatitude(-20.8167);
		loc3.setLongitude(143.9667);
		Sensor s3 = new Sensor("X",loc3);
		sensors.add(s3);
		
		Location loc4 = new Location(LocationManager.NETWORK_PROVIDER);
		loc4.setLatitude(-40.8167);
		loc4.setLongitude(147.9667);
		Sensor s4 = new Sensor("H",loc4);
		sensors.add(s4);

		Location loc5 = new Location(LocationManager.NETWORK_PROVIDER);
		loc5.setLatitude(-41.8167);
		loc5.setLongitude(149.9667);
		Sensor s5 = new Sensor("G",loc5);
		sensors.add(s5);
		*/
		
		
		
//		//Code to add something to the spinner 
		spinner = (Spinner) findViewById(R.id.spinner1);

		addListenerOnSpinnerItemSelection();
			Collections.sort(sensors,new SortByAlph());

		
		//Set the adapter for the list of sensors
		sla = new SensorListAdapter(this,0,sensors);
		lv=(ListView)findViewById(R.id.list);
		lv.setAdapter(sla);
	
		//setListAdapter(sla);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addListenerOnSpinnerItemSelection() {
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(new CustomSortByOnItemSelectedListenerForSensors(sensors, sla, lv, this));
		//sla.notifyDataSetChanged();
	  }
	
	public static void  updateadapter(SensorListAdapter sla2)
	{
		//ea.remove(event);
		sla=sla2;
		lv.setAdapter(sla);
		sla.notifyDataSetChanged();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//sensors.clear();
		EnvisDBAdapter.getSingletonInstance(this).replecateDB();
		
		sensors = SensorListModel.getSingletonInstance().getSensorList();
		Log.i("TEST", sensors.toString());
		sla = new SensorListAdapter(this,0,sensors);
		lv.setAdapter(sla);
		sla.notifyDataSetChanged();
	}

}
