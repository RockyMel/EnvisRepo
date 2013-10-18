package com.envisprototype.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.EnvisDBAdapter;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.LocalDBHelper.SetLocalDBHelper;
import com.envisprototype.controller.PlotSetsBtnListener;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.processing.SetCoordinates;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.model.SetListAdapter;

public class SetListActivity extends Activity {
	public List<SetInterface> sets;
	public static SetListAdapter sla;
	public static ListView lv;
	private Spinner spinner;
	String mapId = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_list);
		
		Button addsensor = (Button)findViewById(R.id.Add);
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			if(bundle.containsKey(getString(R.string.map_id_extra))){
				mapId = bundle.getString(getString(R.string.map_id_extra));
				/* now we have the map id and set list. We need to choose sets
				 *  one by one and plot them to this map.
				 */
			}
		}
		//Log.i("mapId",getIntent().getExtras().getString(getString(R.string.map_id_extra)));
		addsensor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(view.getContext(),InputIDActivity.class);
				intent.putExtra("mode", "set");
				view.getContext().startActivity(intent); 
			}
		});
		
		
		
	}
	
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		EnvisDBAdapter.getSingletonInstance(this).replecateDB();
		Log.i("id", "After SetPlotPapplet " + SetLocalDBHelper.getSingletonInstance(this).getSetList().size());
		Log.i("id", "After SetPlotPapplet " + mapId);
		sets = SetListModel.getSingletonInstance().getSetList();
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			if(bundle.containsKey(getString(R.string.flags))){
				String flag = bundle.getString(getString(R.string.flags));
				if(flag.equals(getString(R.string.sets_to_vis_extra))){
					// getting those sets that belong to this map
					ArrayList<String> setIds = MapSetAssociationDBHelper.
							getSingletoneInstance(this).getListOfSensorsAssosiatedWithMap(mapId);
					sets = SetListModel.getSingletonInstance().getSetListByIds(setIds);
				}
				if(bundle.getString(getString(R.string.flags)).equals(getString(R.string.plot_flag_extra))){
						// get rid of the sets that has already been plotted
					Iterator<SetInterface> iterator = sets.iterator();
					SetInterface setToRemove;
					while(iterator.hasNext()){
						setToRemove = iterator.next();
						if(MapSetAssociationDBHelper.getSingletoneInstance(this).isPlotted(setToRemove.getId())){
							iterator.remove();
						}
					}
				}
			}
		}
		spinner = (Spinner) findViewById(R.id.spinner1);
		//addListenerOnSpinnerItemSelection();
		//Collections.sort(sets,new SortBySetAlph());
		sla = new SetListAdapter(this,0,sets, mapId);
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(sla);
		// here we need to put coordinates of sets into the system
		// 1. associate them with the sets
		// 2. associate plotted sets with the map
		Button plotSetsBtn = (Button) findViewById(R.id.plot_sets_btn);
		plotSetsBtn.setOnClickListener(new PlotSetsBtnListener(mapId));
		//CoordinatesReader setCoorReader = new CoordinatesReader(this);
		//HashMap<String, SetCoordinates> tempSetCoors = setCoorReader.prepareSensorsCoordinates("sensor.txt");
		//Iterator<String> iterator = tempSetCoors.keySet().iterator();
//		while(iterator.hasNext()){
//			String setId = iterator.next();
//			SetCoordinates coors = tempSetCoors.get(setId);
//			SetInterface set = SetListModel.getSingletonInstance().findSetById(setId);
//			if(set!= null){
//				//SetLocalDBHelper.getSingletonInstance(this).editSet(set);
//				MapSetAssociationDBHelper.getSingletoneInstance(this).
//				associateSetWithMap(setId, mapId, coors.getX(), coors.getY(), coors.getZ());
//			}
//		}
	}



	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_list, menu);
		return true;
	}

}
