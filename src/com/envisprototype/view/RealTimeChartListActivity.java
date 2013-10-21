package com.envisprototype.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.envisprototype.R;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
import com.envisprototype.view.model.RealTimeChartVizAdapter;

public class RealTimeChartListActivity extends Activity {

//	public static RealTimeChartVizAdapter cva;
//	public static ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_real_time_chart_list);


		setContentView(R.layout.activity_new_chart);

		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.BLACK);

//		cva = new RealTimeChartVizAdapter(this,0,ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs());
//		lv=(ListView)findViewById(R.id.listView1);
//		lv.setAdapter(cva);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.real_time_chart_list, menu);
		return true;
	}

	public static void updateadpt(){
		Log.i("CHanged", "changed called");
		//cva.notifyDataSetChanged();
	}
}
