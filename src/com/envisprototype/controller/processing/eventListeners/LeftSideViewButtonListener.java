package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

public class LeftSideViewButtonListener extends AbstractEnvisButtonListener{
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(RotateScopeListener.isIfLeftSide()){
				RotateScopeListener.setIfLeftSide(false);
			}
			else{
				RotateScopeListener.setIfLeftSide(true);
				RotateScopeListener.setIfRotate(false);
				RotateScopeListener.setIfFront(false);
				RotateScopeListener.setIfTop(false);
			}
		}
	}
}
