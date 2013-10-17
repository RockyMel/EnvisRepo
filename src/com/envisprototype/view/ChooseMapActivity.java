package com.envisprototype.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.envisprototype.R;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;

public class ChooseMapActivity extends EnvisActivity {
	
	String mapId = null;
	String sensorId = null;
	String setId = null;
	String flag = null;
	Intent intent = new Intent();
	
	ImageButton mapByNameBtn, scanQRBtn, nearestMapsBtn;
	Button chooseSensorsBtn, plotToMapBtn;
	final Activity context = this;
	TextView mapIdTv, mapNameTv, mapLocationTv, mapNotesTv;
	
	public static final int MAP_BY_NAME = 1;
	public static final int MAP_BY_LOCATION = 2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_map);
		//NavigationMaker.makeDropDownMenu(this,R.array.map_drop_down);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Bundle bundle = getIntent().getExtras();
		// if sensor_id_extra is there - we are plotting sensor
		intent = new Intent(this, ThreeDVisualisation.class);
		if(bundle != null){
			if(bundle.containsKey(getString(R.string.flags))){
				flag = bundle.getString(getString(R.string.flags));
				if(flag.equals(getString(R.string.plot_flag_extra))){
					// we need to plot all the sets/sentsors to this map
				}
			}
		}
		init();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.visualisation, menu);
		return true;
	}
	
	private void init(){
		mapIdTv = (TextView) findViewById(R.id.mapIDTV);
		mapNameTv = (TextView) findViewById(R.id.mapNameTV);
		mapLocationTv = (TextView) findViewById(R.id.mapLocationTV);
		mapNotesTv = (TextView) findViewById(R.id.mapNotesTV);
		mapByNameBtn = (ImageButton) findViewById(R.id.map_by_name_btn);
		scanQRBtn = (ImageButton) findViewById(R.id.scan_qr_btn);
		nearestMapsBtn = (ImageButton) findViewById(R.id.nearest_maps_btn);
		chooseSensorsBtn = (Button) findViewById(R.id.chooseSensorsBtn);
		plotToMapBtn = (Button) findViewById(R.id.plot_to_map_btn);
		if(mapId == null){
			chooseSensorsBtn.setVisibility(Button.INVISIBLE);
			plotToMapBtn.setVisibility(Button.INVISIBLE);
		}
		mapByNameBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, MapListActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				if(flag != null)
					intent.putExtra(v.getContext().getString(R.string.flags),
							flag);
				context.startActivityForResult(intent, MAP_BY_NAME);
			}
		});
		
		nearestMapsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, MapListActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				if(flag != null)
					intent.putExtra(v.getContext().getString(R.string.flags),
							flag);
				context.startActivityForResult(intent, MAP_BY_LOCATION);
			}
		});
		
		chooseSensorsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, ThreeDVisualisation.class);
				intent.putExtra(getString(R.string.map_id_extra), mapId);
				context.startActivity(intent);
			}
		});
		
		plotToMapBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.putExtra(getString(R.string.map_id_extra), mapId);
				v.getContext().startActivity(intent);
			}
		});
	}
	
	@Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra(getString(R.string.request_code), requestCode);
        super.startActivityForResult(intent, requestCode);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if(resultCode == RESULT_OK){   
			 if((mapId = data.getExtras().getString("mapId"))!=null){
				 //Log.i("id","in choose map result id = " + mapId);
				 MapInterface map = MapListModel.getSingletonInstance().findMapById(mapId);
				 init();
				 Log.i("id","in choose map result id = " + map.getId());
				 mapIdTv.setText(map.getId());
				 mapNameTv.setText(map.getName());
				 mapLocationTv.setText(map.getLocation().toString());
				 mapNotesTv.setText(map.getNotes());
				 chooseSensorsBtn.setVisibility(Button.VISIBLE);
			 }
				 
	     }
	     if (resultCode == RESULT_CANCELED) {    
	         //Write your code if there's no result
	     }
	  }
}