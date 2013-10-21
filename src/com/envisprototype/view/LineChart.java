package com.envisprototype.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.androidplot.Plot;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.envisprototype.R;
import com.envisprototype.controller.DialogHandler;
import com.envisprototype.model.ChartSensorConcept;
import com.envisprototype.model.ChartSensorConceptInterface;
import com.envisprototype.model.FormatFactory;
import com.envisprototype.model.LinePointFormat;
import com.envisprototype.model.ParameterConstruct;
import com.envisprototype.model.SeriesContainer;
import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.view.model.ChartData;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
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

//	private class MyPlotUpdater implements Observer {
//		Plot plot;
//
//		public MyPlotUpdater(Plot plot) {
//			this.plot = plot;
//		}        
//
//		@Override
//		public void update(Observable o, Object arg) {
//			plot.redraw();
//		}
//	}
	public static XYPlot mySimpleXYPlot;
	private ImageButton imageBut;
	//private ArrayList<Boolean> chosenItems;
	private DialogHandler dHandler;
	private AlertDialog.Builder builder;
	private CharSequence[] items;
	private boolean[] checked;
	private HashMap<XYSeries, LineAndPointFormatter> tempMap;
	ChartData cdata;
	//Number[][] data = null;
	//private MyPlotUpdater potty;
	List<String> tempsensoridlist;
	String[] names = null;


	private class GetSensorReadingTask extends AsyncTask<String, Void, String> {
		//int index;

		GetSensorReadingTask(/*int index*/){
			//this.index=index;
		}
		@Override
		protected String doInBackground(String... args) {
			String response = "";

			for(int i=0;i<tempsensoridlist.size();i++)
				{
				if(i>0)
					response = response + "###" + SensorReadingDBHelper.getDataReadingSensorByHisTimeJSON(tempsensoridlist.get(i), "2013-10-18 18:10:50", "2013-10-18 18:15:50");
				else
					response = SensorReadingDBHelper.getDataReadingSensorByHisTimeJSON(tempsensoridlist.get(i), "2013-10-18 18:10:50", "2013-10-18 18:15:50");
				}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			// textView.setText(result);
			List<ParameterConstruct> pclist = new ArrayList<ParameterConstruct>();
			StringTokenizer strtok = new StringTokenizer(result,"###");
			int index = 0;
			while(strtok.hasMoreElements()){
				Log.i("asdasD", result);
				try {
					String temp = strtok.nextToken();
					System.out.println("---" + temp);
					JSONObject obj = new JSONObject(temp);
					//Iterator tempkeys = obj.keys();
					//while(tempkeys.hasNext()){
					//	Object element = tempkeys.next();
					//	Log.i("chkthis", element.toString());
					//}



					int i=1;
					while(true)
					{
						if(obj.getJSONArray(i+"")!=null){
							DialogHandler.data[index][i-1]=obj.getJSONArray(i+"").getInt(2);
							Log.i("chkthis",obj.getJSONArray(i+"").getString(2)+"");
						}
						else
							break;
						i++;
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				index++;
			}
			
			for(int i=0;i<DialogHandler.data.length;i++){
				for(int j=0;j<DialogHandler.data[i].length;j++)
					System.out.println(DialogHandler.data[i][j]);
				System.out.println("aaaaaaaa");
			}
//			cdata.setData(data);
//			cdata.notifyObservers();

			HashMap<XYSeries, LineAndPointFormatter> container;


			ChartSensorConceptInterface sensor = new ChartSensorConcept();
			FormatFactory format = new LinePointFormat();

			for(int i=0;i<=names.length-1;i++){

				List<Integer> parameter1 = new ArrayList<Integer>();

				parameter1.add(Color.RED);
				parameter1.add(null);
				parameter1.add(null);
				parameter1.add(Color.WHITE);

				ParameterConstruct temp = new ParameterConstruct(names[i],parameter1,DialogHandler.data[i]);
				pclist.add(temp);

			}


			mySimpleXYPlot.clear();

			XYSeries tempSeries = null;
			LineAndPointFormatter tempFormat = null;
			container = SeriesContainer.getContainer();
			container.clear();

			for (int i = 0; i < DialogHandler.chosenItems.size(); i++)
			{

				if(DialogHandler.chosenItems.get(i))
				{
					//Log.i("this2",chosenItems.get(i) +"" );
					tempSeries = sensor.createXYChart(pclist.get(i).getData(), pclist.get(i).getName());

					tempFormat = format.createFormat(pclist.get(i).getParameter1());
					container.put(tempSeries, tempFormat);
					mySimpleXYPlot.addSeries(tempSeries, tempFormat);
				}

			}


			mySimpleXYPlot.redraw();
			//mySimpleXYPlot.redraw();
			Log.i(":async", "done");

		}
	}


	public LineChart() {

		tempsensoridlist = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs();

		if(tempsensoridlist.size()>0){


			for(int i=0;i<tempsensoridlist.size();i++){
				SensorInterface tempsensor = SensorListModel.getSingletonInstance().findSensorById(tempsensoridlist.get(i));
				if(i==0)
				{
					items = new CharSequence[tempsensoridlist.size()];
					names = new String[tempsensoridlist.size()];
					checked = new boolean[tempsensoridlist.size()];
				}

				items[i]=tempsensor.getSetid()+"::"+tempsensor.getName();
				checked[i]=false;
				names[i]=tempsensor.getSetid()+"::"+tempsensor.getName();


			}
			int sizej=10;
			DialogHandler.data = new Number[tempsensoridlist.size()][sizej];
			Log.i("size", tempsensoridlist.size() + " " +DialogHandler.data.length+ " " + DialogHandler.data[0].length);


			//for(int i=0;i<tempsensoridlist.size();i++){
			GetSensorReadingTask task = new GetSensorReadingTask();
			task.execute("dummy");
			//data[i] = new Number[10];
			//				if(i==0){
			//				for(int j=0;j<10;j++){
			//
			//					data[i][j]=(j+1)*4;
			//				}}
			//				else
			//				{
			//					for(int j=0;j<10;j++){
			//
			//						data[i][j]=(j+2)*4;
			//					}
			//					
			//				}

			//	}

			this.dHandler = new DialogHandler();
			dHandler.init(tempsensoridlist.size());
			dHandler.setDataForVis(names);

			DialogHandler.chosenItems = this.dHandler.getChosenItems();
		}
		//checked = new boolean[] { false, false, false, false };
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//Log.i("pungi", "asjdh");

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
		for (int i = 0; i < DialogHandler.chosenItems.size(); i++) {

			checked[i] = (Boolean) DialogHandler.chosenItems.get(i);

		}
		builder.show();
	}

	@Override
	public void onClick(DialogInterface arg0, int pos, boolean flag) {
		Log.i("youcalled me",flag+"");
		DialogHandler.chosenItems.set(pos, flag);
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
		//cdata = new ChartData();
		//cdata.setData(data);

		mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

		//potty = new MyPlotUpdater(mySimpleXYPlot);
		//cdata.addObserver(potty);
		mySimpleXYPlot.setDomainBoundaries(0, 100, BoundaryMode.SHRINNK);
		//	mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 2);
		//	mySimpleXYPlot.setDomainValueFormat(new DecimalFormat("#"));
		// mySimpleXYPlot.setDomainStepValue(24);
		// mySimpleXYPlot.setTicksPerDomainLabel(1);
		// mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
		mySimpleXYPlot.setRangeBoundaries(0, 100, BoundaryMode.SHRINNK);
		//mySimpleXYPlot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 5);
		//	mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("#"));
		//
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
