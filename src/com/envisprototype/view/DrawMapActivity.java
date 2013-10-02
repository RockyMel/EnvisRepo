package com.envisprototype.view;

import com.envisprototype.controller.DrawMapDoneOnClickListener;
import com.envisprototype.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class DrawMapActivity extends Activity {

	Button okBtn; // is clicked when the user if finished with map drawing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw_map);
		SeekBar heightBar = (SeekBar) findViewById(R.id.heightBar);
		TextView heightVauluelbl = (TextView) findViewById(R.id.height_value_listener);
		heightBar.setOnSeekBarChangeListener(new DrawMapDoneOnClickListener(
				heightVauluelbl));
		// okBtn = (Button) findViewById(R.id.doneMapDrawingBtn);
	}

}
