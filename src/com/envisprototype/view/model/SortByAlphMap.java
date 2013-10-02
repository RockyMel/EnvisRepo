package com.envisprototype.view.model;

import java.util.Comparator;

import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.sensor.SensorInterface;

public class SortByAlphMap implements Comparator<MapInterface> {

	@Override
	public int compare(MapInterface o1, MapInterface o2) {
		// TODO Auto-generated method stub
		MapInterface s1=(MapInterface)o1;
		MapInterface s2=(MapInterface)o2;
        return s1.getName().compareTo(s2.getName());

	}

}
