package com.envisprototype.view.model;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.envisprototype.R;
import com.envisprototype.model.sensor.SensorListModel;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class NewChartVizAdapter extends ArrayAdapter<ChartDataByTypes>{

	LinearLayout graph;
	Activity context;
	public NewChartVizAdapter(Context context, int resource,
			List<ChartDataByTypes> list) {
		super(context, resource, list);
		// TODO Auto-generated constructor stub
		this.context = (Activity)context;
	}

	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(inflatedView==null){
			LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflatedView=inflater.inflate(R.layout.rowadpterforchartviz, null);

		}
		graph = (LinearLayout)inflatedView.findViewById(R.id.graph2);

		if(graph.getChildCount()>0)
		{
			graph.removeAllViews();
		}
		//setplussensor = (TextView)inflatedView.findViewById(R.id.SetPlusSensor);
		//

		ChartDataByTypes cdbt = getItem(position);
		Log.i("yuyuyuyu1",cdbt.type+"");
		Log.i("yuyuyuyu2",cdbt.list.size()+"");
		//if(cdbt.list.size()>0)
		//{
			//graph = (LinearLayout)inflatedView.findViewById(R.id.graph2);
			View view = context.getWindow().getDecorView();
			view.setBackgroundColor(Color.BLACK);
			//LinearLayout layout;
			//	Log.i("ikhadfui.e", position+"");

			LineGraphView graphView = new LineGraphView(
					context
					, "TYPE : " + cdbt.type
					);

			GraphViewSeries seriesRnd=null;
			for(int i=0;i<cdbt.list.size();i++)
			{

				int temp = (int)(Math.random()*100);

				seriesRnd = new GraphViewSeries(SensorListModel.getSingletonInstance().findSensorById(cdbt.list.get(i)).getName(), new GraphViewSeriesStyle(Color.rgb(temp, 50, 100), 3),  cdbt.data[i]);
				graphView.addSeries(seriesRnd);
			}
			// set legend
			graphView.setShowLegend(true);
			graphView.setLegendAlign(LegendAlign.BOTTOM);
			graphView.setLegendWidth(200);
			// set view port, start=2, size=10
			graphView.setViewPort(2, 10);
			graphView.setScalable(true);
			//layout = (LinearLayout)inflatedVi findViewById(R.id.graph2);
			graph.addView(graphView);
		
		//}
		
	

		return inflatedView;
	}

}
