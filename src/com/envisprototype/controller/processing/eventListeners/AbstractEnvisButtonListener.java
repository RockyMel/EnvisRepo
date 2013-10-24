package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.controller.processing.dragListeners.EnvisDragListener;
import com.envisprototype.view.processing.AbstractEnvisButton;
import com.envisprototype.view.processing.EnvisButton;

public abstract class AbstractEnvisButtonListener implements EnvisButtonListener, EnvisDragListener{
	protected AbstractEnvisButton eButton;
	
	public void handleEnvisClassEvent(EventObject e){
		if(this.eButton == null)
			this.eButton = (EnvisButton) e.getSource();
	}
	
	public void handleEnvisDragEvent(EventObject e){
		if(this.eButton == null)
			this.eButton = (EnvisButton) e.getSource();
	}
	
	public boolean ifHitTheButton(){
		float defX, defY, defW, defH;
		defX = eButton.getDefX();
		defY = eButton.getDefY();
		defW = eButton.getDefW();
		defH = eButton.getDefH();
		// TODO Auto-generated method stub
		if(eButton.getEpApplet().mouseX>=defX 
				&& eButton.getEpApplet().mouseY>=defY
				&& eButton.getEpApplet().mouseY<=defY+defH
				&& eButton.getEpApplet().mouseX<=defX+defW){
			return true;
		}
		else{
			return false;
		}
	}
}
