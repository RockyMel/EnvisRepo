package com.envisprototype.model;

import java.util.List;

import com.androidplot.xy.LineAndPointFormatter;

/**
 * 
 * @ClassName: FormatFactory
 * @Description: generate chart formatter interface
 * @author Bo wubo.neusoft@gmail.com
 * @date 31/08/2013 11:07:59 AM
 * 
 */
public interface FormatFactory {
	public LineAndPointFormatter createFormat(List<Integer> Parameter);

}
