package com.envisprototype.view;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.envisprototype.R;
import com.envisprototype.controller.QRreaderButtonController;
import com.envisprototype.controller.IdEnteredOKButtonController;
import com.envisprototype.zxing.integration.android.IntentIntegrator;
import com.envisprototype.zxing.integration.android.IntentResult;

public class InputIDActivity extends EnvisActivity {

	
	EditText id;
	ImageButton QRbutton;
	Button ok;
	String mode;
	TextView ModeId;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_sensor_id);
		mode = getIntent().getStringExtra("mode");
		ModeId=(TextView)findViewById(R.id.Mode);
		if(mode.equals("set"))
		{
			ModeId.setText("Set ID");
			
			
		}
		else
		{
			ModeId.setText("Sensor ID");

		}
		
		init();
	}

	
	private void init() {
		// TODO Auto-generated method stub
		id=(EditText)findViewById(R.id.editText1);
		QRbutton = (ImageButton)findViewById(R.id.imageButton1);
		ok=(Button)findViewById(R.id.map_by_name_btn);
		
		QRbutton.setOnClickListener(new QRreaderButtonController(this));
		
		if(mode.equals("sensor"))
		{
			
			String setid = getIntent().getStringExtra("setid"); 
			Log.i("##########SETID", setid);
			ok.setOnClickListener(new IdEnteredOKButtonController(mode,id,setid,this));
			
		}
		else
		{
			ok.setOnClickListener(new IdEnteredOKButtonController(mode,id,this));

		}
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_sensor_id, menu);
		return true;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		//check we have a valid result
		if (scanningResult != null) {
			//get content from Intent Result
			String scanContent = scanningResult.getContents();
			//get format name of data scanned
			if(scanContent!=null){
			StringTokenizer st = new StringTokenizer(scanContent,";");
			 

			id.setText(st.nextElement().toString());
			//name.setText(st.nextElement().toString());
	 
			//output to UI
			Toast toast = Toast.makeText(this, 
					scanContent, Toast.LENGTH_SHORT);
			toast.show();
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
