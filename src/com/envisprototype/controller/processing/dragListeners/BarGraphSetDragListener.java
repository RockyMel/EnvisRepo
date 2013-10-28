package com.envisprototype.controller.processing.dragListeners;

import java.util.EventObject;

import processing.core.PApplet;

import android.util.Log;

import com.envisprototype.controller.processing.eventListeners.EnvisButtonListener;
import com.envisprototype.view.processing.BarGraphSet;
import com.envisprototype.view.processing.EnvisPApplet;
import com.envisprototype.view.processing.Map;

public class BarGraphSetDragListener  implements EnvisButtonListener, EnvisDragListener{
	
	EnvisPApplet ePapplet;
	BarGraphSet eBarSet;
	int x,y;
	public BarGraphSetDragListener(EnvisPApplet ePapplet){ 
		this.x = (int) ePapplet.getEnvisMap().calculateMiddleCoors()[Map.indexX];
		this.y = (int) ePapplet.getEnvisMap().calculateMiddleCoors()[Map.indexY];
		
	}
	
	public void handleEnvisDragEvent(EventObject e){
		if(this.eBarSet == null)
			this.eBarSet = (BarGraphSet) e.getSource();
		if(this.ePapplet == null)
			ePapplet = eBarSet.getEpApplet();
		if(ifHitTheButton()){
//			if(this.eBarSet.hover(0)){
			// we should change xyz of the barset
			Log.i("coords", "This is in bargraphsetdraglistener");
			Log.i("coords", "defX: " +  eBarSet.getGraphCoords().get(0).getX());
			Log.i("coords", "x: " + x);
			Log.i("coords", "shirota: " + eBarSet.getBarGraphList().get(0).getWidth());
			Log.i("coords", "width/2: " + ePapplet.width/2);
			Log.i("coords", "mouseX: " + eBarSet.getEpApplet().mouseX);
			eBarSet.setPlace(ePapplet.mouseX - ePapplet.width/2 - eBarSet.getBarGraphList().get(0).getWidth()/2 + (int)PApplet.sin(20/102)*100,
					ePapplet.mouseY - ePapplet.height/2 - eBarSet.getBarGraphList().get(0).getWidth()/2 + (int)PApplet.sin(20/102)*100);
		}
	}
	
	public boolean ifHitTheButton(){
		float defX, defY, defW, defH;
		
		defW = eBarSet.getBarGraphList().get(0).getWidth();
		defH = eBarSet.getBarGraphList().get(0).getWidth();
		defX = eBarSet.getGraphCoords().get(0).getX();
		defY = eBarSet.getGraphCoords().get(0).getY();
		ePapplet.strokeWeight(5);
		ePapplet.stroke(255,255,0);
		float midX = defX + defW/2;
		float midY = defY - defH/2;
		ePapplet.point(midX, midY,100);
		
//		Log.i("coords", "defX: " + defX);
//		Log.i("coords", "x: " + x);
//		Log.i("coords", "width/2: " + ePapplet.width/2);
//		Log.i("coords", "mouseX: " + eBarSet.getEpApplet().mouseX);
		// TODO Auto-generated method stub
		ePapplet.text("abs x: " + (eBarSet.getEpApplet().mouseX - midX - x),100,100);
		ePapplet.text("abs y: " + (eBarSet.getEpApplet().mouseY - midY - y),100,110);
		ePapplet.text("defX: " + (midX),100,120);
		ePapplet.text("defY: " + (midY),100,130);
		ePapplet.text("x: " + x,100,140);	
		ePapplet.text("y: " + y,100,150);	
		if(PApplet.abs(eBarSet.getEpApplet().mouseX - midX-x) <= defW/2
				&& PApplet.abs(eBarSet.getEpApplet().mouseY - midY - y) <= defH/2){
			return true;
		}
		else{
			return false;
		}
	}


	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		
	}

}
