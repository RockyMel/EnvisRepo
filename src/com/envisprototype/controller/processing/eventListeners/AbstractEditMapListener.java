package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.view.processing.EditMapApplet;

public class AbstractEditMapListener extends AbstractEnvisButtonListener{
	protected EditMapApplet editMapApplet = null;
	
	public void handleEnvisClassEvent(EventObject e){
		super.handleEnvisDragEvent(e);
		if(this.editMapApplet == null)
			this.editMapApplet =  (EditMapApplet) eButton.getEpApplet();
	}
}
