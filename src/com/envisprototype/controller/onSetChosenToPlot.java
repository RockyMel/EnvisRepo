package com.envisprototype.controller;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;

public class onSetChosenToPlot implements OnCheckedChangeListener{
	
	String setId;
	
	public onSetChosenToPlot(String setId){
		this.setId = setId;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			SetInterface setToPlot = SetListModel.getSingletonInstance().findSetById(setId);
			setToPlot.setIftoPlot(isChecked);
	}

}
