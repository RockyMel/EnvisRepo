package com.envisprototype.view.processing;

public class PositionDisplay extends EnvisButton{
	public PositionDisplay(EnvisPApplet epApplet, String name) {
		super(epApplet, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawMe() {
		// TODO Auto-generated method stub
		super.drawMe();
	}
	
	@Override
	public void drawRect(){
		epApplet.rect(defX, defY, epApplet.textWidth(epApplet.mouseX + ", " + epApplet.mouseY + "; "), defH, radii);
	}
	
	@Override
	public void drawText(){
		epApplet.stroke(color[R],color[G], color[B]);
		epApplet.text(epApplet.mouseX + ", " + epApplet.mouseY + "; ", defX+xOffset, defY+textSize-yOffset);
	}
	
	public void drawText(String msg){
		epApplet.stroke(color[R],color[G], color[B]);
		epApplet.text(msg, defX+xOffset, defY+textSize-yOffset);
	}


}
