package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.view.MapInfoViewActivity;

public class DeleteMapButtonController implements OnClickListener {

	String id;
	Activity context;

	public DeleteMapButtonController(String id,
			Context context) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.context=(Activity)context;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MapInterface map = MapListModel.getSingletonInstance().findMapById(id);

		MapListModel.getSingletonInstance().removeMap(map);

		MapInfoViewActivity.del=true;
		this.context.finish();
	}

}
