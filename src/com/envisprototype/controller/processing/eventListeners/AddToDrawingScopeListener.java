package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.controller.processing.CoordinateWriter;
import com.envisprototype.view.processing.Map;

public class AddToDrawingScopeListener extends AbstractDrawMapListener{
	int rectStep = 0;
	Map envisMap;
	
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		envisMap = drawMapApplet.getEnvisMap();
		if(ifHitTheButton() && !envisMap.isIfCentered()){
			if(drawMapApplet.isIfFreePolygon() || drawMapApplet.isIfFreeshape()){
				// add free shape
				envisMap.addNewNode(drawMapApplet.mouseX, drawMapApplet.mouseY);
			}
			else
				if(drawMapApplet.isIfRectMap()){
					// add rectangular
					switch(rectStep){
					case 0:{
						// adding the first point
						envisMap.addNewNode(drawMapApplet.mouseX, drawMapApplet.mouseY);
						break;
					}
					case 1:{
						// adding the last three points
						float width = drawMapApplet.mouseX;
						float height = drawMapApplet.mouseY;
						envisMap.addNewNode(envisMap.getRealCoors().getCoorX().get(0)+width/2,
								envisMap.getRealCoors().getCoorY().get(0));
						envisMap.addNewNode(envisMap.getRealCoors().getCoorX().get(0)+width/2,
								envisMap.getRealCoors().getCoorY().get(0)+height/2);
						envisMap.addNewNode(envisMap.getRealCoors().getCoorX().get(0),
								envisMap.getRealCoors().getCoorY().get(0)+height/2);
						envisMap.setIfCentered(true);
						CoordinateWriter output = new CoordinateWriter(drawMapApplet);
						
						output.saveMapToFile("map.txt");
						eButton.getEpApplet().getEnvisMap().translateToMiddle();
						Log.i("edit","saved");
						break;
					}
					}
					rectStep++;
				}
		}
	}
}
