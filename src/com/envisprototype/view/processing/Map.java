package com.envisprototype.view.processing;

import java.util.ArrayList;

import com.envisprototype.model.processing.Coordinates;

import processing.core.PApplet;
import processing.core.PConstants;
import android.util.Log;

public class Map extends UIElement{
	
	float xmag, ymag = 0;
	float newXmag, newYmag = 0; 
	float zoomValue = 1;
	float xRotate = 0, yRotate = 0, zRotate = 0;
	
	private int highlightedNode = -1;
	
	private final static int COOR_Z_TOP = -50;
	final static int COOR_Z_BOTTOM = 50;
	final static float MIDDLE_COEFF = 1f;
	public final static int indexX = 0;
	public final static int indexY = 1;
	public final static int indexZ = 2;
	final int POINT_RADIUS = 5;
	private boolean ifClosed = false;
	private boolean if3D = false;
	boolean ifBadPoint = false;
	CenterPoint center;
	private Coordinates visCoors; // these are to show the map. Will be tampered
	private Coordinates realCoors; 
	private String mapId;
	
	public void printCoors(){
		Log.i("map", "vis: "+ visCoors.getCoorX() + ", " + visCoors.getCoorY()
				+ " \n real: " + realCoors.getCoorX() + ", " + realCoors.getCoorY());
	}

	public Coordinates getRealCoors() {
		return realCoors;
	}

	public void setRealCoors(Coordinates realCoors) {
		this.realCoors = realCoors;
	}

	public Coordinates getVisCoors() {
		return visCoors;
	}

	public void setVisCoors(Coordinates visCoors) {
		this.visCoors = visCoors;
	}
	
	public void setAllCoors(Coordinates coors){
		for(int i = 0; i < coors.getCoorX().size(); i++){
			  visCoors.getCoorX().add(coors.getCoorX().get(i));
			  visCoors.getCoorY().add(coors.getCoorY().get(i));
			  realCoors.getCoorX().add(coors.getCoorX().get(i));
			  realCoors.getCoorY().add(coors.getCoorY().get(i));
		  }
	}

	public CenterPoint getCenter() {
		return center;
	}

	public void setCenter(CenterPoint center) {
		this.center = center;
	}

	public Map(EnvisPApplet epApplet, ArrayList<Float> coorX,
			ArrayList<Float>coorY) {
		super(epApplet);
		visCoors = new Coordinates();
		realCoors = new Coordinates();
		for(int i = 0; i < coorX.size(); i++){
			realCoors.getCoorX().add(coorX.get(i));
			realCoors.getCoorY().add(coorY.get(i));
			visCoors.getCoorX().add(coorX.get(i));
			visCoors.getCoorY().add(coorY.get(i));
		}
		
	}
	
	public Map(EnvisPApplet epApplet){
		super(epApplet);
		visCoors = new Coordinates();
		realCoors = new Coordinates();
	}
	
	public void removeNode(int nodeToRemove){
		if(visCoors.getCoorX().size()>nodeToRemove){
			visCoors.getCoorX().remove(nodeToRemove);
			visCoors.getCoorY().remove(nodeToRemove);
		}
		if(realCoors.getCoorX().size()>nodeToRemove){
			realCoors.getCoorX().remove(nodeToRemove);
			realCoors.getCoorY().remove(nodeToRemove);
		}
	}
	
	public void addNewNode(float mouseX, float mouseY){
		ifBadPoint = ifIntersects(visCoors.getCoorX(), visCoors.getCoorY(), mouseX, mouseY);
	    if(!ifBadPoint){
	    	visCoors.getCoorX().add(mouseX);
			visCoors.getCoorY().add(mouseY);
			realCoors.getCoorX().add(mouseX);
			realCoors.getCoorY().add(mouseY);
	    }
	    else{
	    	epApplet.println("pick another point");
	    }
	}
	
	public void dragNode(int nodeToDrag){
	//	float[] centerArray = calculateMiddleCoors();
		dragNode(nodeToDrag, epApplet.mouseX, epApplet.mouseY);
	}
	
	public void dragNode(int nodeToDrag, float mouseX, float mouseY){
		//float[] centerArray = calculateMiddleCoors();
		visCoors.getCoorX().set(nodeToDrag,mouseX- epApplet.width/2);
		visCoors.getCoorY().set(nodeToDrag,mouseY- epApplet.height/2);
		realCoors.getCoorX().set(nodeToDrag,mouseX);
		realCoors.getCoorY().set(nodeToDrag,mouseY);
	}
	
