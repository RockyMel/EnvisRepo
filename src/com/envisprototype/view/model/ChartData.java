package com.envisprototype.view.model;

	
import com.jjoe64.graphview.GraphView.GraphViewData;


public class ChartData{

	public GraphViewData[][] data;
	//public ArrayList<>
	public static ChartData singletonInstance;
	
	
	public static ChartData getSingletonInstance() {
		if(singletonInstance==null)
			singletonInstance=new ChartData();

		return singletonInstance;
	}

	
	
}
