package com.envisprototype.controller.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import processing.core.PApplet;

import android.util.Log;

import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.processing.EnvisPApplet;
import com.envisprototype.view.processing.SensorSet;



public class CoordinateFiller {
	EnvisPApplet epApplet;
	BufferedReader mapBReader, sensorsBReader;
	String line, sensorLine;
	
	public CoordinateFiller(EnvisPApplet epApplet){
		this.epApplet = epApplet;
	}
	
	public HashMap<String, SensorSet> prepareSensorsCoordinates(String sensorFileName){
		HashMap<String, SensorSet> envisSensors = new HashMap<String, SensorSet>();
		try {
			sensorsBReader = new BufferedReader(new FileReader(new 
			        File(epApplet.getFilesDir()+File.separator+sensorFileName)));
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
				      envisSensors.put(setid, new SensorSet(epApplet, setid,
			    			  Float.parseFloat(xCoor),
			    			  Float.parseFloat(yCoor),
			    			  Float.parseFloat(zCoor)));
				  }
			      

			  }
		}
		return envisSensors;
	}

	public void prepareMapCoordinates(String mapFileName){
		ArrayList<Float> coorX = new ArrayList<Float>();
		ArrayList<Float> coorY = new ArrayList<Float>();
	  try{
	    mapBReader = new BufferedReader(new FileReader(new 
                File(epApplet.getFilesDir()+File.separator+mapFileName)));
	    line = mapBReader.readLine();
	    Log.i("for db", "total line when read is  = " + line);
	    if(line != null){
			  PApplet.println(line);
			  String tempEl = new String();
			  Log.i("for db", "total line when read is  = " + line);
		    {
		    	StringTokenizer filecontents = new StringTokenizer(line, "||");
		    	String xCoor =  (String) filecontents.nextElement();
		    	String yCoor =  (String) filecontents.nextElement();

		    	StringTokenizer X = new StringTokenizer(xCoor, ",");
		    	System.out.println("scoor = " + xCoor);
		    	while (X.hasMoreElements()) {
		    		tempEl = (String) X.nextElement();
		    	System.out.println(tempEl);
		    	coorX.add(Float.parseFloat(tempEl));
		    	}


		    	StringTokenizer Y = new StringTokenizer(yCoor, ",");
		    	while (Y.hasMoreElements()) {
		    		tempEl = (String) Y.nextElement();
		    	System.out.println(tempEl);
		    	coorY.add(Float.parseFloat(tempEl));
		    	}
		    	
		    }
		  }
		  epApplet.getEnvisMap().setVisCoors(new Coordinates());
		  epApplet.getEnvisMap().setRealCoors(new Coordinates());
		  for(int i = 0; i < coorX.size(); i++){
			  epApplet.getEnvisMap().getVisCoors().getCoorX().add(coorX.get(i));
			  epApplet.getEnvisMap().getVisCoors().getCoorY().add(coorY.get(i));
			  epApplet.getEnvisMap().getRealCoors().getCoorX().add(coorX.get(i));
			  epApplet.getEnvisMap().getRealCoors().getCoorY().add(coorY.get(i));
		  }
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
	  }
	  
	  //epApplet.getEnvisMap().printCoors();
	}
}
