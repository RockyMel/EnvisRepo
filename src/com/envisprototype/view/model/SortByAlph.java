 package com.envisprototype.view.model;

import java.util.Comparator;

import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.sensor.SensorInterface;

public class SortByAlph implements Comparator {

	
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		SensorInterface s1=(SensorInterface)o1;
		SensorInterface s2=(SensorInterface)o2;
        return s1.getName().compareTo(s2.getName());

		
        

	}

	
}
