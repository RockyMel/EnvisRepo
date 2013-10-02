package com.envisprototype.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.view.MapInfoViewActivity;

public class MapViewButtonController implements OnClickListener{

	String id;
	Context context;
	
	public MapViewButtonController(String id, Context context) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.context=context;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(view.getContext(),MapInfoViewActivity.class);
		intent.putExtra(MapListModel.MAP_ID_EXTRA,id);
		view.getContext().startActivity(intent);

	}

}
