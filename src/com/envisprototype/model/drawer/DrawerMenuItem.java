package com.envisprototype.model.drawer;

public class DrawerMenuItem {
	private int menuIconID;
	private String menuName;

	public DrawerMenuItem() {
	}

	public DrawerMenuItem(String menuName, int menuIconID) {
		// TODO Auto-generated constructor stub
		this.menuName = menuName;
		this.menuIconID = menuIconID;
	}

	public void setMenuIcon(int menuIconID) {
		this.menuIconID = menuIconID;
	}

	public int getMenuIcon() {
		return menuIconID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
