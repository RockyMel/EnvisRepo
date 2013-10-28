package com.envisprototype.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.envisprototype.R;
import com.envisprototype.controller.QRreaderButtonController;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
import com.envisprototype.zxing.integration.android.IntentIntegrator;
import com.envisprototype.zxing.integration.android.IntentResult;

public class TagViewActivity extends Activity {

	TextView ID;
	TextView SETORSENSOR;
	TextView NAME; 
	ImageButton QRBUTTON;
	ImageButton TwoDButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_view);
		init();
	}

	private void init(){
		ID = (TextView)findViewById(R.id.ID);
		SETORSENSOR = (TextView)findViewById(R.id.SETORSENSOR);
		NAME = (TextView)findViewById(R.id.NAME);
		QRBUTTON = (ImageButton)findViewById(R.id.QRBUTTON);
		QRBUTTON.setOnClickListener(new QRreaderButtonController(this));
		TwoDButton = (ImageButton)findViewById(R.id.TwoChartButton);
		TwoDButton.setVisibility(View.INVISIBLE);
		
	}

	@Override

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_view, menu);
		return true;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		//check we have a valid result
		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			if(scanContent!=null){
				ID.setText("ID :"+scanContent);
				SetInterface tempset = null;
				SensorInterface tempsensor = null;
				tempsensor = SensorListModel.getSingletonInstance().findSensorById(scanContent);
				if(tempsensor==null)
				{
					tempset = SetListModel.getSingletonInstance().findSetById(scanContent);
					
					if(tempset!=null)
					{
						NAME.setText("NAME: " + tempset.getName());
						SETORSENSOR.setText("YOU SCANNED A SET");
						TwoDButton.setVisibility(View.VISIBLE);
						//ArrayList<String> tempidlists = new ArrayList<String>();
						List<SensorInterface> temps = SensorListModel.getSingletonInstance().getSensorListBySetID(tempset.getId());
						Log.i("temps",temps.size()+"");
						for(int i=0;i<temps.size();i++)
							{
							ChartVisualizationSettingsModel.getSingletonInstance().addSensorID(temps.get(i).getId());
							}
						Log.i("CVSM", ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs().size()+"");
						
						TwoDButton.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent = new Intent (TagViewActivity.this,NewRealTimeChartActivity.class);
								v.getContext().startActivity(intent);
							}
						});
						
					}
					else
					{
						SETORSENSOR.setText("SORRY...WE COULD NOT FIND SCANNED ID IN OUR SYSTEM");

						NAME.setText("PLEASE TRY AGAIN!");
					}
				}
				else
				{
					NAME.setText("NAME: " + tempsensor.getName());
					SETORSENSOR.setText("YOU SCANNED A SENSOR");
					
					
				}


				//output to UI
				
			}
			else
			{
				Toast toast = Toast.makeText(this, 
						"Nothing Scanned..!!", Toast.LENGTH_SHORT);
				toast.show();

			}
		}
		else{
			//invalid scan data or scan canceled
			Toast toast = Toast.makeText(this, 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
