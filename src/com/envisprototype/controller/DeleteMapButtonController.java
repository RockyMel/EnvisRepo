package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
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

		//MapListModel.getSingletonInstance().removeMap(map);
		MapLocalDBHelper.getSingletonInstance(context).removeMap(map);
		MapLocalDBHelper.getSingletonInstance(context).ReplicateMapList();
		MapInfoViewActivity.del=true;
		Intent intent = new Intent(context, MapInfoViewActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
		//this.context.finish();
	}

}
