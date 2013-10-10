package com.envisprototype.view;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.controller.CustomSortByOnItemSelectedListenerForMaps;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.view.model.MapListAdapter;
import com.envisprototype.view.model.SortByAlphMap;
import com.envisprototype.R;


public class MapListActivity extends Activity {
	public List<MapInterface> maps;
	public static MapListAdapter mla;
	public static ListView lv;
	private Spinner spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_map_list);

		//WILL BE REMOVED (TEMPORARY TO DEMO ADD SENSOR)
		Button addmap = (Button)findViewById(R.id.button1);
		addmap.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(view.getContext(),AddMapActivity.class);
				view.getContext().startActivity(intent); 
			}
		});
		maps = MapListModel.getSingletonInstance().getMapList();
	
		spinner = (Spinner) findViewById(R.id.spinner1);

		addListenerOnSpinnerItemSelection();
			Collections.sort(maps,new SortByAlphMap());

		
		//Set the adapter for the list of sensors
		mla = new MapListAdapter(this,0,maps);
		lv=(ListView)findViewById(R.id.list);
		lv.setAdapter(mla);
	}

	public void addListenerOnSpinnerItemSelection() {
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(new CustomSortByOnItemSelectedListenerForMaps(maps, mla, lv, this));
		//sla.notifyDataSetChanged();
	  }
	
	public static void  updateadapter(MapListAdapter mla2)
	{
		//ea.remove(event);
		mla=mla2;
		lv.setAdapter(mla);
		mla.notifyDataSetChanged();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//sensors.clear();
		MapLocalDBHelper.getSingletonInstance(this).ReplicateMapList();
		maps = MapListModel.getSingletonInstance().getMapList();
		Log.i("TEST", maps.toString());
		mla = new MapListAdapter(this,0,maps);
		lv.setAdapter(mla);
		mla.notifyDataSetChanged();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_list, menu);
		return true;
	}

}
