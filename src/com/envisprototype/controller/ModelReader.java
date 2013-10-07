package com.envisprototype.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
	String name, xCoorStr, yCoorStr;
	ArrayList<Float> xCoor, yCoor;
	
	public ModelReader(Context context){
		this.context = context;
		coorReader = new CoordinatesReader(context);
		mapCoors = new Coordinates();
		xCoor = new ArrayList<Float>();
		yCoor = new ArrayList<Float>();
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
				    	
				    	if(lineToParse != null){
				    		Log.i("parse", lineToParse);
				    		StringTokenizer filecontents = new StringTokenizer(lineToParse, "||");
				  		  name =  (String) filecontents.nextElement();
				  		  xCoorStr =  (String) filecontents.nextElement();
				  		  yCoorStr =  (String) filecontents.nextElement();
				  		  
				  		  StringTokenizer X = new StringTokenizer(xCoorStr, ",");
				  		  while (X.hasMoreElements()) {
				  		  System.out.println(X.nextElement());
				  		xCoor.add( Float.parseFloat((String)  X.nextElement()));
				  		  }


				  		  StringTokenizer Y = new StringTokenizer(yCoorStr, ",");
				  		  while (Y.hasMoreElements()) {
				  		  System.out.println(Y.nextElement());
				  		yCoor.add( Float.parseFloat((String)  Y.nextElement()));
				  		  }
				    		//mapNameArray = PApplet.matchAll(lineToParse, "name:\\d+");
							tempMap.setName(name);
							Log.i("ModelReaderName",mapName);
							mapId = mapFile.getName().replace(".emp","");
							tempMap.setId(mapId);
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
