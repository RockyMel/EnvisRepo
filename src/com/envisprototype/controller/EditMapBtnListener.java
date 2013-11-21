package com.envisprototype.controller;

import com.envisprototype.view.processing.EditMapApplet;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class EditMapBtnListener  implements OnClickListener{
	String mapId;

	public EditMapBtnListener(String id) {
		// TODO Auto-generated constructor stub
		this.mapId = id;
	}

	@Override
	public void onClick(View v) {
		if(!Checks.isOnline(v.getContext()))
			return;
		// TODO Auto-generated method stub
		Context context = v.getContext();
		Intent intent = new Intent(context, EditMapApplet.class);
		intent.putExtra("mapId", mapId);
		context.startActivity(intent);
	}

}
