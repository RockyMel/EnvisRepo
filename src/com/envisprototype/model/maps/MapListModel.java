package com.envisprototype.model.maps;

import java.util.ArrayList;
import java.util.List;

import com.envisprototype.model.processing.Coordinates;

import android.content.Context;
import android.util.Log;




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
	

	@Override
	public void editMap(MapInterface map) {
		for(MapInterface mapToEdit: mapList){
			if(mapToEdit.getId().equals(map.getId())){
				mapToEdit.setName(map.getName());
				mapToEdit.setLocation(map.getLocation());
				mapToEdit.setRealCoordinates(map.getRealCoordinates());
				mapToEdit.setNotes(map.getNotes());
				mapToEdit.setzCoordinate(map.getzCoordinate());
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.example.envisproto.Model.Maps.MapListInterface#findMapById(java.lang.String)
	 */
	@Override
	public MapInterface findMapById(String Id){
		
		for(MapInterface map: mapList){
			if(map.getId().equals(Id)){
				Log.i("map ids", map.getId() + " - " + Id);
				return map;
			}
										
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

//	@Override
//	public void resetModel(Context context) {
//		// TODO Auto-generated method stub
//		singletonInstance = new MapListModel();
//		ModelReader modelReader = new ModelReader(context);
//		modelReader.readModel();
//		 for(int j = 0; j < mapList.size(); j++){
//			 Log.i("reset", mapList.get(j).getId());
//			  Log.i("reset", mapList.get(j).getName());
//			  Log.i("reset", "###############");
//		 }
//	}
	
	public void setMapList(List<MapInterface> mapList){
		
		this.mapList=mapList;
	}
//	public boolean isSensorListEmpty()
//	{
//		return sensorList.isEmpty();
//
//	}

	@Override
	public void ReplicateMapList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveCoorsForMap(String mapId, Coordinates coorsToSave,
			float zCoor) {
		// TODO Auto-generated method stub
		MapInterface tempMap = this.findMapById(mapId);
		tempMap.setRealCoordinates(coorsToSave);
		tempMap.setzCoordinate(zCoor);
		
	}
}
