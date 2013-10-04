package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.envisprototype.R;

public class ExitFromDrawMapAppletListener extends AbstractDrawMapListener{
	
	@Override	
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton() && !eButton.getName().equals(drawMapApplet.getString(R.string.disabled))){
			if(drawMapApplet.getEnvisMap().isIfCentered()){
				Intent intentForAddMap = new Intent();
				intentForAddMap.putExtra("map", drawMapApplet.getEnvisMap().getRealCoors());
				drawMapApplet.setResult(Activity.RESULT_OK,intentForAddMap);     
				drawMapApplet.finish();
				drawMapApplet.destroy();
				//return;
			}
		}
	}
}
