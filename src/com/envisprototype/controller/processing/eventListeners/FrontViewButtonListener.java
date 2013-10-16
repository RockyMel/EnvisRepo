package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

public class FrontViewButtonListener extends AbstractEnvisButtonListener{
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(RotateScopeListener.isIfFront()){
				RotateScopeListener.setIfFront(false);
			}
			else{
				RotateScopeListener.setIfFront(true);
				//RotateScopeListener.setIfRotate(false);
				RotateScopeListener.setIfLeftSide(false);
				RotateScopeListener.setIfTop(false);
			}
		}
	}
}