	public void shiftNodes(int index){
		for(int i = realCoors.getCoorX().size()-1; i > index ; i--){
			visCoors.getCoorX().set(i, visCoors.getCoorX().get(i-1));
			visCoors.getCoorY().set(i, visCoors.getCoorY().get(i-1));
			realCoors.getCoorX().set(i, realCoors.getCoorX().get(i-1));
			realCoors.getCoorY().set(i, realCoors.getCoorY().get(i-1));
		}
	}
	
	public void drawRectWhileDragging(){
		if(realCoors.getCoorX().size()>0){
			float defX = realCoors.getCoorX().get(0);
			float defY =  realCoors.getCoorY().get(0);
			int defW = (int) (epApplet.mouseX-realCoors.getCoorX().get(0));
			int defH = (int) (epApplet.mouseY-realCoors.getCoorY().get(0));
			epApplet.rect(defX, defY, defW, defH);
			epApplet.pushMatrix();
			epApplet.textSize(epApplet.height/40);
			epApplet.text(defW,defX+defW/2,defY);
			epApplet.text(defH,defX,defY+defH/2);
			epApplet.popMatrix();
		}
	}
	
	public void drawLineWithDimsWhileDragging(){
		if(realCoors.getCoorX().size()>0){
			float startX = realCoors.getCoorX().get(realCoors.getCoorX().size()-1);
			float startY =  realCoors.getCoorY().get(realCoors.getCoorY().size()-1);
			int endX = (int) (epApplet.mouseX);
			int endY = (int) (epApplet.mouseY);
			int lineWidth = (int) (endX - startX);
			int lineHeight = (int) (endY - startY);
			int lineSize = (int) PApplet.sqrt(lineWidth*lineWidth+lineHeight*lineHeight);
			boolean ifIntersects = ifIntersects(visCoors.getCoorX(), visCoors.getCoorY(),
					endX, endY);
			epApplet.pushMatrix();
			if(ifIntersects)
				epApplet.stroke(255,0,0);
			epApplet.textSize(epApplet.height/40);
			epApplet.line(startX, startY, endX, endY);
			epApplet.text(lineSize, (startX+endX)/2,epApplet.height/20+(startY+endY)/2);
			epApplet.popMatrix();
		}
	}

	public void drawMe2D(){
		float dispX, dispY;
		//epApplet.ellipseMode(PConstants.CORNERS);
		switch(visCoors.getCoorX().size()){
		case 0:
			return;
		case 1:{
			dispX = realCoors.getCoorX().get(0);
			dispY = realCoors.getCoorY().get(0);
			epApplet.text(dispX + ", " + dispY,
				    visCoors.getCoorX().get(0), visCoors.getCoorY().get(0)+10);
			epApplet.ellipse(visCoors.getCoorX().get(0)-POINT_RADIUS,
		    		visCoors.getCoorY().get(0)-POINT_RADIUS,
		    		visCoors.getCoorX().get(0)+POINT_RADIUS,
		    		visCoors.getCoorY().get(0)+POINT_RADIUS);
			return;
		}
		default:{
			for(int i = visCoors.getCoorX().size()-1; i >  0; i--){
				dispX = realCoors.getCoorX().get(i);
				dispY = realCoors.getCoorY().get(i);
				epApplet.stroke(EnvisPApplet.STROKE_COLOR);
			    epApplet.line(visCoors.getCoorX().get(i),visCoors.getCoorY().get(i),
			    		visCoors.getCoorX().get(i-1), visCoors.getCoorY().get(i-1));
			    //epApplet.fill(217,200,33);
			    epApplet.text(dispX + ", " + dispY,
			    visCoors.getCoorX().get(i), visCoors.getCoorY().get(i)+10);
			    if(i == highlightedNode)
			    	epApplet.stroke(255,0,0);
			    epApplet.ellipse(visCoors.getCoorX().get(i)-POINT_RADIUS,
			    		visCoors.getCoorY().get(i)-POINT_RADIUS,
			    		visCoors.getCoorX().get(i)+POINT_RADIUS,
			    		visCoors.getCoorY().get(i)+POINT_RADIUS);
			    epApplet.stroke(255,255,255);
			}
			}
		}
		dispX = realCoors.getCoorX().get(0);
		dispY = realCoors.getCoorY().get(0);
		if(ifClosed){
			epApplet.line(visCoors.getCoorX().get(visCoors.getCoorX().size()-1),
					visCoors.getCoorY().get(visCoors.getCoorY().size()-1),
		    		visCoors.getCoorX().get(0), visCoors.getCoorY().get(0));
		}
		epApplet.text(dispX + ", " + dispY,
				visCoors.getCoorX().get(0), visCoors.getCoorY().get(0)+10);
		if(highlightedNode == 0)
	    	epApplet.stroke(255,0,0);
		epApplet.ellipse(visCoors.getCoorX().get(0)-POINT_RADIUS,
	    		visCoors.getCoorY().get(0)-POINT_RADIUS,
	    		visCoors.getCoorX().get(0)+POINT_RADIUS,
	    		visCoors.getCoorY().get(0)+POINT_RADIUS);
	}
	
