package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.view.processing.EnvisButton;
import com.envisprototype.view.processing.Map;

public class ZoomListener extends AbstractEnvisButtonListener{

	Map envisMap;
	float wait = 0;
	final float MIN_ZOOM = 0.5f;
	
	public ZoomListener(Map envisMap){
		this.envisMap = envisMap;
	}


	@Override
	public void handleEnvisClassEvent(EventObject e) {
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			if(wait < eButton.getEpApplet().mouseY){
				// zooming out
				Log.i("xy", "out");
				if(envisMap.getZoomValue() > MIN_ZOOM)
					envisMap.setZoomValue(envisMap.getZoomValue()-0.1f);
			}
			else{
				if(wait > eButton.getEpApplet().mouseY){
					Log.i("xy", "in");
					envisMap.setZoomValue(envisMap.getZoomValue()+0.1f);
				}
			}
			Log.i("xy","zoom value = " + envisMap.getZoomValue());
			envisMap.getEpApplet().scale(envisMap.getZoomValue());
			wait = eButton.getEpApplet().mouseY;
		}
	}
}
