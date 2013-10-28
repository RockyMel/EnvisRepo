package com.envisprototype.model.processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import android.util.Log;

public class SensorReadingsModel {
	
	private static SensorReadingsModel singletonInstance;
	public ArrayList<String> timeStamps = new ArrayList<String>();
	private int timeIndex = 0;
	
	HashMap<String, TreeMap<String, Float>> sensorReadings = new HashMap<String, TreeMap<String,Float>>();
	
	private SensorReadingsModel(){};

	public static SensorReadingsModel getSingletonInstance() {
		if(singletonInstance == null){
			singletonInstance = new SensorReadingsModel();
		}
		return singletonInstance;
	}
	
	public void addNewSensorToReadingsModel(String sensorId, TreeMap<String, Float> readingTimePair){
		sensorReadings.put(sensorId, readingTimePair);
		Iterator<String> iterator = readingTimePair.keySet().iterator();
		while(iterator.hasNext()){
			timeStamps.add(iterator.next());
		}
		Collections.sort(timeStamps);
	}
	
	public  TreeMap<String, Float> FindTimeReadingPairsForId(String sensorId){
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

}
