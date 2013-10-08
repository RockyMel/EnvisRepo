package com.envisprototype.model.processing;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

public class Coordinates implements Serializable{
	ArrayList<Float> coorX;
	ArrayList<Float> coorY; 
	
	public Coordinates(){
		coorX = new ArrayList<Float>();
		coorY = new ArrayList<Float>();
	}
	
	public void setMapCoodrinates(ArrayList<Float> coorX, ArrayList<Float> coorY){
		this.coorX = coorX;
		this.coorY = coorY;
	}

	
	public void setMapCoordinates(ArrayList<Float> coorX, ArrayList<Float> coorY){
		this.coorX = coorX;
		this.coorY = coorY;
	}
	
	@Override
	public String toString(){
		String stringToReturn = new String();
		for(int j = 0; j < coorX.size(); j++){
			stringToReturn += "x:" + coorX.get(j).toString();
			stringToReturn += "y:" + coorY.get(j).toString() + ";";
			  }
		return stringToReturn;
		
	}

	public ArrayList<Float> getCoorX() {
		return coorX;
	}

	public void setCoorX(ArrayList<Float> coorX) {
		this.coorX = coorX;
	}

	public ArrayList<Float> getCoorY() {
		return coorY;
	}

	public void setCoorY(ArrayList<Float> coorY) {
		this.coorY = coorY;
	}

	public String getXCoorString(){
		String temp="";
		Log.i("testingxcoorsize", coorX.size()+"");
		for(int j = 0; j < coorX.size(); j++){
			if(j==0)
				temp+=String.valueOf(coorX.get(j));
			else
				temp+="," + String.valueOf(coorX.get(j));
			  }
		return temp;
	}
	
	public String getYCoorString(){
		String temp="";
		for(int j = 0; j < coorY.size(); j++){
			if(j==0)
				temp=temp + String.valueOf(coorY.get(j));
			else
				temp=temp + "," + String.valueOf(coorY.get(j));
			  }
		return temp;
		
	}
	
}
