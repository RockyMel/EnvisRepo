package com.envisprototype.model.sensor;

import android.location.Location;

public class SensorModel extends AbstractSensor{

	public SensorModel() {
		
		// TODO Auto-generated constructor stub
	}

	public SensorModel(String name,int type,String brand,String notes,
			String state, Location location, double minValue, double maxValue) {
		super(name,type,brand,notes,state,location, minValue, maxValue);
		// TODO Auto-generated constructor stub
	}


	
}
