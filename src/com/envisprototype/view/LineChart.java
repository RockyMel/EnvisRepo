package com.envisprototype.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.AsyncTask;
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
import com.envisprototype.model.DBHelper.SensorReadingDBHelper;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
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

	public static XYPlot mySimpleXYPlot;
	private ImageButton imageBut;
	private ArrayList<Boolean> chosenItems;
	private DialogHandler dHandler;
	private AlertDialog.Builder builder;
	private CharSequence[] items;
	private boolean[] checked;
	private HashMap<XYSeries, LineAndPointFormatter> tempMap;
	Number[][] data = null;
	
	private class GetSensorReadingTask extends AsyncTask<String, Void, String> {
		int index;
		GetSensorReadingTask(int index){
			this.index=index;
		}
	    @Override
	    protected String doInBackground(String... args) {
	      String response = "";
	      response = SensorReadingDBHelper.getDataReadingSensorByHisTimeJSON("S1ZZZ", "2013-10-18 18:10:50", "2013-10-18 18:15:50");
	      return response;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	     // textView.setText(result);
	    	Log.i("asdasD", result);
	    	try {
				JSONObject obj = new JSONObject(result);
				Iterator tempkeys = obj.keys();
				//while(tempkeys.hasNext()){
				//	Object element = tempkeys.next();
				//	Log.i("chkthis", element.toString());
				//}
				int i=1;
				while(true)
				{
					if(obj.getJSONArray(i+"")!=null){
						data[index][i-1]=obj.getJSONArray(i+"").getInt(2);
						Log.i("chkthis",obj.getJSONArray(i+"").getString(2)+"");
						}
					else
						break;
					i++;
				}
//				Log.i("chkthis",obj.getJSONArray("1")+"");
//				Log.i("chkthis",obj.getJSONArray("2")+"");
//				Log.i("chkthis",obj.getJSONArray("3")+"");
//				Log.i("chkthis",obj.getJSONArray("4")+"");
//				Log.i("chkthis",obj.getJSONArray("5")+"");
//				Log.i("chkthis",obj.getJSONArray("6")+"");
//				Log.i("chkthis",obj.getJSONArray("7")+"");
//				Log.i("chkthis",obj.getJSONArray("8")+"");
//				Log.i("chkthis",obj.getJSONArray("9")+"");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	Log.i(":async", "done");
	    }
	  }
	
	
	public LineChart() {

		List<String> tempsensoridlist = ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs();

		String[] names = null;
		
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
			data = new Number[tempsensoridlist.size()][sizej];
			Log.i("size", tempsensoridlist.size() + " " +data.length+ " " + data[0].length);
		  
		    
			for(int i=0;i<tempsensoridlist.size();i++){
				  GetSensorReadingTask task = new GetSensorReadingTask(i);
				    task.execute(new String[]{"dummy"});
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

			}

			this.dHandler = new DialogHandler();
			dHandler.init(tempsensoridlist.size());
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
