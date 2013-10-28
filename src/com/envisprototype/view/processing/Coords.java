package com.envisprototype.view.processing;


public class Coords {
	
	float x, y, z;
	
	public Coords(float f, float g, float h) {
		this.x = f;
		this.y = g;
		this.z = h;
	}
	

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}
	
	public void setCoords(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
		  

}
