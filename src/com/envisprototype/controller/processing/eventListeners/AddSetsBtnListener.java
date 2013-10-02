package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;


public class AddSetsBtnListener extends AbstractSetSetsListener{
	@Override
	public void handleEnvisClassEvent(EventObject e){
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(!ePlotApplet.isIfAddingSets()){
				// enable removing
				eButton.setName(ePlotApplet.getString(R.string.stop_add_set));
				ePlotApplet.setIfRemovingSets(false);
				ePlotApplet.setIfAddingSets(true);
				ePlotApplet.getRemoveLastSetBtn().setName(ePlotApplet.getString(R.string.remove_sets));
			}
			else{
				eButton.setName(ePlotApplet.getString(R.string.add_set));
				ePlotApplet.setIfAddingSets(false);
			}
		}
	}
}
