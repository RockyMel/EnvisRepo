package com.envisprototype.controller;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.view.View.OnClickListener;

public class SensorsChosenListener implements OnClickListener, OnMultiChoiceClickListener{
	private ArrayList<Boolean> chosenItems;
	
	public SensorsChosenListener(ArrayList<Boolean> chosenItems) {
		// TODO Auto-generated constructor stub
		this.chosenItems = chosenItems;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
