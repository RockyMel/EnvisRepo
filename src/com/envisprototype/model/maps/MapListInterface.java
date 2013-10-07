package com.envisprototype.model.maps;

import java.util.List;

import android.content.Context;

public interface MapListInterface {

	public abstract void addMap(MapInterface map);

	public abstract void removeMap(MapInterface map);

	public abstract MapInterface findMapById(String Id);

	public abstract List<MapInterface> getMapList();
	
	public abstract void resetModel(Context context);

}