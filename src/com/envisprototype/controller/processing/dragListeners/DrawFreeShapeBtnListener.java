package com.envisprototype.controller.processing.dragListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.view.processing.DrawMapApplet;

public class DrawFreeShapeBtnListener extends AbstractDrawMapListener{	

	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton() && !eButton.getText().equals(drawMapApplet.getString(R.string.disabled))){
			drawMapApplet.setIfRectMap(false);
			drawMapApplet.setIfFreePolygon(false);
			if(eButton.getText().equals(drawMapApplet.getString(R.string.draw_free_shape))){
				eButton.setText(drawMapApplet.getString(R.string.stop_draw_free_shape));
				drawMapApplet.getRectMapBtn().setText(drawMapApplet.getString(R.string.disabled));
				drawMapApplet.getDrawPolygonBtn().setText(drawMapApplet.getString(R.string.disabled));
				drawMapApplet.setIfFreeshape(true);
			}
			else{
				if(eButton.getText().equals(drawMapApplet.getString(R.string.stop_draw_free_shape))){
					eButton.setText(drawMapApplet.getString(R.string.draw_free_shape));
					drawMapApplet.getDrawPolygonBtn().setText(drawMapApplet.getString(R.string.draw_polygon));
					drawMapApplet.setIfFreeshape(false);
					}
				}
		}
	}
}