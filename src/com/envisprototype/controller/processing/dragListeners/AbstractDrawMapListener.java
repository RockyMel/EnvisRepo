package com.envisprototype.controller.processing.dragListeners;

import java.util.EventObject;

import com.envisprototype.controller.processing.eventListeners.AbstractEnvisButtonListener;
import com.envisprototype.view.processing.DrawMapApplet;

public abstract class AbstractDrawMapListener extends AbstractEnvisButtonListener{
	protected DrawMapApplet drawMapApplet;
	
	public void handleEnvisClassEvent(EventObject e){
		super.handleEnvisClassEvent(e);
		if(this.drawMapApplet == null)
			this.drawMapApplet =  (DrawMapApplet) eButton.getEpApplet();
	}
	
	public void handleEnvisDragEvent(EventObject e){
		super.handleEnvisDragEvent(e);
		if(this.drawMapApplet == null)
			this.drawMapApplet =  (DrawMapApplet) eButton.getEpApplet();
	}
}
