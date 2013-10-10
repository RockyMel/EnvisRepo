package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;


public class DragNodeBtnListener extends AbstractEditMapListener{


	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			if(!EditMapListener.isIfDrag()){
				eButton.setName(eButton.getEpApplet().getString(R.string.stop_drag_node_btn));
				editMapApplet.getDeleteNodeBtn().setName(editMapApplet.getString(R.string.delete_node_btn));
				editMapApplet.getAddNodeBtn().setName(editMapApplet.getString(R.string.add_node));
				EditMapListener.setIfDrag(true);
				EditMapListener.setIfDelete(false);
				EditMapListener.setAdding(false);
			}
			else{
				eButton.setName(eButton.getEpApplet().getString(R.string.drag_node_btn));
				EditMapListener.setIfDrag(false);
				}
			}
		}
	}