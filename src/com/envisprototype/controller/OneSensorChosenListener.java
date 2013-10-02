package com.envisprototype.controller;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class OneSensorChosenListener implements OnCheckedChangeListener{

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(buttonView.getText().equals("Choose all sensors")){
			System.out.println("123");
		}
		System.out.println(buttonView.getText());
	}

}
