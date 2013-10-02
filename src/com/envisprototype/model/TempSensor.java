package com.envisprototype.model;

import java.util.Arrays;

import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;

/**
 * 
 * @ClassName: TempSensor
 * @Description: inherit class
 * @author Bo wubo.neusoft@gmail.com
 * @date 31/08/2013 11:11:10 AM
 * 
 */
public class TempSensor implements Sensor {

	@Override
	public XYSeries createXYChart(Number[] numbers, String name) {
		XYSeries series = new SimpleXYSeries(Arrays.asList(numbers), // SimpleXYSeries
																		// takes
																		// a
																		// List
																		// so
																		// turn
																		// our
																		// array
																		// into
																		// a
																		// List
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use
														// the element index as
														// the x value
				name);
		return series;
	}

}
