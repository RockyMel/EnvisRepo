package com.envisprototype.view;

import java.util.List;

import android.app.Activity;
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
import android.widget.ListView;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.EnvisDBAdapter;
import com.envisprototype.controller.AddSensorButtonController;
import com.envisprototype.controller.SetSaveOnClickController;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.model.Set_SensorListAdapter;
import com.envisprototype.view.processing.SetPlotPApplet;

public class SetInfoViewActivity extends EnvisActivity {


	String setid;
	String flag;
	EditText id;
	EditText name;
	EditText notes;
	ListView sensorlistview;
	Button Add;
	Button Save;
	Button Delete;
	SetInterface set;
	Set_SensorListAdapter sla;
	List<SensorInterface> list;
	Location location;
	String mapId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_info_view);
		setid = getIntent().getStringExtra("setid");
		flag=getIntent().getStringExtra("flag");

		init();


	}



	private void init() {
		// TODO Auto-generated method stub

		id= (EditText) findViewById(R.id.ID);
		name= (EditText) findViewById(R.id.NAME);
		notes= (EditText) findViewById(R.id.NOTES);
		sensorlistview = (ListView) findViewById(R.id.listOfSensors);
		Add = (Button)findViewById(R.id.ADD);
		Save = (Button)findViewById(R.id.Save);
		Delete = (Button)findViewById(R.id.Delete);

		if(flag.equals("new"))
			location=new Location(LocationManager.NETWORK_PROVIDER);
		else
		{
			set = SetListModel.getSingletonInstance().findSetById(setid);

			Log.i("setinfoview", set.getLocation()+"");
			location=set.getLocation();
			Log.i("setinfoview", set.getLocation()+"");
		}
		//Save.setOnClickListener(new SetSaveOnClickController(set,setid,name,notes,list,Delete,Add,flag,location, this));

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

		}
		Add.setOnClickListener(new AddSensorButtonController(setid,list,flag,this));
		
		

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//if(SetListModel.getSingletonInstance().findSetById(setid)!=null){
			//SetInterface tempset= SetListModel.getSingletonInstance().findSetById(setid);
			//list= (List<SensorInterface>) tempset.getSensors();
		EnvisDBAdapter.getSingletonInstance(this).replecateDB();
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
