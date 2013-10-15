package com.envisprototype.view.processing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.envisprototype.R;

public class MainAct extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_m);
	    Button visBtn = (Button) findViewById(R.id.map_by_name_btn);
	    visBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), ThreeDVis.class);
				v.getContext().startActivity(intent);
			}
		});
	    
	    Button twoDBtn = (Button) findViewById(R.id.nearest_maps_btn);
	    twoDBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), DrawMapApplet.class);
				v.getContext().startActivity(intent);
			}
		});
	    
	    Button setSet = (Button) findViewById(R.id.scan_qr_btn);
	    setSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), SetPlotPApplet.class);
				v.getContext().startActivity(intent);
			}
		});
	    
	    Button editMap = (Button) findViewById(R.id.button4);
	    editMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), EditMapApplet.class);
				v.getContext().startActivity(intent);
			}
		});
	    // TODO Auto-generated method stub
	}

}
