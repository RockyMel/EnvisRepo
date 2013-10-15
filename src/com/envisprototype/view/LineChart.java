package com.envisprototype.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import com.envisprototype.R;
import com.envisprototype.controller.DialogHandler;
import com.envisprototype.model.SeriesContainer;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.view.navigation.NavigationMaker;

/**
 * 
 * @ClassName: LineChart
 * @Description: main activity to handle display
 * @author Bo wubo.neusoft@gmail.com
 * @date 31/08/2013 11:04:21 AM
 * 
 */
public class LineChart extends EnvisActivity implements OnClickListener,
OnMultiChoiceClickListener {

	public static XYPlot mySimpleXYPlot;
	private ImageButton imageBut;
	private ArrayList<Boolean> chosenItems;
	private DialogHandler dHandler;
	private AlertDialog.Builder builder;
	private CharSequence[] items;
	private boolean[] checked;
	private HashMap<XYSeries, LineAndPointFormatter> tempMap;

	public LineChart() {

		List<SensorInterface> tempsensorlist = SensorListModel.getSingletonInstance().getSensorList();

		String[] names = null;
		Number[][] data = null;
		if(tempsensorlist.size()>0){


			for(int i=0;i<tempsensorlist.size();i++){
				if(i==0)
				{
					items = new CharSequence[tempsensorlist.size()];
					names = new String[tempsensorlist.size()];
					checked = new boolean[tempsensorlist.size()];
				}

				items[i]=tempsensorlist.get(i).getSetid()+"::"+tempsensorlist.get(i).getName();
				checked[i]=false;
				names[i]=tempsensorlist.get(i).getSetid()+"::"+tempsensorlist.get(i).getName();


			}
			int sizej=10;
			data = new Number[tempsensorlist.size()][sizej];
			Log.i("size", tempsensorlist.size() + " " +data.length+ " " + data[0].length);
			for(int i=0;i<tempsensorlist.size();i++){
				//data[i] = new Number[10];

				for(int j=0;j<10;j++){

					data[i][j]=(j+1)*4;
				}

			}

			this.dHandler = new DialogHandler();
			dHandler.init(tempsensorlist.size());
			dHandler.setDataForVis(data, names);

			this.chosenItems = this.dHandler.getChosenItems();
		}
		//checked = new boolean[] { false, false, false, false };
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.line_chart);
		NavigationMaker.makeDropDownMenu(this, R.array.twod_drop_down);

		this.initalChart();
		this.createDialog();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.line_chart, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {

		checked = new boolean[] { false, false, false, false };
		for (int i = 0; i < this.chosenItems.size(); i++) {

			checked[i] = (Boolean) chosenItems.get(i);

		}
		builder.show();
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
		chosenItems.set(arg1, arg2);
	}

	/**
	 * 
	 * @Title: createDialog
	 * @Description: create popup dialog window
	 * @param void
	 * @return void
	 * @throws
	 */
	private void createDialog() {

		imageBut = (ImageButton) findViewById(R.id.imageButton1);
		imageBut.setOnClickListener(this);

		//		tempMap = SeriesContainer.getContainer();
		//		if (!tempMap.isEmpty()) {
		//			Iterator iter = tempMap.keySet().iterator();
		//			String name = "";
		//			// this.chosenItems.clear();
		////			while (iter.hasNext()) {
		////				name = ((XYSeries) iter.next()).getTitle();
		////				if (name.equals("Sensor1")) {
		////					this.chosenItems.remove(0);
		////					this.chosenItems.add(0, true);
		////					this.checked[0] = true;
		////				} else if (name.equals("Sensor2")) {
		////					this.chosenItems.remove(1);
		////					this.chosenItems.add(1, true);
		////					this.checked[1] = true;
		////				} else if (name.equals("Sensor3")) {
		////					this.chosenItems.remove(2);
		////					this.chosenItems.add(2, true);
		////					this.checked[2] = true;
		////				} else if (name.equals("Sensor4")) {
		////					this.chosenItems.remove(3);
		////					this.chosenItems.add(3, true);
		////					this.checked[3] = true;
		////				}
		////			}
		//		}

		builder = new AlertDialog.Builder(this);
		builder.setTitle("MultiChoose");
		builder.setPositiveButton("OK", dHandler);
		// builder.setNegativeButton("Cancel", dHandler);
		builder.setMultiChoiceItems(items, checked, this);

	}

	/**
	 * 
	 * @Title: initalChart
	 * @Description: initialize chart object
	 * @param void
	 * @return void
	 * @throws
	 */
	private void initalChart() {
		mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
		mySimpleXYPlot.setDomainBoundaries(0, 24, BoundaryMode.FIXED);
		mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 2);
		mySimpleXYPlot.setDomainValueFormat(new DecimalFormat("#"));
		// mySimpleXYPlot.setDomainStepValue(24);
		// mySimpleXYPlot.setTicksPerDomainLabel(1);
		// mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
		mySimpleXYPlot.setRangeBoundaries(0, 50, BoundaryMode.FIXED);
		mySimpleXYPlot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 5);
		mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("#"));

		tempMap = SeriesContainer.getContainer();
		if (!tempMap.isEmpty()) {
			XYSeries key;
			LineAndPointFormatter value;
			Iterator iter = tempMap.keySet().iterator();
			while (iter.hasNext()) {
				key = (XYSeries) iter.next();
				value = tempMap.get(key);
				this.mySimpleXYPlot.addSeries(key, value);
			}
		}

	}

}
