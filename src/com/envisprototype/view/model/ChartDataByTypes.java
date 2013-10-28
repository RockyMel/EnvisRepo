package com.envisprototype.view.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.jjoe64.graphview.GraphView.GraphViewData;

public class ChartDataByTypes {

	public int type;
	public ArrayList<String> list;
	public GraphViewData[][] data;
	
	public static List<ChartDataByTypes> singletonInstance;

	public ChartDataByTypes(){
		list = new ArrayList<String>();
	}

	public ChartDataByTypes(int i, ArrayList<String> sensorIDListByType) {
		// TODO Auto-generated constructor stub
		this.type = i;
		this.list = sensorIDListByType;
		
	}
	public static List<ChartDataByTypes> getSingletonInstance() {
		if(singletonInstance==null)
		{
			singletonInstance=new ArrayList<ChartDataByTypes>();
			singletonInstance.add(new ChartDataByTypes());
			singletonInstance.add(new ChartDataByTypes());
			singletonInstance.add(new ChartDataByTypes());
			singletonInstance.add(new ChartDataByTypes());
			singletonInstance.add(new ChartDataByTypes());
			singletonInstance.add(new ChartDataByTypes());
			singletonInstance.add(new ChartDataByTypes());
			
			singletonInstance.get(0).type=1;
			singletonInstance.get(1).type=2;
			singletonInstance.get(2).type=3;
			singletonInstance.get(3).type=4;
			singletonInstance.get(4).type=5;
			singletonInstance.get(5).type=6;
			singletonInstance.get(6).type=7;
			
		}

		return singletonInstance;
	}
	
	public static void addIDForType(String ID,int type){
		//Log.i("asd", type+" "+ID);
		//Log.i("asd", type+" "+ID);
		
		singletonInstance.get(type-1).list.add(ID);
	}
	public static void addDataForType(GraphViewData[] data,int type,int pos){
		///singletonInstance.get(type-1).data[pos]= new GraphViewData();
		Log.i("position", pos+ " " + type + " " + data + " " + singletonInstance.get(type-1));
		singletonInstance.get(type-1).data[pos]=data;
	}
	public static GraphViewData[] getDataForType(int type,int pos){
		return singletonInstance.get(type-1).data[pos];
	}
	
	
}
