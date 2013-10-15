package com.envisprototype.view.processing;


public class EnvisButton extends AbstractEnvisButton{
	int textSize;	
	int xOffset = 3;
	int yOffset = 3;
	int radii = 7;
	float tempTextSize;
	
	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}


	public EnvisButton(EnvisPApplet epApplet, String name) {
		super(epApplet, name);
		textSize = getDefH();
		setColor(255, 255, 255);
		tempTextSize  = epApplet.textAscent();
	}
	
	public EnvisButton(EnvisPApplet epApplet, String name, String btnID) {
		this(epApplet, name);
		this.btnID = btnID;
	}
	@Override
	public void drawMe() {
		// TODO Auto-generated method stub
		if(ifVisible){
			epApplet.pushMatrix();
			epApplet.noFill();
			epApplet.stroke(color[R],color[G], color[B]);
			epApplet.textSize(textSize);
			drawRect();
			drawText();
			epApplet.textSize(tempTextSize);
			epApplet.popMatrix();
		}
	}
	
	public void drawRect(){
		if(defW < epApplet.textWidth(name))
			defW = (int) (epApplet.textWidth(name)+xOffset+1);
		epApplet.rect(defX, defY, defW, defH, radii);
	}
	
	public void drawText(){
		epApplet.text(name, defX+xOffset, defY+textSize-yOffset);
	};


	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
	

	

	@Override
	public void rotate(float xRotate, float yRotate, float zRotate) {
		// TODO Auto-generated method stub
		
	}


	
	

}
