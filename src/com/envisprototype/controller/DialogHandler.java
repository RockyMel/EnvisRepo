package com.envisprototype.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.envisprototype.model.ChartSensorConcept;
import com.envisprototype.model.ChartSensorConceptInterface;
import com.envisprototype.model.FormatFactory;
import com.envisprototype.model.LinePointFormat;
import com.envisprototype.model.ParameterConstruct;
import com.envisprototype.model.SeriesContainer;
import com.envisprototype.view.LineChart;

/**
 * 
 * @ClassName: DialogHandler
 * @Description: handle OK button request
 * @author Bo wubo.neusoft@gmail.com
 * @date 31/08/2013 11:11:40 AM
 * 
 */
public class DialogHandler implements OnClickListener {

	private ArrayList<Boolean> chosenItems;
	private HashMap<XYSeries, LineAndPointFormatter> container;
	private Number[][] data;
	private String[] names;

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

	public void setChosenItems(ArrayList chosenItems) {
		this.chosenItems = chosenItems;
	}

	public void setDataForVis(Number[][] data,String[] names){
		this.data = data;
		this.names = names;
		
	}
	
	List<ParameterConstruct> pclist = new ArrayList<ParameterConstruct>();
	
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		if (arg1 == arg0.BUTTON_POSITIVE) {
			boolean flag = false;
			XYPlot plot = LineChart.mySimpleXYPlot;
			ChartSensorConceptInterface sensor = new ChartSensorConcept();
			FormatFactory format = new LinePointFormat();

			for(int i=0;i<=names.length-1;i++){

				List<Integer> parameter1 = new ArrayList<Integer>();
				
				parameter1.add(Color.RED);
				parameter1.add(null);
				parameter1.add(null);
				parameter1.add(Color.WHITE);
				
				ParameterConstruct temp = new ParameterConstruct(names[i],parameter1,data[i]);
				pclist.add(temp);
				
			}
			

			plot.clear();

			XYSeries tempSeries = null;
			LineAndPointFormatter tempFormat = null;
			container = SeriesContainer.getContainer();
			container.clear();

			for (int i = 0; i < this.chosenItems.size(); i++)
			{
				if(chosenItems.get(i))
				{
					tempSeries = sensor.createXYChart(pclist.get(i).getNumber1(), pclist.get(i).getName());
					tempFormat = format.createFormat(pclist.get(i).getParameter1());
					container.put(tempSeries, tempFormat);
					plot.addSeries(tempSeries, tempFormat);
				}
			}
			
			
			plot.redraw();

		} else if (arg1 == arg0.BUTTON_NEGATIVE) {
			System.out.println("Cancel button has been clicked!!!");
		}

	}

}
