package com.envisprototype.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import processing.core.PApplet;
import android.content.Context;
import android.util.Log;

import com.envisprototype.model.maps.MapInterface;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.maps.MapModel;
import com.envisprototype.model.processing.Coordinates;

public class ModelReader {

	Context context;
	CoordinatesReader coorReader;
	Coordinates mapCoors;
	String mapName, mapId, lineToParse;
	BufferedReader mapBReader;
	
	public ModelReader(Context context){
		this.context = context;
		coorReader = new CoordinatesReader(context);
		mapCoors = new Coordinates();
	}
	public void readModel(){
		MapInterface tempMap;
		String[][] mapNameArray;
		  for(File mapFile: context.getFilesDir().listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.toLowerCase().endsWith(".emp");}
			    }
		  )){
			  // now let's populate the map model with maps
			  tempMap = new MapModel();
			  Log.i("model", "in read model");
			  try{
				    mapBReader = new BufferedReader(new FileReader(new 
			                File(context.getFilesDir()+File.separator+mapFile.getName())));
//				    while((lineToParse = mapBReader.readLine())!=null){
				    	lineToParse = mapBReader.readLine();
				    	Log.i("parse", lineToParse);
				    	if(lineToParse != null){
				    		mapNameArray = PApplet.matchAll(lineToParse, "name:\\d+");
							if(mapNameArray != null && mapNameArray.length > 0)
								mapName = PApplet.matchAll(mapNameArray[0][0],"\\d+")[0][0];
							else
								mapName = "parsing problem";
							tempMap.setName(mapName);
							Log.i("ModelReaderName",mapName);
							mapId = mapFile.getName().replace(".emp","");
							tempMap.setId(mapId);
							Log.i("ModelReaderId",mapId);
				    	}
				    	
//				    }
				    mapBReader.close();
				    mapCoors = coorReader.prepareMapCoordinates(mapFile.getName());
					tempMap.setRealCoordinates(mapCoors);
					MapListModel.getSingletonInstance().addMap(tempMap);
				  }catch(IOException e){
					  lineToParse = null;
				    e.printStackTrace();
				  }
		  }
		//mapCoors = coorReader.prepareMapCoordinates("map.txt");
	}
}
