package com.envisprototype.controller.processing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

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
			  for(int j = 0; j < epApplet.getEnvisMap().getVisCoors().getCoorX().size(); j++){
				  stringToWrite +=epApplet.getEnvisMap().getRealCoors().getCoorX().get(j).toString() + ",";
				  }
			  stringToWrite+="||";
			  bufferedWriter.write(stringToWrite);
			  stringToWrite = new String();
			  for(int j = 0; j < epApplet.getEnvisMap().getVisCoors().getCoorX().size(); j++){
				  stringToWrite += epApplet.getEnvisMap().getRealCoors().getCoorY().get(j).toString() + ",";
				  }
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
	
	public void saveSetsToFile(String mapFileName, ArrayList<SensorSet> sensorSets){
		try {
			  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new 
                    File(epApplet.getFilesDir()+File.separator+mapFileName)));
			  String stringToWrite = new String();
			  for(int j = 0; j < sensorSets.size(); j++){
				  //stringToWrite = "id:" + sensorSets.get(j).getId().toString();
				  //bufferedWriter.write(stringToWrite);
				  //sensorSets.get(j).translateSensorsForFile(epApplet.getEnvisMap());
				  stringToWrite = "x:" + Float.toString(sensorSets.get(j).getRealX());
				  bufferedWriter.write(stringToWrite);
				  stringToWrite = "y:" + Float.toString(sensorSets.get(j).getRealY());
				  bufferedWriter.write(stringToWrite);
				  stringToWrite = "z:" + Float.toString(sensorSets.get(j).getRealZ()) + ";";
				  bufferedWriter.write(stringToWrite);
				  }
			  bufferedWriter.flush();
			  bufferedWriter.close();
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}
}
