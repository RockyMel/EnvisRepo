package com.envisprototype.view.model;

import java.util.Observable;
import java.util.Observer;

import android.util.Log;

public class ChartData extends Observable{

	Number[][] data;

	public Number[][] getData() {
		return data;
	}

	public void setData(Number[][] data) {
		this.data = data;
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		setChanged();
		super.notifyObservers();
		Log.i("ChartData", "now");
	}

	
	
}
