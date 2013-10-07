package com.envisprototype.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import android.content.Context;
import android.util.Log;

import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.processing.Coordinates;

public class ModelSaver {

	Context context;
	
	public ModelSaver(Context context){
		this.context = context;
	}
	
	public void saveMapsToFiles(){
		  try {
			  BufferedWriter bufferedWriter;
			  String stringToWrite = new String();
			  //saving maps
			  Coordinates mapCoors = new Coordinates();
			  String mapId = new String();
			  String mapName = new String();
			  Log.i("model",Integer.toString(MapListModel.getSingletonInstance().getMapList().size()));
			  for(int i = 0; i < MapListModel.getSingletonInstance().getMapList().size(); i++){
				  mapId = MapListModel.getSingletonInstance().
						  getMapList().get(i).getId();
				  mapName = MapListModel.getSingletonInstance().
						  getMapList().get(i).getName();
				  mapCoors = MapListModel.getSingletonInstance().
						  getMapList().get(i).getRealCoordinates();
				  bufferedWriter = new BufferedWriter(new FileWriter(new 
		                    File(context.getFilesDir()+File.separator+mapId+".emp")));
				  stringToWrite = "name:" + mapName + ";";
				  bufferedWriter.write(stringToWrite);
				  for(int j = 0; j < mapCoors.getCoorX().size(); j++){
					  stringToWrite = "x:" + mapCoors.getCoorX().get(j).toString();
					  bufferedWriter.write(stringToWrite);
					  stringToWrite = "y:" + mapCoors.getCoorY().get(j).toString() + ";";
					  bufferedWriter.write(stringToWrite);
					  }
				  Log.i("model", mapId);
				  Log.i("model", mapName);
				  Log.i("model", mapCoors.toString());
				  bufferedWriter.flush();
				  bufferedWriter.close();
			  }
			} catch (Exception e) {
			  e.printStackTrace();
			}
		  // testing
		}
}
