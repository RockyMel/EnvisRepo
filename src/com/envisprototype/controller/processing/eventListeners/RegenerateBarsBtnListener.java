package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.view.processing.BarGraphSet;

public class RegenerateBarsBtnListener extends AbstractEnvisButtonListener{
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			
			// regenerating coors for all the bars
			for(BarGraphSet barSet: eButton.getEpApplet().getBarGraphSetList()){
				barSet.addGeneratedCoors();
			}
			
		}
	}
}
