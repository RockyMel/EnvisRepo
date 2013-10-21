package com.envisprototype.view.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.envisprototype.R;
import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.view.RealTimeChartListActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

public class RealTimeChartVizAdapter extends ArrayAdapter<String>{


	LinearLayout graph;
	Activity context;

	private final Handler mHandler = new Handler();
	//private Runnable mTimer1;
	private Runnable mTimer2;
	private GraphView graphView;
	//private GraphViewSeries exampleSeries1;
	private GraphViewSeries exampleSeries2;
	private double graph2LastXValue = 0.0d;

	public RealTimeChartVizAdapter(Context context, int resource,
			List<String> objects) {
		super(context, resource, objects);
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
		//setplussensor = (TextView)inflatedView.findViewById(R.id.SetPlusSensor);
		//
		exampleSeries2 = new GraphViewSeries(new GraphViewData[] {
				new GraphViewData(1, 0.0d)

		});

		graphView = new LineGraphView(
				context
				, "GraphViewDemo"
				);
		((LineGraphView) graphView).setDrawBackground(true);

		graphView.addSeries(exampleSeries2); // data
		graphView.setViewPort(1, 8);
		graphView.setScalable(true);

		graph = (LinearLayout) inflatedView.findViewById(R.id.graph2);
		graph.addView(graphView);

		GetDataThread gt = new GetDataThread(graph2LastXValue,exampleSeries2,context);
		
		
		return inflatedView;
	}



}
