package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;


public class RemoveLastNodeBtnListener extends AbstractDrawMapListener{
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton() && !eButton.getName().equals(drawMapApplet.getString(R.string.disabled))){
			if(drawMapApplet.getEnvisMap().getRealCoors().getCoorX().size()>0){
				drawMapApplet.getEnvisMap().
				removeNode(drawMapApplet.getEnvisMap().getRealCoors().getCoorX().size()-1);
			}
		}
	}
}
