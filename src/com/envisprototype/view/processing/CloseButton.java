package com.envisprototype.view.processing;

import com.envisprototype.model.processing.Coords;

public class CloseButton {
	private EnvisPApplet p;
	private boolean hover, selected;
	private Coords coords;
	
	private float triangleHeight = 10;
	private float origin = 0;
	public static int rectHeight = 20;
	private float parentWidth, parentHeight;
	
	public CloseButton(EnvisPApplet parent, float posX, float posY, float parent_w, float parent_h) {
		p = parent;
		hover = false;
		selected = false;
		coords = new Coords(posX, posY, 0);
		
		parentWidth = parent_w;
		parentHeight = parent_h;
	}
	
	
	public void setHover(boolean h) {
		hover = h;
	}
	
	public void setSelected(boolean s) {
		selected = s;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public void display() {
		
		if (hover) {
			p.fill(250);
		}
		
		else {
			p.fill(150);
		}

		p.rect(coords.getX() + origin, coords.getY() + origin + parentHeight - rectHeight, 
				parentWidth, rectHeight, 7);
		
		p.noStroke();
		p.fill(250);
		p.triangle(coords.getX() + origin, coords.getY() + origin + parentHeight, 
				coords.getX() + parentWidth, coords.getY() + origin + parentHeight, 
				(coords.getX() + parentWidth) / 2, (coords.getY() + parentHeight) - triangleHeight);
	}
}
