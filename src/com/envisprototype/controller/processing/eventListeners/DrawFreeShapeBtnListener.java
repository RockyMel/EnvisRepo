package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.view.processing.DrawMapApplet;

public class DrawFreeShapeBtnListener extends AbstractDrawMapListener{	

	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton() && !eButton.getName().equals(drawMapApplet.getString(R.string.disabled))){
			drawMapApplet.setIfRectMap(false);
			drawMapApplet.setIfFreePolygon(false);
			if(eButton.getName().equals(drawMapApplet.getString(R.string.draw_free_shape))){
				eButton.setName(drawMapApplet.getString(R.string.stop_draw_free_shape));
				drawMapApplet.getRectMapBtn().setName(drawMapApplet.getString(R.string.disabled));
				drawMapApplet.getDrawPolygonBtn().setName(drawMapApplet.getString(R.string.disabled));
				drawMapApplet.setIfFreeshape(true);
			}
			else{
				if(eButton.getName().equals(drawMapApplet.getString(R.string.stop_draw_free_shape))){
					eButton.setName(drawMapApplet.getString(R.string.draw_free_shape));
					drawMapApplet.getDrawPolygonBtn().setName(drawMapApplet.getString(R.string.draw_polygon));
					drawMapApplet.setIfFreeshape(false);
					}
				}
		}
	}
}