package com.envisprototype.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.envisprototype.R;
import com.envisprototype.controller.DeleteMapButtonController;
import com.envisprototype.controller.Show3DMapBtnListener;
import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;


public class MapInfoViewActivity extends Activity {
	EditText id;
	EditText name;
	EditText location;
	EditText notes;
	Button delete;
	String mapid;
	MapInterface map;
	Button show3DMapBtn;
	public static Boolean del = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_info_view);
		mapid = getIntent().getStringExtra(MapListModel.MAP_ID_EXTRA);

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		id = (EditText)findViewById(R.id.id_map);
		
		name = (EditText)findViewById(R.id.editText2);
		location = (EditText)findViewById(R.id.editText3);
		notes = (EditText)findViewById(R.id.editText7);
		map = MapListModel.getSingletonInstance().findMapById(mapid);
		Log.i("asdsad", map.getId());
		id.setText(map.getId());
		name.setText(map.getName());
		delete = (Button)findViewById(R.id.Delete);
		delete.setOnClickListener(new DeleteMapButtonController(map.getId(),this));
		show3DMapBtn = (Button) findViewById(R.id.show_map_btn);
		show3DMapBtn.setOnClickListener(new Show3DMapBtnListener(map.getId()));
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		if(!del){
		map.setId(id.getText().toString());
		map.setName(name.getText().toString());

		MapListModel.getSingletonInstance().removeMap(map);
		MapListModel.getSingletonInstance().addMap(map);
		}
		else
			del = false;




	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_info_view, menu);
		return true;
	}

}