	public void closeFigure(){
		if(visCoors.getCoorX().size()<3)
			return;
		float mouseX = visCoors.getCoorX().get(0);
		float mouseY = visCoors.getCoorY().get(0);
		try{
			boolean ifIntersects = ifIntersects(visCoors.getCoorX(), visCoors.getCoorY(),
					mouseX, mouseY);
			if(ifIntersects)
				throw new Exception();
		}catch(Exception e){
			PApplet.println("Not a good time to finish");
			return;
		}finally{
    }
			if3D = true;
		ifClosed = true;
	}
	
	@Override
	public void drawMe(){
		if(visCoors.getCoorX() == null || visCoors.getCoorY() == null){
			Log.e("fail","no coordinates defined");
		}
		epApplet.pushMatrix();
		epApplet.stroke(EnvisPApplet.STROKE_COLOR);
		epApplet.strokeWeight(EnvisPApplet.STROKE_WEIGHT);
		epApplet.hint(EnvisPApplet.DISABLE_DEPTH_TEST);
		drawMapVertex();
		epApplet.popMatrix();
		}
	
	private void drawMapVertex(){
		if(visCoors.getCoorX().size() == 0 || visCoors.getCoorY().size() == 0)
			return;
		visCoors.getCoorX().add(visCoors.getCoorX().get(0));
		visCoors.getCoorY().add(visCoors.getCoorY().get(0));
		epApplet.beginShape();
		for(int j = 0; j < visCoors.getCoorX().size()-1; j++){
			epApplet.vertex(visCoors.getCoorX().get(j),visCoors.getCoorY().get(j),getCoorZTop());          
			epApplet.vertex(visCoors.getCoorX().get(j+1),visCoors.getCoorY().get(j+1),getCoorZTop());
			epApplet.vertex(visCoors.getCoorX().get(j+1),visCoors.getCoorY().get(j+1),COOR_Z_BOTTOM);
			epApplet.vertex(visCoors.getCoorX().get(j),visCoors.getCoorY().get(j),COOR_Z_BOTTOM);
			epApplet.vertex(visCoors.getCoorX().get(j),visCoors.getCoorY().get(j),getCoorZTop());
			epApplet.vertex(visCoors.getCoorX().get(j+1),visCoors.getCoorY().get(j+1),getCoorZTop());
			}
		epApplet.endShape(PConstants.CLOSE);
		visCoors.getCoorX().remove(visCoors.getCoorX().size()-1);
		visCoors.getCoorY().remove(visCoors.getCoorY().size()-1);
	}
	

	@Override
	public void rotate(float xRotate, float yRotate, float zRotate){
		  
		  
		  //rotateX(ymag*0.5); 
		  //rotateY(xmag);
		  //rotateX(PI/2); 
		epApplet.rotateX(xRotate);
		epApplet.rotateY(yRotate);
		epApplet.rotateZ(zRotate);
//		this.xRotate = xRotate;
//		this.yRotate = yRotate;
//		this.zRotate = zRotate;
		}
	
	public float getZoomValue() {
		return zoomValue;
	}

	public void setZoomValue(float zoomValue) {
		this.zoomValue = zoomValue;
	}

	public float getXmag() {
		return xmag;
	}

	public void setXmag(float xmag) {
		this.xmag = xmag;
	}

	public float getYmag() {
		return ymag;
	}

