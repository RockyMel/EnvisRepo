package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import processing.core.PApplet;
import processing.core.PConstants;
import android.util.Log;

import com.envisprototype.view.processing.EnvisPApplet;

public class RotateScopeListener extends AbstractEnvisButtonListener{
	EnvisPApplet mainApplet = null;
	float xmag, ymag, zmag = 0;
	public static int X_ROT = 0, Y_ROT = 1, Z_ROT = 2;
	private static boolean ifFront = false, ifLeftSide = false, ifTop = false,
			ifPerspective = true;
	float xRotate, yRotate, zRotate;

	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(mainApplet == null)
			mainApplet = eButton.getEpApplet();
		if(ifHitTheButton()){
			//if(ifRotate){
				float[] rotationVals = calcRotation();
				if(ifFront)
					zRotate = rotationVals[Z_ROT];
				if(ifLeftSide)
					xRotate = rotationVals[Z_ROT];
				if(ifTop)
					zRotate = rotationVals[Z_ROT];
				if(ifPerspective){
					zRotate = rotationVals[Z_ROT];
				}
//				xRotate = rotationVals[X_ROT];
//				yRotate = rotationVals[Y_ROT];
//				zRotate = rotationVals[Z_ROT];
			//}
		}
		else{
			//if(!ifRotate){
				if(ifFront){
					//Log.i("rotate","front");
					xRotate = PConstants.PI/2;
					yRotate = 0;
					zRotate = 0;
				}
				if(ifLeftSide){
					//Log.i("rotate","left");
					xRotate = PConstants.PI/2;
					yRotate = 0;
					zRotate = 3*PConstants.PI/2;
					}
				if(ifTop){
					//Log.i("rotate","top");
					xRotate = 0;
					yRotate = 0;
					zRotate = 0;
				}
				if(ifPerspective){
					//Log.i("rotate","left");
					xRotate = PConstants.PI/4;
					yRotate = 0;
					zRotate = 3*PConstants.PI/4;
					}
			//}
		}
		mainApplet.getEnvisMap().rotate(xRotate, yRotate, zRotate);
//		for(int i = 0; i < mainApplet.getEnvisSensors().size(); i++){
//			mainApplet.getEnvisSensors().get(i).rotate(xRotate, yRotate, zRotate);
//		}
		mainApplet.getAxis().rotate(xRotate, yRotate, zRotate);
		mainApplet.scale(mainApplet.getEnvisMap().getZoomValue());
	}
	
	public float[] calcRotation(){
		mainApplet = eButton.getEpApplet();
		float newXmag = mainApplet.mouseX/PApplet.parseFloat(mainApplet.width) * 4 * PConstants.PI;
		float newYmag = mainApplet.mouseY/PApplet.parseFloat(mainApplet.height) * 4 * PConstants.PI;
		float diff = zmag-newXmag;
		  if (PApplet.abs(diff) >  0.01f) { 
			  zmag -= diff/5.0f; 
		  }
		  
		  diff = ymag-newYmag;
		  if (PApplet.abs(diff) >  0.01f) { 
		    ymag -= diff/5.0f; 
		  }
		  float[] rotationVals = {xmag, ymag, zmag};
		  return rotationVals;
	}

	public static boolean isIfFront() {
		return ifFront;
	}

	public static void setIfFront(boolean ifFront) {
		RotateScopeListener.ifFront = ifFront;
	}

	public static boolean isIfLeftSide() {
		return ifLeftSide;
	}

	public static void setIfLeftSide(boolean ifLeftSide) {
		RotateScopeListener.ifLeftSide = ifLeftSide;
	}

	public static boolean isIfTop() {
		return ifTop;
	}

	public static void setIfTop(boolean ifTop) {
		RotateScopeListener.ifTop = ifTop;
	}

	public static boolean isIfPerspective() {
		return ifPerspective;
	}

	public static void setIfPerspective(boolean ifPerspective) {
		RotateScopeListener.ifPerspective = ifPerspective;
	}
	
	

}