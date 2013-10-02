package com.envisprototype.controller;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.R;
import com.envisprototype.view.DrawMapActivity;

public class onDrawMapClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.drawMapBtn) {
			Intent intent = new Intent(v.getContext(), DrawMapActivity.class);
			v.getContext().startActivity(intent);
		}
	}

}
