package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.R;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.view.MapInfoViewActivity;
import com.envisprototype.view.SetListActivity;

public class MapViewButtonController implements OnClickListener{

	String id;
	Context context;
	int mode = 0;
	public MapViewButtonController(String id, Context context) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.context=context;
	}
	
	public MapViewButtonController(String id, Context context, int mode) {
		// TODO Auto-generated constructor stub
		this(id, context);
		this.mode = mode;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		Bundle bundle = ((Activity)context).getIntent().getExtras();
		String flag;
		if(bundle != null && bundle.containsKey(context.getString(R.string.flags))){
			flag = bundle.getString(context.getString(R.string.flags));
			if(flag.equals(context.getString(R.string.plot_flag_extra))){
				// it means we will plot sets or sensors to this map
				intent=new Intent(view.getContext(),SetListActivity.class);
				intent.putExtra(context.getString(R.string.map_id_extra), id);
				intent.putExtra(view.getContext().getString(R.string.flags),
                        view.getContext().getString(R.string.plot_flag_extra));
				((Activity) view.getContext()).startActivity(intent);
				((Activity) view.getContext()).finish();
			}
		}
		switch(mode){
		case 0:{
			intent=new Intent(view.getContext(),MapInfoViewActivity.class);
			intent.putExtra(MapListModel.MAP_ID_EXTRA,id);
			view.getContext().startActivity(intent);
		}
		break;
		case 1:
		case 2:{
			intent = new Intent();
			intent.putExtra(context.getString(R.string.map_id_extra), id);    
			((Activity) view.getContext()).setResult(Activity.RESULT_OK,intent); 
			((Activity) view.getContext()).finish();
		}
		break;
		}
		

	}

}
