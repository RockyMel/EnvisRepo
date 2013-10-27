package com.envisprototype.model.processing;

import java.util.HashMap;
import java.util.TreeMap;

public class SensorReadingsModel {
	
	HashMap<String, TimeStampReading> sensorReadings;

	class TimeStampReading{
		TreeMap<String, Float> readingTimePair;
	}
}
