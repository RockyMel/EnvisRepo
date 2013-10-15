package com.envisprototype.view.processing;


public abstract class AbstractEnvisButton extends UIElement{
	int defX, defY, defW, defH;
	boolean ifVisible, ifCanFireWithNoClick;
	String btnID;

	public AbstractEnvisButton(EnvisPApplet epApplet, String name) {
		super(epApplet, name);
		defW = epApplet.width/15;
		defH = epApplet.height/27;
		ifVisible = true;
		ifCanFireWithNoClick = false;
		this.btnID = null;
		// TODO Auto-generated constructor stub
	}
	
	public AbstractEnvisButton(EnvisPApplet epApplet, String name, String btnID) {
		super(epApplet, name);
		this.btnID = btnID;
	}
	
	
	
	public void setSize(int defW, int defH) {
				// TODO Auto-generated constructor stub
		this.defW = defW;
		this.defH = defH;
		}	
	
	public void setPlace(int defX, int defY){
		this.defX = defX;
		this.defY = defY;
	}
	
	public int getDefX() {
		return defX;
	}
	public void setDefX(int defX) {
		this.defX = defX;
	}
	public int getDefY() {
		return defY;
	}
	public void setDefY(int defY) {
		this.defY = defY;
	}
	public int getDefW() {
		return defW;
	}
	public void setDefW(int defW) {
		this.defW = defW;
	}
	public int getDefH() {
		return defH;
	}
	public void setDefH(int defH) {
		this.defH = defH;
	}
	
	public boolean isIfVisible() {
		return ifVisible;
	}

	public void setIfVisible(boolean ifVisible) {
		this.ifVisible = ifVisible;
	}
	public boolean isIfCanFireWithNoClick() {
		return ifCanFireWithNoClick;
	}
	
	public String getBtnID() {
		return btnID;
	}

	public void setBtnID(String btnID) {
		this.btnID = btnID;
	}



	public void setIfCanFireWithNoClick(boolean ifCanFireWithNoClick) {
		this.ifCanFireWithNoClick = ifCanFireWithNoClick;
	}
}
