package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.envisprototype.zxing.integration.android.IntentIntegrator;
import com.envisprototype.zxing.integration.android.IntentResult;

public class QRreaderButtonController implements OnClickListener {

	Activity context;
	public QRreaderButtonController(Context context) {
		// TODO Auto-generated constructor stub
		this.context=(Activity)context;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		IntentIntegrator scanIntegrator = new IntentIntegrator(context);
		//start scanning
		scanIntegrator.initiateScan();
		
	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		//check we have a valid result
		if (scanningResult != null) {
			//get content from Intent Result
			String scanContent = scanningResult.getContents();
			//get format name of data scanned
			String scanFormat = scanningResult.getFormatName();
			//output to UI
			Toast toast = Toast.makeText(context, 
					scanContent, Toast.LENGTH_SHORT);
			toast.show();
		}
		else{
			//invalid scan data or scan canceled
			Toast toast = Toast.makeText(context, 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
