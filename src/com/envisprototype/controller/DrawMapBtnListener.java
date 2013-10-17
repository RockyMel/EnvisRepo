package com.envisprototype.controller;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.envisprototype.R;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.view.processing.DrawMapApplet;

public class DrawMapBtnListener implements OnClickListener{
	String mapId;
	
	public DrawMapBtnListener(String mapId){
		this.mapId = mapId;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(MapListModel.getSingletonInstance().findMapById(mapId)!=null){
			Intent intent = new Intent(v.getContext(), DrawMapApplet.class);
			intent.putExtra(v.getContext().getString(R.string.map_id_extra), mapId);
			v.getContext().startActivity(intent);
		}
		else{
			// map must be saved
			Toast.makeText(v.getContext().getApplicationContext(), "SaveMapFirst",
					   Toast.LENGTH_LONG).show();
		}
	}
	
	

}
