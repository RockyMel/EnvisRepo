package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.view.processing.SetPlotPApplet;

public class AbstractSetSetsListener extends AbstractEnvisButtonListener{
	SetPlotPApplet ePlotApplet;
	
	public void handleEnvisClassEvent(EventObject e){
		super.handleEnvisClassEvent(e);
		this.ePlotApplet = (SetPlotPApplet) eButton.getEpApplet();
	}
	
}
