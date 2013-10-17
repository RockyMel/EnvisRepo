package com.envisprototype.controller.processing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;

import android.util.Log;

import com.envisprototype.view.processing.EnvisPApplet;
import com.envisprototype.view.processing.SensorSet;

public class CoordinateWriter {
	EnvisPApplet epApplet;
	
	public CoordinateWriter(EnvisPApplet epApplet){
		this.epApplet = epApplet;
	}
	
	public void saveMapToFile(String mapFileName){
		  try {
			  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new 
                      File(epApplet.getFilesDir()+File.separator+mapFileName)));
			  String stringToWrite = new String();
			  stringToWrite = epApplet.getEnvisMap().getRealCoors().getXCoorString();
			  stringToWrite+="||";
			  stringToWrite += epApplet.getEnvisMap().getRealCoors().getYCoorString();
			  Log.i("for db", "total line is  = " + stringToWrite);
			  bufferedWriter.write(stringToWrite);
			  bufferedWriter.flush();
			  bufferedWriter.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		  // testing
		  CoordinateFiller cf = new CoordinateFiller(epApplet);
		  cf.prepareMapCoordinates(mapFileName);
		}
	
	public void saveSetsToFile(String mapFileName, HashMap<String, SensorSet> sensorSets){
		try {
			  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new 
                    File(epApplet.getFilesDir()+File.separator+mapFileName)));
			  Iterator<String> iterator = sensorSets.keySet().iterator();
			  String stringToWrite = new String();
			  //stringToWrite = this.epApplet.getEnvisMap().getMapId() + "||";
			  while(iterator.hasNext()){
				  SensorSet setToWrite = sensorSets.get(iterator.next());
				  stringToWrite += setToWrite.getId();
				  stringToWrite += "||";
				  stringToWrite += Float.toString(setToWrite.getRealX());
				  stringToWrite += ",";
				  stringToWrite += Float.toString(setToWrite.getRealY());
				  stringToWrite += ",";
				  stringToWrite += Float.toString(setToWrite.getRealZ());
				  stringToWrite += "||";
				  bufferedWriter.write(stringToWrite);
				  }
			  bufferedWriter.flush();
			  bufferedWriter.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}
}
