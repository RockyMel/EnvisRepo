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
	Coordinates mapCoors;
	String mapName, mapId, lineToParse;
	BufferedReader mapBReader;
	String name, xCoorStr, yCoorStr;
	ArrayList<Float> xCoor, yCoor;
	String line = null, sensorLine;
	Coordinates coors;
	
	public ModelReader(Context context){
		this.context = context;
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
							mapId = mapFile.getName().replace(".emp","");
							tempMap.setId(mapId);
				    	}
				    	
//				    }
				    mapBReader.close();
				    //mapCoors = prepareMapCoordinates(mapFile.getName());
				    mapCoors = new Coordinates();
				    mapCoors.setCoorX(xCoor);
				    mapCoors.setCoorY(yCoor);
					tempMap.setRealCoordinates(mapCoors);
					MapListModel.getSingletonInstance().addMap(tempMap);
				  }catch(IOException e){
					  lineToParse = null;
				    e.printStackTrace();
				  }
		  }
		//mapCoors = coorReader.prepareMapCoordinates("map.txt");
	}
//public Coordinates prepareMapCoordinates(String mapFileName){
//		
//		coors = new Coordinates();
//	  try{
//	    mapBReader = new BufferedReader(new FileReader(new 
//                File(context.getFilesDir()+File.separator+mapFileName)));
////	    while((line = mapBReader.readLine()) != null){
//	    	line = mapBReader.readLine();
//	    	if(line != null){
//	    		 PApplet.println(line);
//	    		 Log.i("quest real", "in real");
//	    		 Log.i("quest real", line);
//	    		 StringTokenizer filecontents = new StringTokenizer(line, "||");
//		  		  xCoorStr =  (String) filecontents.nextElement();
//		  		  yCoorStr =  (String) filecontents.nextElement();
//		  		  
//		  		  StringTokenizer X = new StringTokenizer(xCoorStr, ",");
//		  		  while (X.hasMoreElements()) {
//		  		  System.out.println(X.nextElement());
//		  		  xCoor.add( Float.parseFloat((String)  X.nextElement()));
//		  		  }
//
//
//		  		  StringTokenizer Y = new StringTokenizer(yCoorStr, ",");
//		  		  while (Y.hasMoreElements()) {
//		  		  System.out.println(Y.nextElement());
//		  		  yCoor.add( Float.parseFloat((String) Y.nextElement()));
//					  for(int i = 0; i < xCoor.size(); i++){
//						  coors.getCoorX().add(xCoor.get(i));
//						  coors.getCoorY().add(yCoor.get(i));
//					  }
//			      }
//	    	}
//	    	
////		    }
//		  mapBReader.close(); 
//	    
//	  }catch(IOException e){
//	    line = null;
//	    e.printStackTrace();
//	  }
//	  finally{
//		  return coors;
//	  }
//	}
}
