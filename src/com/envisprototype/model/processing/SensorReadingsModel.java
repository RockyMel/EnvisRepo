package com.envisprototype.model.processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import android.util.Log;

public class SensorReadingsModel {
	
	private static SensorReadingsModel singletonInstance;
	public static ArrayList<String> timeStamps = new ArrayList<String>();
	private HashMap<String, String> firstTimeStampsForSensors = new HashMap<String, String>();
	private int timeIndex = 0;
	
	HashMap<String, HashMap<String, Float>> sensorReadings = new HashMap<String, HashMap<String,Float>>();
	

	private SensorReadingsModel(){};

	public static SensorReadingsModel getSingletonInstance() {
		if(singletonInstance == null){
			singletonInstance = new SensorReadingsModel();
		}
		return singletonInstance;
	}
	
//	public void addNewSensorToReadingsModel(String sensorId, HashMap<String, Float> readingTimePair){
//		sensorReadings.put(sensorId, readingTimePair);
//		Iterator<String> iterator = readingTimePair.keySet().iterator();
//		while(iterator.hasNext()){
//			timeStamps.add(iterator.next());
//		}
//		//Collections.sort(timeStamps);
//	}
	
	public void addNewSensorToReadingsModel(String sensorId, String timeStamp, Float reading){
		HashMap<String, Float> tempMapOfTimeStamps = new HashMap<String, Float>();
		if(sensorReadings.get(sensorId) != null)
			tempMapOfTimeStamps = sensorReadings.get(sensorId);
		tempMapOfTimeStamps.put(timeStamp, reading);
		sensorReadings.put(sensorId, tempMapOfTimeStamps);
		timeStamps.add(timeStamp);
		//Collections.sort(timeStamps);
	}
	
	public  HashMap<String, Float> FindTimeReadingPairsForId(String sensorId){
		return sensorReadings.get(sensorId);
	}
	
	public Float FindReadingPairsForIdAndTime(String sensorId, String timeStamp){
		return (sensorReadings.get(sensorId)).get(timeStamp);
	}

	public ArrayList<String> getTimeStamps() {
		if(timeStamps == null){
			timeStamps = new ArrayList<String>();
		}
		return timeStamps;
	}

	public int getTimeIndex() {
		return timeIndex;
	}
	
	public void setTimeIndex(int timeIndex) {
		this.timeIndex = timeIndex;
	}
	

	public HashMap<String, HashMap<String, Float>> getSensorReadings() {
		return sensorReadings;
	}

	public void increaseIndex() {
		if(timeIndex < timeStamps.size()-1){
			timeIndex++;
		}
		else{
			timeIndex = timeStamps.size() - 1;
		}
		Log.i("index ",Integer.toString(timeIndex));
		Log.i("timeStamps.size() ",Integer.toString(timeStamps.size()));
	}
	public void decreaseIndex() {
		if(timeIndex > 0){
			timeIndex--;
		}
		else{
			timeIndex = 0;
		}
	}

	public  HashMap<String, String> getFirstTimeStampsForSensors() {
		return firstTimeStampsForSensors;
	}

	public  void setFirstTimeStampsForSensors(
			HashMap<String, String> firstTimeStampsForSensors) {
		this.firstTimeStampsForSensors = firstTimeStampsForSensors;
	}
	
	public String findFirstTimeStampForId(String sensorId){
		return firstTimeStampsForSensors.get(sensorId);
	}

}
