package com.envisprototype.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.envisprototype.R;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
import com.envisprototype.view.model.ChartVizAdapter;

public class NewChartActivity extends Activity {
	//GraphViewData[] data = new GraphViewData[];
	public static ChartVizAdapter cva;
	public static ListView lv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_chart);

		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.BLACK);
		
		cva = new ChartVizAdapter(this,0,ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs());
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(cva);

		/*LinearLayout layout;
		for(int i=0;i< ChartData.getSingletonInstance().data.length;i++){
			GraphViewSeries seriesRnd = new GraphViewSeries("Random curve", null, ChartData.getSingletonInstance().data[i]);
			LineGraphView graphView = new LineGraphView(
					this
					, "GraphViewDemo"
					);

			graphView.addSeries(seriesRnd);
			// set legend
			graphView.setShowLegend(true);
			graphView.setLegendAlign(LegendAlign.BOTTOM);
			graphView.setLegendWidth(200);
			// set view port, start=2, size=10
			graphView.setViewPort(2, 10);
			graphView.setScalable(true);
			layout = (LinearLayout) findViewById(R.id.graph2);
			layout.addView(graphView);

		}*/
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_chart, menu);
		return true;
	}

}
