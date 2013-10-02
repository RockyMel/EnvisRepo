package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;


public class RemoveSetsBtnListener extends AbstractSetSetsListener{
	
	@Override
	public void handleEnvisClassEvent(EventObject e){
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(!ePlotApplet.isIfRemovingSets()){
				// enable removing
				eButton.setName(ePlotApplet.getString(R.string.stop_remove_sets));
				ePlotApplet.setIfRemovingSets(true);
				ePlotApplet.setIfAddingSets(false);
				ePlotApplet.getAddSetsBtn().setName(ePlotApplet.getString(R.string.add_set));
			}
			else{
				eButton.setName(ePlotApplet.getString(R.string.remove_sets));
				ePlotApplet.setIfRemovingSets(false);
			}
		}
	}
}
