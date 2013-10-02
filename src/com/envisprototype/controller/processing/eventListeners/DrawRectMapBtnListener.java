package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;
import com.envisprototype.view.processing.DrawMapApplet;

public class DrawRectMapBtnListener extends AbstractDrawMapListener{
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton() && !eButton.getName().equals(drawMapApplet.getString(R.string.disabled))){
			drawMapApplet.setIfRectMap(true);
			drawMapApplet.setIfFreeshape(false);
			drawMapApplet.setIfFreePolygon(false);
			drawMapApplet.getFreeShapeBtn().setName(drawMapApplet.getString(R.string.disabled));
			drawMapApplet.getDrawPolygonBtn().setName(drawMapApplet.getString(R.string.disabled));
			drawMapApplet.getCloseFigure().setName(drawMapApplet.getString(R.string.disabled));
			drawMapApplet.getCloseFigure().setName(drawMapApplet.getString(R.string.disabled));
			drawMapApplet.getRemoveLastNodeBtn().setName(drawMapApplet.getString(R.string.disabled));
		}
	}
}
