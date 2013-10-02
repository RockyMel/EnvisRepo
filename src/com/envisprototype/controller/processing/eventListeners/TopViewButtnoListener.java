package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

public class TopViewButtnoListener extends AbstractEnvisButtonListener{
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(RotateScopeListener.isIfTop()){
				RotateScopeListener.setIfTop(false);
			}
			else{
				RotateScopeListener.setIfTop(true);
				RotateScopeListener.setIfRotate(false);
				RotateScopeListener.setIfFront(false);
				RotateScopeListener.setIfLeftSide(false);
			}
		}
	}
}
