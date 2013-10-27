package com.envisprototype.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.envisprototype.R;
import com.envisprototype.view.model.ChartDataByTypes;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
import com.envisprototype.view.model.NewChartVizAdapter;

public class NewChartActivity extends Activity {

	public static ListView lv;
	public static NewChartVizAdapter ncva;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_chart);

		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.BLACK);
		

		List<ChartDataByTypes> temp = new ArrayList<ChartDataByTypes>();
		int index=0;
		for(int i=0;i<ChartDataByTypes.getSingletonInstance().size();i++)
		{
			System.out.println( i+ ": " + ChartDataByTypes.getSingletonInstance().get(i).type +"");
			if(ChartDataByTypes.getSingletonInstance().get(i).list.size()>0)
			{
			
				temp.add(index	, ChartDataByTypes.getSingletonInstance().get(i));index++;
			}

		}
		Log.i("sisisisize1", ChartDataByTypes.singletonInstance.size()+"");
		Log.i("sisisisize2",temp.size()+"");

		ncva = new NewChartVizAdapter(this,0,temp);
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(ncva);

		
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_chart, menu);
		return true;
	}

}
