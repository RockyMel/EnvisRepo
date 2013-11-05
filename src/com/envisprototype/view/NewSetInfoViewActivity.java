package com.envisprototype.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.EnvisDBAdapter;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.controller.AddSensorButtonController;
import com.envisprototype.controller.PlotSensorsBtnListener;
import com.envisprototype.controller.SetSaveOnClickController;
import com.envisprototype.controller.UnplotSetBtnListener;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.model.set.SetModel;
import com.envisprototype.view.model.GPSTracker;
import com.envisprototype.view.model.Set_SensorListAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NewSetInfoViewActivity extends Activity {


	String setid;
	String flag;

	TextView id;
	EditText name;
	EditText notes;

	ListView sensorlistview;

	ImageButton Add;
	ImageButton Save;
	ImageButton Delete;
	ImageButton plotSensors;
	ImageButton unplotSet;

	SetInterface set;

	Set_SensorListAdapter sla;

	List<SensorInterface> list;

	Location location;

	String mapId = null;

	private GoogleMap map;

	private LatLng LOCATION_SET;
	private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_set_info_view);
		setid = getIntent().getStringExtra("setid");
		flag=getIntent().getStringExtra("flag");
		
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_set_info_view, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		EnvisDBAdapter.getSingletonInstance(this).replecateDB();
		if(setid!=null)
		{list=SensorListModel.getSingletonInstance().getSensorListBySetID(setid);

		sla = new Set_SensorListAdapter(this,0,list,setid);
		Log.i("asda", list+"");
		Log.i("kajsgd", SensorListModel.getSingletonInstance().getSensorList()+"");
		sensorlistview.setAdapter(sla);
		sla.notifyDataSetChanged();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		setid=id.getText().toString();
	}

	private void init() {
		// TODO Auto-generated method stub

		id= (TextView) findViewById(R.id.ID);
		name= (EditText) findViewById(R.id.NAME);
		notes= (EditText) findViewById(R.id.NOTES);
		sensorlistview = (ListView) findViewById(R.id.listOfSensors);
		Add = (ImageButton)findViewById(R.id.Add);
		Save = (ImageButton)findViewById(R.id.Save);
		Delete = (ImageButton)findViewById(R.id.Delete);
		plotSensors = (ImageButton) findViewById(R.id.plot_sensors_btn);
		unplotSet = (ImageButton) findViewById(R.id.unplot_set);
		double latitude = 0;
		double longitude = 0;
		if(flag.equals("new")){
			location=new Location(LocationManager.NETWORK_PROVIDER);
			GPSTracker gps = new GPSTracker(this);

			if(gps.canGetLocation())
			{
				latitude = gps.getLatitude();
				longitude = gps.getLongitude();
			}
			else
			{
				gps.showSettingsAlert();
			}
			set = new SetModel();
		}
		else
		{
			set = SetListModel.getSingletonInstance().findSetById(setid);

			//Log.i("setinfoview", set.getLocation()+"");
			location=set.getLocation();
			//Log.i("setinfoview", set.getLocation()+"");
		}

		Log.i("checkthis",set+"");
		Log.i("checkthis",setid+"");
		Log.i("checkthis",name+"");
		Log.i("checkthis",notes+"");
		Log.i("checkthis",Delete+"");
		Log.i("checkthis",Add+"");
		Log.i("checkthis",flag+"");
		Log.i("checkthis",location+"");


		//+" "+setid+" "+name,notes,Delete,Add,flag,location,);
		Save.setOnClickListener(new SetSaveOnClickController(set,id,setid,name,notes,Delete,Add,flag,location, this));

		if(flag.equals("new"))
		{
			id.setText(setid);
			//id.setText(setid);
			Delete.setVisibility(ImageButton.INVISIBLE);
			Add.setVisibility(ImageButton.INVISIBLE);
			Double lat = latitude;
			Double lng = longitude;

			LOCATION_SET = new LatLng(lat,lng);
			map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			map.addMarker(new MarkerOptions().position(LOCATION_SET).title("Your New Set").icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
			CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_SET);
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			update = CameraUpdateFactory.newLatLngZoom(MELBOURNE, 14);
			map.animateCamera(update);
			update = CameraUpdateFactory.newLatLngZoom(LOCATION_SET, 14);
			map.animateCamera(update);
		}
		else
		{
			id.setText(set.getId());
			name.setText(set.getName());
			notes.setText(set.getNotes());
			Double lat = (Double)set.getLocation().getLatitude();
			Double lng = (Double)set.getLocation().getLongitude();

			LOCATION_SET = new LatLng(lat,lng);
			map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			map.addMarker(new MarkerOptions().position(LOCATION_SET).title("Your New Set").icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
			CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_SET);
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			update = CameraUpdateFactory.newLatLngZoom(MELBOURNE, 14);
			map.animateCamera(update);
			update = CameraUpdateFactory.newLatLngZoom(LOCATION_SET, 14);
			map.animateCamera(update);
			//list= (List<SensorInterface>) set.getSensors();
			list=SensorListModel.getSingletonInstance().getSensorListBySetID(setid);
			sla = new Set_SensorListAdapter(this,0,list,setid);
			mapId = set.getMapID();
			if(MapSetAssociationDBHelper.getSingletoneInstance(this).getCoordsForSet(setid)!=null){
				plotSensors.setVisibility(View.VISIBLE);
				unplotSet.setVisibility(View.VISIBLE);
				ArrayList<String> sensorIdsToPlot = new ArrayList<String>();
				for(SensorInterface sensor: list){
						sensorIdsToPlot.add(sensor.getId());
				}
				plotSensors.setOnClickListener(new PlotSensorsBtnListener(sensorIdsToPlot, mapId));
				unplotSet.setOnClickListener(new UnplotSetBtnListener(setid, plotSensors));
				// need to get ids for sensors belonging to this set that are to be plotted
				
				
			}
			else{
				plotSensors.setVisibility(View.INVISIBLE);
				unplotSet.setVisibility(View.INVISIBLE);
			}
			sensorlistview.setAdapter(sla);

		}
		//setid="set1";
		Add.setOnClickListener(new AddSensorButtonController(setid,list,flag,this));



	}

}
