/*
 * TODO: integrate this and CoordinateFiller classes as the code is repetitive
 *  also do the same for the CoordinateWriter class
 */
package com.envisprototype.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import processing.core.PApplet;
import android.content.Context;
import android.util.Log;

import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.model.processing.SetCoordinates;
import com.envisprototype.view.processing.SensorSet;

public class CoordinatesReader {
	BufferedReader mapBReader, sensorsBReader;
	String line = null, sensorLine;
	Context context;
	Coordinates coors;
	public CoordinatesReader(Context context){
		this.context = context;
	}
	
	public Coordinates prepareMapCoordinates(String mapFileName){
		ArrayList<Float> coorX = new ArrayList<Float>();
		ArrayList<Float> coorY = new ArrayList<Float>();
		coors = new Coordinates();
	  try{
	    mapBReader = new BufferedReader(new FileReader(new 
                File(context.getFilesDir()+File.separator+mapFileName)));
//	    while((line = mapBReader.readLine()) != null){
	    
	    	line = mapBReader.readLine();
	    	Log.i("for db", "total line when read is  = " + line);
	    	if(line != null){
	    		 PApplet.println(line);
	    		 Log.i("for db", "total line is  = " + line);
	    		 StringTokenizer filecontents = new StringTokenizer(line, "||");
			    	String xCoor =  (String) filecontents.nextElement();
			    	String yCoor =  (String) filecontents.nextElement();

			    	StringTokenizer X = new StringTokenizer(xCoor, ",");
			    	Float tempFloat;
			    	while (X.hasMoreElements()) {
			    		tempFloat = Float.parseFloat(X.nextToken());
			    		coorX.add(tempFloat);
			    	}
			    	StringTokenizer Y = new StringTokenizer(yCoor, ",");
			    	while (Y.hasMoreElements()) {
			    		tempFloat = Float.parseFloat(Y.nextToken());
			    		coorY.add(tempFloat);
			    	}
			    	Log.i("for db", "coorX size = " + coorX.size());
			    	for(int i = 0; i < coorX.size(); i++){
						  Log.i("for db", "x = " + Float.toString(coorX.get(i)));
						  Log.i("for db", "y = " + Float.toString(coorY.get(i)));
						  coors.getCoorX().add(coorX.get(i));
						  coors.getCoorY().add(coorY.get(i));
					  }
	    	}
	    	
//		    }
		  
	    
	  }catch(IOException e){
	    line = null;
	    e.printStackTrace();
	  }
	  finally{
		  try {
			mapBReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  return coors;
	  }
	}
	
	public HashMap<String, SetCoordinates> prepareSensorsCoordinates(String sensorFileName){
		HashMap<String, SetCoordinates> envisSensors = new HashMap<String, SetCoordinates>();
		try {
			sensorsBReader = new BufferedReader(new FileReader(new 
			        File(context.getFilesDir()+File.separator+sensorFileName)));
			sensorLine = sensorsBReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			sensorLine = null;
			e.printStackTrace();
		}
		if(sensorLine != null){
			// parsing sensors
			  if(sensorLine != null){
				  PApplet. println(line);
				  StringTokenizer filecontents = new StringTokenizer(line, "||");
				  while(filecontents.hasMoreElements()){
					  String setid =  (String) filecontents.nextElement();
					  String xyzcoors =  (String) filecontents.nextElement();
					  StringTokenizer xyzTokenizer = new StringTokenizer(xyzcoors, ",");
					  String xCoor = (String)xyzTokenizer.nextElement();
					  String yCoor = (String)xyzTokenizer.nextElement();
					  String zCoor = (String)xyzTokenizer.nextElement();
				      envisSensors.put(setid, new SetCoordinates(
				    		  Float.parseFloat(xCoor),
			    			  Float.parseFloat(yCoor),
			    			  Float.parseFloat(zCoor)));
				  }
			      

			  }
		}
		return envisSensors;
	}
}
