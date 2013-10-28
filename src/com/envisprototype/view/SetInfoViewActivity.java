package com.envisprototype.view;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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
import com.envisprototype.view.model.Set_SensorListAdapter;

public class SetInfoViewActivity extends EnvisActivity {


	String setid;
	String flag;
	TextView id;
	EditText name;
	EditText notes;
	ListView sensorlistview;
	ImageButton Add;
	ImageButton Save;
	ImageButton Delete;
	SetInterface set;
	Set_SensorListAdapter sla;
	List<SensorInterface> list;
	Location location;
	String mapId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_set_info_view);
		setid = getIntent().getStringExtra("setid");
		flag=getIntent().getStringExtra("flag");

		


	}



	private void init() {
		// TODO Auto-generated method stub

		id= (TextView) findViewById(R.id.ID);
		name= (EditText) findViewById(R.id.NAME);
		notes= (EditText) findViewById(R.id.NOTES);
		sensorlistview = (ListView) findViewById(R.id.listOfSensors);
		Add = (ImageButton)findViewById(R.id.ADD);
		Save = (ImageButton)findViewById(R.id.Save);
		Delete = (ImageButton)findViewById(R.id.Delete);
		ImageButton plotSensors = (ImageButton) findViewById(R.id.plot_sensors_btn);
		ImageButton unplotSet = (ImageButton) findViewById(R.id.unplot_set);

		if(flag.equals("new"))
			location=new Location(LocationManager.NETWORK_PROVIDER);
		else
		{
			set = SetListModel.getSingletonInstance().findSetById(setid);

			Log.i("setinfoview", set.getLocation()+"");
			location=set.getLocation();
			Log.i("setinfoview", set.getLocation()+"");
		}
		Save.setOnClickListener(new SetSaveOnClickController(set,id, setid,name,notes,Delete,Add,flag,location, this));

		if(flag.equals("new"))
		{
			id.setText(setid);
			Delete.setVisibility(Button.INVISIBLE);
			Add.setVisibility(Button.INVISIBLE);
		}
		else
		{
			id.setText(set.getId());
			name.setText(set.getName());
			notes.setText(set.getNotes());
			//list= (List<SensorInterface>) set.getSensors();
			list=SensorListModel.getSingletonInstance().getSensorListBySetID(setid);
			sla = new Set_SensorListAdapter(this,0,list,setid);
			sensorlistview.setAdapter(sla);
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

		}
		//Add.setOnClickListener(new AddSensorButtonController(setid,list,flag,this));
		
		

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		//if(SetListModel.getSingletonInstance().findSetById(setid)!=null){
			//SetInterface tempset= SetListModel.getSingletonInstance().findSetById(setid);
			//list= (List<SensorInterface>) tempset.getSensors();
		EnvisDBAdapter.getSingletonInstance(this).replecateDB();
		init();
			list=SensorListModel.getSingletonInstance().getSensorListBySetID(setid);

			sla = new Set_SensorListAdapter(this,0,list,setid);
			Log.i("asda", list+"");
			sensorlistview.setAdapter(sla);
			sla.notifyDataSetChanged();
		//}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_info_view, menu);
		return true;
	}

}
