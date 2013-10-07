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
import java.util.StringTokenizer;

import processing.core.PApplet;

import com.envisprototype.model.processing.Coordinates;

import android.content.Context;

public class CoordinatesReader {
	BufferedReader mapBReader, sensorsBReader;
	String line = null, sensorLine;
	Context context;
	Coordinates coors;
	String name, xCoorStr, yCoorStr;
	ArrayList<Float> xCoor, yCoor;
	public CoordinatesReader(Context context){
		this.context = context;
		xCoor = new ArrayList<Float>();
		yCoor = new ArrayList<Float>();
	}
	
	public Coordinates prepareMapCoordinates(String mapFileName){
		
		coors = new Coordinates();
	  try{
	    mapBReader = new BufferedReader(new FileReader(new 
                File(context.getFilesDir()+File.separator+mapFileName)));
//	    while((line = mapBReader.readLine()) != null){
	    	line = mapBReader.readLine();
	    	if(line != null){
	    		 PApplet.println(line);
	    		 StringTokenizer filecontents = new StringTokenizer(line, "||");
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
		  		  yCoor.add( Float.parseFloat((String) Y.nextElement()));
					  for(int i = 0; i < xCoor.size(); i++){
						  coors.getCoorX().add(xCoor.get(i));
						  coors.getCoorY().add(yCoor.get(i));
					  }
			      }
	    	}
	    	
//		    }
		  mapBReader.close(); 
	    
	  }catch(IOException e){
	    line = null;
	    e.printStackTrace();
	  }
	  finally{
		  return coors;
	  }
	}
}
