package com.envisprototype.controller;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.R;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.processing.PlotPApplet;

public class PlotSetsBtnListener implements OnClickListener{

	String mapId;
	public PlotSetsBtnListener(String mapId) {
		// TODO Auto-generated constructor stub
		this.mapId = mapId;
	}

	@Override
	public void onClick(View v) {
		if(!Checks.isOnline(v.getContext()))
			return;
		// TODO Auto-generated method stub
		ArrayList<String> listToPlot = new ArrayList<String>();
		for(SetInterface setToPlot: SetListModel.getSingletonInstance().getSetList()){
			if(setToPlot.isIftoPlot()){
				listToPlot.add(setToPlot.getId());
			}
		}
		
		// now we have all the setids to plot
		Intent intent = new Intent(v.getContext(),PlotPApplet.class);
		intent.putExtra(v.getContext().getString(R.string.map_id_extra), mapId);
		intent.putExtra(v.getContext().getString(R.string.flags),
				v.getContext().getString(R.string.plot_flag_extra));
		intent.putStringArrayListExtra(v.getContext().getString(R.string.sets_id_extra), listToPlot);
		v.getContext().startActivity(intent);
	}

}
