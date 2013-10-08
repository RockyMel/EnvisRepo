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

import processing.core.PApplet;

import com.envisprototype.model.processing.Coordinates;

import android.content.Context;

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
	    	if(line != null){
	    		 PApplet.println(line);
			      String[][] xValues = PApplet.matchAll(line, "x:\\d+");
			      String[][] yValues = PApplet.matchAll(line, "y:\\d+");    
			      for(int j = 0; j < xValues.length; j++){
			         String xString = PApplet.matchAll(xValues[j][0],"\\d+")[0][0];
			         String yString = PApplet.matchAll(yValues[j][0],"\\d+")[0][0];
			         coorX.add(Float.parseFloat(xString));
			         coorY.add(Float.parseFloat(yString));
					  for(int i = 0; i < coorX.size(); i++){
						  coors.getCoorX().add(coorX.get(i));
						  coors.getCoorY().add(coorY.get(i));
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
