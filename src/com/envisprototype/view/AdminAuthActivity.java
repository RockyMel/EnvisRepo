package com.envisprototype.view;





import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.envisprototype.R;

public class AdminAuthActivity extends EnvisActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linear_layout);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_linear_layout, menu);
	
	
	Button button1 = (Button)findViewById(R.id.button);
	button1.setOnClickListener(new View.OnClickListener(){
		public void onClick(View view) {

			//Navigate to Activity for Creation of Event
			Intent intent=new Intent(view.getContext(),AdminkaActivity.class);
			view.getContext().startActivity(intent);
			}
	});
	
	
	return true;

	}
	
	

}
