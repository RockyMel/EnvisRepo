package com.envisprototype.view.processing;

import android.util.Log;

public class ZCoorSpinner extends EnvisButton{
	
	private float xLine, yLine, maxX;
	private float initX;

	public ZCoorSpinner(EnvisPApplet epApplet, String name) {
		super(epApplet, name);
		initX = defX;
		xLine = this.defX+this.defW;
		yLine = this.defY+this.defH+this.defH/2;
		maxX = epApplet.MAX_WIDTH;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawRect() {
		// TODO Auto-generated method stub
		epApplet.line(xLine, yLine, maxX,  yLine);
		super.drawRect();
		
	}
	
	
	
	@Override
	public void setPlace(int defX, int defY) {
		// TODO Auto-generated method stub
		super.setPlace(defX, defY);
		initX = defX;
		xLine = this.defX;
		yLine = this.defY+this.defH/2;
	}
	
	

	@Override
	public void setSize(int defW, int defH) {
		// TODO Auto-generated method stub
		super.setSize(defW, defH);
		xLine = this.defX+this.defW;
		yLine = this.defY+this.defH+this.defH/2;
	}

	public float getxLine() {
		return xLine;
	}

	public void setxLine(int xLine) {
		this.xLine = xLine;
	}

	public float getyLine() {
		return yLine;
	}

	public void setyLine(int yLine) {
		this.yLine = yLine;
	}
	
	
	
	public float getInitX() {
		return initX;
	}

	public float calcZ(Map map){
		float percentage = (epApplet.mouseX-xLine)/(maxX-xLine);
		float zVal = percentage*map.getCOOR_Z();
		if(percentage < 0.5){
			zVal =-(map.getCOOR_Z()/2-zVal);
		}
		else{
			zVal-=map.getCOOR_Z()/2;
		}
		return zVal;
	}
	
	public void adjustZ(Map map){
		float percentage = (epApplet.mouseX-xLine)/(maxX-xLine);
		float zVal = percentage*(Map.COOR_Z_TOP - Map.COOR_Z_BOTTOM);
		map.setCOOR_Z((int)zVal);
	}
	

}