	public void setYmag(float ymag) {
		this.ymag = ymag;
	}
	

public boolean ifIntersects(ArrayList<Float> coorX, ArrayList<Float> coorY,
		float mouseX, float mouseY){
  if(coorX.size() < 2)
  return false;
  float x2, x1, y2, y1, xLast, yLast, xNew, yNew;
  float v1,v2,v3,v4;
   float a1, b1, c1, a2, b2, c2;
  float xInter, yInter;
  xLast = coorX.get(coorX.size()-1);
  yLast = coorY.get(coorY.size()-1);
  xNew = mouseX;
  yNew = mouseY;
  if(coorX.size()>2){
    for(int j = 0; j < coorX.size()-2; j++){
    // Getting coordinates of the 1st and second points that were already connected
    x1 = coorX.get(j);
    y1 = coorY.get(j);
    x2 = coorX.get(j+1);
    y2 = coorY.get(j+1);
    
    
    v1 = (xNew-xLast)*(y1-yLast) - (yNew-yLast)*(x1-xLast);
    v2 = (xNew-xLast)*(y2-yLast) - (yNew-yLast)*(x2-xLast);
    v3 = (x2-x1)*(yLast-y1) - (y2-y1)*(xLast-x1);
    v4 = (x2-x1)*(yNew-y1) - (y2-y1)*(xNew-x1);
    // Builiding equations

    if(v1*v2<0 && v3*v4 <0){
      return true;
    }
    else{
      epApplet.println();
    }
    }
  }
  return false;
}


//CHANGE TO GET COORDINATES FROM MAPMODEL -- TO BE DONE 
	public float[] calculateMiddleCoors(){
		 float[] coors = new float[3];
		  Float minX, maxX, minY, maxY;
		  if(realCoors.getCoorX().size() < 2){
			  epApplet.println("not enought points to find the middle");
		    return null;
		  }
		  minX = realCoors.getCoorX().get(0);
		  maxX = realCoors.getCoorX().get(0);
		  minY = realCoors.getCoorY().get(0);
		  maxY = realCoors.getCoorY().get(0);
		  for(int j = 0; j < realCoors.getCoorX().size(); j++){
		    if(minX > realCoors.getCoorX().get(j)){
		      minX = realCoors.getCoorX().get(j);
		    }
		    if(maxX < realCoors.getCoorX().get(j)){
		      maxX = realCoors.getCoorX().get(j);
		    }
		    if(minY > realCoors.getCoorY().get(j)){
		      minY = realCoors.getCoorY().get(j);
		    }
		    if(maxY < realCoors.getCoorY().get(j)){
		      maxY = realCoors.getCoorY().get(j);
		    }
		  }
		  coors[0] = (maxX+minX)/2;
		  coors[1] = (maxY+minY)/2;
		  coors[2] = (Map.COOR_Z_BOTTOM+Map.getCoorZTop())/2;
		  return coors;
	}
	
	// find the middle of the 2D figure to translate image there
	public void translateToMiddle(){
		//epApplet.translate(epApplet.width/2, epApplet.height/2);
	  float[] coors = calculateMiddleCoors();
	  if(coors == null)
		  return;
		 for(int j = 0; j < visCoors.getCoorX().size(); j++){
			 visCoors.getCoorX().set(j, visCoors.getCoorX().get(j)-
					 (Float)coors[indexX]*MIDDLE_COEFF);
			 visCoors.getCoorY().set(j, visCoors.getCoorY().get(j)-
					 (Float)coors[indexY]*MIDDLE_COEFF);
		      }
		 ifClosed = true;
	}
	
	public int ifHitNode(float mouseX, float mouseY){
		//Log.i("edit", "MouseX = " + mouseX + " mouseY = " + mouseY);
		for(int i = 0; i < visCoors.getCoorX().size(); i++){
			//Log.i("edit", "x = " + visCoors.getCoorX().get(i) + " y = " + visCoors.getCoorY().get(i));
			if(PApplet.sqrt(PApplet.pow((mouseX-visCoors.getCoorX().get(i)),2)+
					PApplet.pow((mouseY-visCoors.getCoorY().get(i)),2))<=
					PApplet.pow(POINT_RADIUS,2)){
				return i;
			}
		}
		return -1;
	}
	

	public boolean isIfCentered() {
		return ifClosed;
	}

	public void setIfCentered(boolean ifCentered) {
		this.ifClosed = ifCentered;
	}

	public static int getCoorZTop() {
		return COOR_Z_TOP;
	}

	public boolean isIf3D() {
		return if3D;
	}

	public void setIf3D(boolean if3D) {
		this.if3D = if3D;
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public int getHighlightedNode() {
		return highlightedNode;
	}

	public void setHighlightedNode(int highlightedNode) {
		this.highlightedNode = highlightedNode;
	}

	class CenterPoint{
	  private Float x, y, z;
	  
	  CenterPoint(Float x, Float y, Float z){
	    this.x = x;
	    this.y = y;
	    this.z = z;
	  }
	  
	  public Float getX(){
	    return x;
	  }
	  public Float getY(){
	    return y;
	  }
	  
	  public Float getZ(){
	    return z;
	  }
	}
	
	

}
