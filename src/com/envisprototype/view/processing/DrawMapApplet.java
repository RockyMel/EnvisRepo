package com.envisprototype.view.processing;

import android.util.Log;
import android.view.KeyEvent;

import com.envisprototype.R;
import com.envisprototype.controller.processing.dragListeners.DrawingScopeListener;
import com.envisprototype.controller.processing.eventListeners.AddToDrawingScopeListener;
import com.envisprototype.controller.processing.eventListeners.DrawFreePolygonBtnListener;
import com.envisprototype.controller.processing.eventListeners.DrawFreeShapeBtnListener;
import com.envisprototype.controller.processing.eventListeners.DrawRectMapBtnListener;
import com.envisprototype.controller.processing.eventListeners.ExitFromDrawMapAppletListener;
import com.envisprototype.controller.processing.eventListeners.RemoveLastNodeBtnListener;
import com.envisprototype.controller.processing.eventListeners.SaveMapBtnListener;

public class DrawMapApplet extends EnvisPApplet {

	EnvisButton closeFigure, rectMapBtn, freeShapeBtn, drawPolygonBtn,
	removeLastNodeBtn, exitBtn;
	EnvisButton drawingScope;
	private boolean ifFreeshape = false;
	private boolean ifRectMap = false;
	private boolean ifFreePolygon = false;
	
public void setup(){
	super.setup();
	closeFigure = new EnvisButton(this, "Close");
	closeFigure.setPlace(DEF_BTN_X, height-height/25);
	closeFigure.addEventListener(new SaveMapBtnListener(this));
	envisMap = new Map(this);
	rectMapBtn = new EnvisButton(this, "Draw rect");
	rectMapBtn.setPlace(DEF_BTN_X, height/29);
	rectMapBtn.addEventListener(new DrawRectMapBtnListener());
	freeShapeBtn = new EnvisButton(this, "Free shape");
	freeShapeBtn.setPlace(DEF_BTN_X, 3*height/29);
	freeShapeBtn.addEventListener(new DrawFreeShapeBtnListener());
	drawPolygonBtn = new EnvisButton(this, getString(R.string.draw_polygon));
	drawPolygonBtn.setPlace(DEF_BTN_X, 5*height/29);
	drawPolygonBtn.addEventListener(new DrawFreePolygonBtnListener());
	drawingScope = new EnvisButton(this, "");
	drawingScope.setPlace(1, 1);
	drawingScope.setSize(MAX_WIDTH, height-2);
	drawingScope.addEventListener(new AddToDrawingScopeListener());
	drawingScope.addDragEventListener(new DrawingScopeListener());
	removeLastNodeBtn = new EnvisButton(this, getString(R.string.remove_last_node));
	removeLastNodeBtn.setPlace(DEF_BTN_X, 7*height/29);
	removeLastNodeBtn.addEventListener(new RemoveLastNodeBtnListener());
	exitBtn = new EnvisButton(this, "Exit");
	exitBtn.setPlace(DEF_BTN_X, height-height/25);
	exitBtn.addEventListener(new ExitFromDrawMapAppletListener());
}

public void draw(){
  super.draw();
  if(!envisMap.isIfCentered()){
	  // drawing a button for figure closure
	  //drawingScope.drawRect();
	  currentClick.drawMe();
	  closeFigure.drawMe();
	  rectMapBtn.drawMe();
	  freeShapeBtn.drawMe();
	  envisMap.drawMe2D();
	  drawPolygonBtn.drawMe();
	  removeLastNodeBtn.drawMe();
	  drawingScope.fireDragEvent();
  }
  else{
	  threeDDrawPreset(false); // false - no sets of sensors will be displayed
	  exitBtn.drawMe();
  }
}

public void mouseReleased(){
	if(!envisMap.isIfCentered()){
		freeShapeBtn.fireEvent();
		closeFigure.fireEvent();
		rectMapBtn.fireEvent();
		drawingScope.fireEvent();	
		drawPolygonBtn.fireEvent();
		removeLastNodeBtn.fireEvent();
	}
	else{
		exitBtn.fireEvent();
	}
}

public void mouseDragged(){
	super.mouseDragged();
	if(!envisMap.isIfCentered()){
		if(ifFreeshape)
			drawingScope.fireEvent();
	}
	else{
		rotateScope.fireEvent();
	}
}

public boolean isIfFreeshape() {
	return ifFreeshape;
}

public void setIfFreeshape(boolean ifFreeshape) {
	this.ifFreeshape = ifFreeshape;
}

public boolean isIfRectMap() {
	return ifRectMap;
}

public void setIfRectMap(boolean ifRectMap) {
	this.ifRectMap = ifRectMap;
}

public boolean isIfFreePolygon() {
	return ifFreePolygon;
}

public void setIfFreePolygon(boolean ifFreePolygon) {
	this.ifFreePolygon = ifFreePolygon;
}

public EnvisButton getCloseFigure() {
	return closeFigure;
}

public void setCloseFigure(EnvisButton closeFigure) {
	this.closeFigure = closeFigure;
}

public EnvisButton getRectMapBtn() {
	return rectMapBtn;
}

public void setRectMapBtn(EnvisButton rectMapBtn) {
	this.rectMapBtn = rectMapBtn;
}

public EnvisButton getFreeShapeBtn() {
	return freeShapeBtn;
}

public void setFreeShapeBtn(EnvisButton freeShapeBtn) {
	this.freeShapeBtn = freeShapeBtn;
}

public EnvisButton getDrawPolygonBtn() {
	return drawPolygonBtn;
}

public void setDrawPolygonBtn(EnvisButton drawPolygonBtn) {
	this.drawPolygonBtn = drawPolygonBtn;
}

public EnvisButton getDrawingScope() {
	return drawingScope;
}

public void setDrawingScope(EnvisButton drawingScope) {
	this.drawingScope = drawingScope;
}

public EnvisButton getRemoveLastNodeBtn() {
	return removeLastNodeBtn;
}

public void setRemoveLastNodeBtn(EnvisButton removeLastNodeBtn) {
	this.removeLastNodeBtn = removeLastNodeBtn;
}






}
