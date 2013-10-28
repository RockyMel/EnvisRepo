package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.controller.processing.dragListeners.AbstractDrawMapListener;
//import com.envisprototype.controller.processing.CoordinateWriter;
import com.envisprototype.model.processing.Coordinates;
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
						envisMap.addNewNode(width ,
								envisMap.getRealCoors().getCoorY().get(0));
						envisMap.addNewNode(width,
								height);
						envisMap.addNewNode(envisMap.getRealCoors().getCoorX().get(0),
								height);
						envisMap.setIfCentered(true);
//						CoordinateWriter output = new CoordinateWriter(drawMapApplet);
//						output.saveMapToFile("map.txt");
						Coordinates coorsToSave = drawMapApplet.getEnvisMap().getRealCoors();
						MapLocalDBHelper.getSingletonInstance(drawMapApplet).
						saveCoorsForMap(drawMapApplet.getEnvisMap().getMapId(), coorsToSave, drawMapApplet.getEnvisMap().getCOOR_Z());
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
