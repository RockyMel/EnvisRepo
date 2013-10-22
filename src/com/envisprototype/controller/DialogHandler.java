package com.envisprototype.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.util.Log;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.envisprototype.model.ChartSensorConcept;
import com.envisprototype.model.ChartSensorConceptInterface;
import com.envisprototype.model.FormatFactory;
import com.envisprototype.model.LinePointFormat;
import com.envisprototype.model.ParameterConstruct;
import com.envisprototype.model.SeriesContainer;

/**
 * 
 * @ClassName: DialogHandler
 * @Description: handle OK button request
 * @author Bo wubo.neusoft@gmail.com
 * @date 31/08/2013 11:11:40 AM
 * 
 */
public class DialogHandler implements OnClickListener {

	public static ArrayList<Boolean> chosenItems;
	public static HashMap<XYSeries, LineAndPointFormatter> container;
	public static Number[][] data;
	public static String[] names;

	public DialogHandler() {

		
	}

	public void init(int size){
		this.chosenItems = new ArrayList<Boolean>();
		for(int i=0;i<size;i++)
		{
			this.chosenItems.add(i, false);

		}
	}
	
	public ArrayList getChosenItems() {
		return chosenItems;
	}

	

	public void setDataForVis(String[] names){
		this.data = data;
		this.names = names;
		
	}
	
	List<ParameterConstruct> pclist = new ArrayList<ParameterConstruct>();
	
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
//	//	if (arg1 == arg0.BUTTON_POSITIVE) {
//			//boolean flag = false;
//		XYPlot plot = LineChart.mySimpleXYPlot;
//		ChartSensorConceptInterface sensor = new ChartSensorConcept();
//		FormatFactory format = new LinePointFormat();
//		if(chosenItems.size()==0){
//			plot.clear();
//		}
//		else{
//			
//
//			for(int i=0;i<=names.length-1;i++){
//
//				List<Integer> parameter1 = new ArrayList<Integer>();
//				
//				parameter1.add(Color.RED);
//				parameter1.add(null);
//				parameter1.add(null);
//				parameter1.add(Color.WHITE);
//				Log.i("yeh dekh abhi",data[i].toString());
//				ParameterConstruct temp = new ParameterConstruct(names[i],parameter1,data[i]);
//				pclist.add(temp);
//				
//			}
//			Log.i("yahaan tak", "ji haan 1");
//
//			plot.clear();
//
//			XYSeries tempSeries = null;
//			LineAndPointFormatter tempFormat = null;
//			container = SeriesContainer.getContainer();
//			container.clear();
//			Log.i("yahaan tak", "ji haan 2");
//			Log.i("SIZEOFCHOSEN", chosenItems.size()+"");
//			for (int i = 0; i < chosenItems.size(); i++)
//			{
//				if(chosenItems.get(i))
//				{
//					Log.i("thisisit", pclist.get(i).getData()+"");
//					tempSeries = sensor.createXYChart(pclist.get(i).getData(), pclist.get(i).getName());
//					tempFormat = format.createFormat(pclist.get(i).getParameter1());
//					container.put(tempSeries, tempFormat);
//					plot.addSeries(tempSeries, tempFormat);
//				}
//			}
//			Log.i("yahaan tak", "ji haan 3");
//			
//			plot.redraw();
//			Log.i("yahaan tak", "ji haan 4");
//		}
////		} else if (arg1 == arg0.BUTTON_NEGATIVE) {
////			System.out.println("Cancel button has been clicked!!!");
////		}

	}

}
