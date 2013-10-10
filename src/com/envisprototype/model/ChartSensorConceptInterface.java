package com.envisprototype.model;

import com.androidplot.xy.XYSeries;

/**
 * 
 * @ClassName: Sensor
 * @Description: sensor chart generator
 * @author Bo wubo.neusoft@gmail.com
 * @date 31/08/2013 11:09:43 AM
 * 
 */
public interface ChartSensorConceptInterface {
	public XYSeries createXYChart(Number[] numbers, String name);

}
