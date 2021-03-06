package com.envisprototype.model.maps;

import java.util.ArrayList;
import java.util.List;



public class MapListModel implements MapListInterface {

	private static MapListInterface singletonInstance;
	public static final String MAP_ID_EXTRA="mapidextra";
	List<MapInterface> mapList = new ArrayList<MapInterface>();

	

	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Maps.MapListInterface#addMap(com.example.envisproto.Model.Maps.MapInterface)
	 */
	@Override
	public void addMap(MapInterface map){
		mapList.add(map);
	}
	
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Maps.MapListInterface#removeMap(com.example.envisproto.Model.Maps.MapInterface)
	 */
	@Override
	public void removeMap(MapInterface map){
		mapList.remove(map);
		
	}
	
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Maps.MapListInterface#findMapById(java.lang.String)
	 */
	@Override
	public MapInterface findMapById(String Id){
		
		for(MapInterface map: mapList){
			if(map.getId().equals(Id))
				return map;						
		}
		return  null;
		
		
	}
	

	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Maps.MapListInterface#getMapList()
	 */
	@Override
	public List<MapInterface> getMapList(){

		//can still modify individual events
		return mapList;
	}
	
	//only one model!!
	public static MapListInterface getSingletonInstance() {
		if(singletonInstance==null)
			singletonInstance=new MapListModel();

		return singletonInstance;
	}
	
//	public boolean isSensorListEmpty()
//	{
//		return sensorList.isEmpty();
//
//	}
}
