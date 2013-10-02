package com.envisprototype.controller.processing.dragListeners;

import java.util.EventObject;

import com.envisprototype.controller.processing.eventListeners.AbstractDrawMapListener;
import com.envisprototype.view.processing.Map;

public class DrawingScopeListener extends AbstractDrawMapListener{

	Map envisMap;
	@Override
	public void handleEnvisDragEvent(EventObject e) {
		super.handleEnvisDragEvent(e);
		envisMap = drawMapApplet.getEnvisMap();
		// TODO Auto-generated method stub
		// do dragging
		if(ifHitTheButton()){
			if(drawMapApplet.isIfRectMap()){
				envisMap.drawRectWhileDragging();
			}
			else{
				if(drawMapApplet.isIfFreePolygon()){
					envisMap.drawLineWithDimsWhileDragging();
				}
			}
		}
	}
	

}
