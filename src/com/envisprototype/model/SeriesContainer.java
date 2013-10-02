package com.envisprototype.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYSeries;

/**
 * 
 * @ClassName: SeriesContainer
 * @Description: singleton object to manage chart content
 * @author Bo wubo.neusoft@gmail.com
 * @date 31/08/2013 11:10:15 AM
 * 
 */
public class SeriesContainer {
	private SeriesContainer() {
	}

	public static HashMap<XYSeries, LineAndPointFormatter> container;

	public static HashMap<XYSeries, LineAndPointFormatter> getContainer() {
		if (container == null) {
			container = new HashMap<XYSeries, LineAndPointFormatter>();
		}
		return container;
	}

	public static void setContainer() {

	}

}
