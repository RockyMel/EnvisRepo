package com.envisprototype.model;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;

/**
 * 
 * @ClassName: LinePointFormat
 * @Description: inherit class
 * @author Bo wubo.neusoft@gmail.com
 * @date 31/08/2013 11:08:48 AM
 * 
 */
public class LinePointFormat implements FormatFactory {

	@Override
	public LineAndPointFormatter createFormat(List<Integer> parameter) {

		LineAndPointFormatter format = new LineAndPointFormatter(
				parameter.get(0), // line color
				parameter.get(1), // point color
				parameter.get(2), // fill color (none)
				new PointLabelFormatter(parameter.get(3)));

		/*
		 * Paint paint = new Paint(); LineAndPointFormatter format = new
		 * LineAndPointFormatter(); paint.setColor(parameter.get(0));
		 * format.setLinePaint(paint); paint.setColor(parameter.get(1));
		 * format.setVertexPaint(paint); paint.setColor(parameter.get(2));
		 * format.setFillPaint(paint); format.setPointLabelFormatter(new
		 * PointLabelFormatter(parameter.get(3)));
		 */
		return format;
	}

}
