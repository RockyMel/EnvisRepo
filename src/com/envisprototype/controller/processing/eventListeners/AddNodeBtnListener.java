package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;

public class AddNodeBtnListener extends AbstractEditMapListener{


	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			//eButton.getEpApplet().getEnvisMap().
			// add node
			if(!EditMapListener.isAdding()){
				eButton.setText(eButton.getEpApplet().getString(R.string.add_node_step_one));
				editMapApplet.getDeleteNodeBtn().setText(editMapApplet.getString(R.string.delete_node_btn));
				editMapApplet.getMoveNodeBtn().setText(editMapApplet.getString(R.string.drag_node_btn));
				EditMapListener.setIfDrag(false);
				EditMapListener.setIfDelete(false);
				EditMapListener.setAdding(true);
			}
			else{
				switch(EditMapListener.addingStep){
				case 1:{
					eButton.setText(eButton.getEpApplet().getString(R.string.add_node_step_two));
				}
				break;
				case 2:{
					//EditMapListener.setAdding(false);
					//eButton.setName(eButton.getEpApplet().getString(R.string.add_node));					
				}
				break;
				}
				
//				EditMapListener.setAdding(false);
			}
			EditMapListener.addingStep++;
		}
	}

}
