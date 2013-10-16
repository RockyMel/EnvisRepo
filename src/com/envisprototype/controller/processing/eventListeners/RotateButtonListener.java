package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

public class RotateButtonListener extends AbstractEnvisButtonListener{
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(RotateScopeListener.isIfRotate()){
				RotateScopeListener.setIfRotate(false);
			}
			else{
				RotateScopeListener.setIfRotate(true);
//				RotateScopeListener.setIfFront(false);
//				RotateScopeListener.setIfLeftSide(false);
//				RotateScopeListener.setIfTop(false);
			}
		}
	}

}
