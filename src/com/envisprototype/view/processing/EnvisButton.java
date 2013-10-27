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
		epApplet.pushMatrix();
		epApplet.noFill();
		if(defW < epApplet.textWidth(text))
			defW = (int) (epApplet.textWidth(text)+xOffset+1);
		epApplet.rect(defX, defY, defW, defH, radii);
		epApplet.popMatrix();
	}
	
	public void drawText(){
		epApplet.pushMatrix();
		epApplet.fill(color[R],color[G], color[B]);
		epApplet.text(text, defX+xOffset, defY+textSize-yOffset);
		epApplet.popMatrix();
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
