package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;
import com.envisprototype.controller.processing.dragListeners.AbstractDrawMapListener;
import com.envisprototype.view.processing.DrawMapApplet;

public class DrawRectMapBtnListener extends AbstractDrawMapListener{
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton() && !eButton.getText().equals(drawMapApplet.getString(R.string.disabled))){
			drawMapApplet.setIfRectMap(true);
			drawMapApplet.setIfFreeshape(false);
			drawMapApplet.setIfFreePolygon(false);
			drawMapApplet.getFreeShapeBtn().setText(drawMapApplet.getString(R.string.disabled));
			drawMapApplet.getDrawPolygonBtn().setText(drawMapApplet.getString(R.string.disabled));
			drawMapApplet.getCloseFigure().setText(drawMapApplet.getString(R.string.disabled));
			drawMapApplet.getCloseFigure().setText(drawMapApplet.getString(R.string.disabled));
			drawMapApplet.getRemoveLastNodeBtn().setText(drawMapApplet.getString(R.string.disabled));
		}
	}
}
