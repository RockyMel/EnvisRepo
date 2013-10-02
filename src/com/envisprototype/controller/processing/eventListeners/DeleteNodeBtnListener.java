package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;


public class DeleteNodeBtnListener extends AbstractEditMapListener{
	

	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(!EditMapListener.isIfDelete()){
				eButton.setName(editMapApplet.getString(R.string.choose_node_to_delete));
				editMapApplet.getAddNodeBtn().setName(editMapApplet.getString(R.string.add_node));
				editMapApplet.getMoveNodeBtn().setName(editMapApplet.getString(R.string.drag_node_btn));
				EditMapListener.setIfDrag(false);
				EditMapListener.setIfDelete(true);
				EditMapListener.setAdding(false);
			}
			else{
				eButton.setName(editMapApplet.getString(R.string.delete_node_btn));
				EditMapListener.setIfDelete(false);
				}
		}
	}
}