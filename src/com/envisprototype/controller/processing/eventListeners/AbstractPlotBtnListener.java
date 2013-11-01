package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.view.processing.PlotPApplet;

public class AbstractPlotBtnListener extends AbstractEnvisButtonListener{
	
	protected PlotPApplet spApplet;
	
	public AbstractPlotBtnListener(){
		super();
		this.spApplet = null;
	}
	
	public void handleEnvisClassEvent(EventObject e){
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(spApplet == null){
				spApplet = (PlotPApplet)eButton.getEpApplet();
			}
		}
		}

}
