package com.envisprototype.controller;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.view.MapListActivity;
import com.envisprototype.view.model.MapListAdapter;
import com.envisprototype.view.model.SortByAlphMap;

public class CustomSortByOnItemSelectedListenerForMaps implements OnItemSelectedListener {

	public List<MapInterface> maps;
	public MapListAdapter mla;
	public ListView lv;	
	Context context;
	public CustomSortByOnItemSelectedListenerForMaps(List<MapInterface> maps,
			MapListAdapter mla, ListView lv,Context context) {
		super();
		this.context=context;
		this.maps = maps;
		this.mla = mla;
		this.lv = lv;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		if(parent.getItemAtPosition(pos).toString().equals("Alphabetical Order"))
			Collections.sort(maps, new SortByAlphMap());
		else
			Collections.sort(maps, new SortByAlphMap());
		
		mla = new MapListAdapter(context,0,maps);
		
		Log.i("test", maps.toString());
		Log.i("test1",mla.toString() );
		MapListActivity.updateadapter(mla);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
