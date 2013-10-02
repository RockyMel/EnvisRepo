package com.envisprototype.controller;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class DrawMapDoneOnClickListener implements OnSeekBarChangeListener {

	TextView heightVauluelbl;
	SeekBar heightBar;

	public DrawMapDoneOnClickListener(TextView _heightVauluelbl) {
		this.heightVauluelbl = _heightVauluelbl;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		progress += 100;
		this.heightVauluelbl.setText(Integer.toString(progress));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

}
