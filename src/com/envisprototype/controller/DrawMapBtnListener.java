package com.envisprototype.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.view.processing.DrawMapApplet;

public class DrawMapBtnListener implements OnClickListener{
	public static int DRAW_REQUEST_CODE = 537;
	Activity context;
	
	public DrawMapBtnListener(Context context){
		this.context = (Activity) context;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, DrawMapApplet.class);
		context.startActivityForResult(intent, DRAW_REQUEST_CODE);
	}
	
	

}
