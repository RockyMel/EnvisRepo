package com.envisprototype.view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.LocalDBHelper.SetSensorAssociationLocalDBHelper;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.model.ExpandableListAdapter;
public class SetsForMapExpandableListView extends Activity{


	public List<SetInterface> sets;
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	String mapId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensors_exp_view);
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.sensorTypes);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	@SuppressWarnings("unchecked")
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			if(bundle.containsKey(getString(R.string.map_id_extra))){
				mapId = bundle.getString(getString(R.string.map_id_extra));
			if(bundle.containsKey(getString(R.string.flags))){
				String flag = bundle.getString(getString(R.string.flags));
				if(flag.equals(getString(R.string.sets_to_vis_extra))){
					// getting those sets that belong to this map
					ArrayList<String> setIds = MapSetAssociationDBHelper.
							getSingletoneInstance(this).getListOfSensorsAssosiatedWithMap(mapId);
					sets = SetListModel.getSingletonInstance().getSetListByIds(setIds);
				}
			}
		}
		}
		else{
			sets = SetListModel.getSingletonInstance().getSetList();
		}

		// Adding child data
		List<String> sensorsIds;
		for(SetInterface setToShow: sets){
			listDataHeader.add(setToShow.getId()+":"+setToShow.getName());
			// Adding child data
			sensorsIds = SetSensorAssociationLocalDBHelper.getSingletonInstance(this).
					getListOfSensorsAssosiatedWithSet(setToShow.getId());
			listDataChild.put(listDataHeader.get(listDataHeader.size()-1), sensorsIds); // Header, Child
		}
		
//		listDataHeader.add("Air Quality");
//		listDataHeader.add("Humidity");
//		listDataHeader.add("Light");
//		listDataHeader.add("Motion");
//		listDataHeader.add("Temperature");
//		listDataHeader.add("Water Level");
//		listDataHeader.add("Others");
		

		
		
		
//		List<String> AQ = SensorListModel.getSingletonInstance().getSensorIDListByType(1);
//		List<String> H = SensorListModel.getSingletonInstance().getSensorIDListByType(2);
//		List<String> L = SensorListModel.getSingletonInstance().getSensorIDListByType(3);
//		List<String> M = SensorListModel.getSingletonInstance().getSensorIDListByType(4);
//		List<String> T = SensorListModel.getSingletonInstance().getSensorIDListByType(5);
//		List<String> WL = SensorListModel.getSingletonInstance().getSensorIDListByType(6);
//		List<String> O = SensorListModel.getSingletonInstance().getSensorIDListByType(7);
		
//		Bundle bundle = getIntent().getExtras();
//		if(bundle != null){
//			if(bundle.containsKey(getString(R.string.map_id_extra))){
//				String mapId = bundle.getString(getString(R.string.map_id_extra));
//				// maybe modify to get lists only if they are associated rather than
//				// removing those who don't
//				ArrayList<String> setIds = MapSetAssociationDBHelper.
//						getSingletoneInstance(this).getListOfSensorsAssosiatedWithMap(mapId);
//				Set AQCommon = new HashSet(AQ);
//				Set HCommon = new HashSet(H);
//				Set LCommon = new HashSet(L);
//				Set MCommon = new HashSet(M);
//				Set TCommon = new HashSet(T);
//				Set WLCommon = new HashSet(WL);
//				Set OCommon = new HashSet(O);
//				AQCommon.retainAll(setIds);
//				HCommon.retainAll(setIds);
//				LCommon.retainAll(setIds);
//				MCommon.retainAll(setIds);
//				TCommon.retainAll(setIds);
//				WLCommon.retainAll(setIds);
//				OCommon.retainAll(setIds);
//				AQ = new ArrayList<String>(AQCommon);
//				H = new ArrayList<String>(HCommon);
//				L = new ArrayList<String>(LCommon);
//				M = new ArrayList<String>(MCommon);
//				T = new ArrayList<String>(TCommon);
//				WL = new ArrayList<String>(WLCommon);
//				O = new ArrayList<String>(OCommon);
//				
//			}
//		}
		
		
//		listDataChild.put(listDataHeader.get(0), AQ); // Header, Child
//		listDataChild.put(listDataHeader.get(1), H); // Header, Child
//		listDataChild.put(listDataHeader.get(2), L); // Header, Child
//		listDataChild.put(listDataHeader.get(3), M); // Header, Child
//		listDataChild.put(listDataHeader.get(4), T); // Header, Child
//		listDataChild.put(listDataHeader.get(5), WL); // Header, Child
//		listDataChild.put(listDataHeader.get(6), O); // Header, Child

		
	}


}
