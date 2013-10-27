package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import com.envisprototype.R;
import com.envisprototype.view.processing.ThreeDVis;

public class VisTypeBtnListener extends AbstractEnvisButtonListener{
	ThreeDVis tdPapplet = null;

	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(this.tdPapplet == null && eButton.getEpApplet() instanceof ThreeDVis)
			this.tdPapplet =  (ThreeDVis) eButton.getEpApplet();
		if(ifHitTheButton()){
			if(eButton.getText().equals(tdPapplet.getString(R.string.bars_vis_type_btn))){
				eButton.setText(tdPapplet.getString(R.string.spheres_vis_type_btn));
				tdPapplet.setIfBars(false);
				tdPapplet.getRegenerateBarSetCoors().setIfVisible(false);
			}
			else{
				eButton.setText(tdPapplet.getString(R.string.bars_vis_type_btn));
				tdPapplet.setIfBars(true);
				tdPapplet.getRegenerateBarSetCoors().setIfVisible(true);
			}
		}
	}
}

//}
