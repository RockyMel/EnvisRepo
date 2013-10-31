package com.envisprototype.controller.processing.dragListeners;

import java.util.EventObject;

import com.envisprototype.controller.processing.eventListeners.AbstractEnvisButtonListener;
import com.envisprototype.view.processing.Map;
import com.envisprototype.view.processing.ZCoorSpinner;

public class ZMapSizeBtnListener extends AbstractEnvisButtonListener{
	protected ZCoorSpinner spinner;
	
	@Override
	public void handleEnvisDragEvent(EventObject e){
		//super.handleEnvisDragEvent(e);
		if(this.spinner == null)
			this.spinner = (ZCoorSpinner) e.getSource();
		if(ifHitTheButton()){
			if( spinner.getInitX() < spinner.getEpApplet().mouseX &&
					spinner.getEpApplet().getMAX_WIDTH() > spinner.getEpApplet().mouseX){
				spinner.setDefX(spinner.getEpApplet().mouseX-spinner.getDefH()/2);
				int newZ = (int) spinner.adjustValue(spinner.getEpApplet().getEnvisMap(), Map.COOR_Z_TOP, Map.COOR_Z_BOTTOM);
				spinner.getEpApplet().getEnvisMap().setCOOR_Z(newZ);
			}
		}
	}
	
	public boolean ifHitTheButton(){
		float defX, defY, defW, defH;
		defX = spinner.getDefX();
		defY = spinner.getDefY();
		defW = spinner.getDefW();
		defH = spinner.getDefH();
		// TODO Auto-generated method stub
		if(spinner.getEpApplet().mouseX>=defX 
				&& spinner.getEpApplet().mouseY>=defY
				&& spinner.getEpApplet().mouseY<=defY+defH
				&& spinner.getEpApplet().mouseX<=defX+defW){
			return true;
		}
		else{
			return false;
		}
	}

}