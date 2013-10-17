package com.envisprototype.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.EnvisDBAdapter;
import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.controller.CoordinatesReader;
import com.envisprototype.controller.DeleteMapButtonController;
import com.envisprototype.controller.EditMapBtnListener;
import com.envisprototype.controller.SaveMapToAddBtnListener;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.navigation.NavigationMaker;


public class MapInfoViewActivity extends EnvisActivity {
	EditText id;
	EditText name;
	EditText location;
	EditText notes;
	String mapid;
	MapInterface map;
	Button delete, editMapCoorsBtn, saveMapInfoBtn;
	boolean secondTime = false;
	public static Boolean del = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_info_view);
		NavigationMaker.makeDropDownMenu(this, R.array.map_drop_down);
	}

	private void init() {
		MapLocalDBHelper.getSingletonInstance(this).ReplicateMapList();

		// TODO Auto-generated method stub
		id = (EditText)findViewById(R.id.id_map);
		mapid = getIntent().getStringExtra(MapListModel.MAP_ID_EXTRA);
		name = (EditText)findViewById(R.id.editText2);
		location = (EditText)findViewById(R.id.editText3);
		notes = (EditText)findViewById(R.id.editText7);
		if(mapid!=null){
			map = MapListModel.getSingletonInstance().findMapById(mapid);
			id.setText(map.getId());
			name.setText(map.getName());
			delete = (Button)findViewById(R.id.Delete);
			delete.setOnClickListener(new DeleteMapButtonController(map.getId(),this));
			editMapCoorsBtn = (Button) findViewById(R.id.show_map_btn);
			editMapCoorsBtn.setOnClickListener(new EditMapBtnListener(map.getId()));
			saveMapInfoBtn = (Button)findViewById(R.id.Save);
			saveMapInfoBtn.setOnClickListener(new SaveMapToAddBtnListener(this, false, id, name));
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		init();
		EnvisDBAdapter.getSingletonInstance(this).replecateDB();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_info_view, menu);
		return true;
	}

}
