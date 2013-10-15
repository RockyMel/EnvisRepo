package com.envisprototype.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.controller.CoordinatesReader;
import com.envisprototype.controller.DrawMapBtnListener;
import com.envisprototype.controller.SaveMapToAddBtnListener;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.maps.MapModel;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.model.GPSTracker;


public class AddMapActivity extends EnvisActivity {
	
	EditText id;
	EditText name;
	EditText location;
	EditText notes;
	MapInterface map;
	Location myloc =  new Location(LocationManager.NETWORK_PROVIDER);
	Button drawMapBtn;
	Button SaveBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_map);

		init();

	}
	
	private void init() {
		// TODO Auto-generated method stub
		id = (EditText)findViewById(R.id.editText1);
		name  = (EditText)findViewById(R.id.editText2);
		location = (EditText)findViewById(R.id.editText3);
		notes = (EditText)findViewById(R.id.editText7);
		map = new MapModel();
		drawMapBtn = (Button) findViewById(R.id.draw_map_btn);
		drawMapBtn.setOnClickListener(new DrawMapBtnListener(this));
		final Context context = this;
		SaveBtn = (Button)findViewById(R.id.savebutton);
		SaveBtn.setOnClickListener(new SaveMapToAddBtnListener(this, true, id, name));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//CODE TO SET THE TEXT FIELDS FROM QR READER
		MapLocalDBHelper.getSingletonInstance(this).ReplicateMapList();
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_map, menu);
		return true;
	}

}
