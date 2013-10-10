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
			
		/*	Number[] number1 = { 5, 15, 23, 36, 23, 17 };
			List<Integer> parameter1 = new ArrayList<Integer>();
			parameter1.add(Color.RED);
			parameter1.add(null);
			parameter1.add(null);
			parameter1.add(Color.WHITE);
			Number[] number2 = { 14, 26, 30, 18, 20, 10 };
			List<Integer> parameter2 = new ArrayList<Integer>();
			parameter2.add(Color.YELLOW);
			parameter2.add(null);
			parameter2.add(null);
			parameter2.add(Color.WHITE);
			Number[] number3 = { 26, 14, 37, 33, 48, 26 };
			List<Integer> parameter3 = new ArrayList<Integer>();
			parameter3.add(Color.BLUE);
			parameter3.add(null);
			parameter3.add(null);
			parameter3.add(Color.WHITE);
			Number[] number4 = { 29, 33, 11, 25, 36, 43 };
			List<Integer> parameter4 = new ArrayList<Integer>();
			parameter4.add(Color.GREEN);
			parameter4.add(null);
			parameter4.add(null);
			parameter4.add(Color.WHITE);
*/
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
			/*for (int i = 0; i < this.chosenItems.size(); i++) {
				if (chosenItems.get(i)) {
					switch (i) {
					case 0:
						tempSeries = sensor.createXYChart(number1, "Sensor1");
						tempFormat = format.createFormat(parameter1);
						container.put(tempSeries, tempFormat);
						plot.addSeries(tempSeries, tempFormat);
						break;
					case 1:
						tempSeries = sensor.createXYChart(number2, "Sensor2");
						tempFormat = format.createFormat(parameter2);
						container.put(tempSeries, tempFormat);
						plot.addSeries(tempSeries, tempFormat);
						break;
					case 2:
						tempSeries = sensor.createXYChart(number3, "Sensor3");
						tempFormat = format.createFormat(parameter3);
						container.put(tempSeries, tempFormat);
						plot.addSeries(tempSeries, tempFormat);
						break;
					case 3:
						tempSeries = sensor.createXYChart(number4, "Sensor4");
						tempFormat = format.createFormat(parameter4);
						container.put(tempSeries, tempFormat);
						plot.addSeries(tempSeries, tempFormat);
						break;
					default:
						break;
					}
				} else {

				}
			}*/
			
			plot.redraw();

		} else if (arg1 == arg0.BUTTON_NEGATIVE) {
			System.out.println("Cancel button has been clicked!!!");
		}

	}

}
