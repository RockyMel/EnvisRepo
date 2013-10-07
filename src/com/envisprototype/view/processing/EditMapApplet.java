package com.envisprototype.view.processing;

import com.envisprototype.R;
import com.envisprototype.controller.processing.eventListeners.AddNodeBtnListener;
import com.envisprototype.controller.processing.eventListeners.DeleteNodeBtnListener;
import com.envisprototype.controller.processing.eventListeners.EditMapListener;
import com.envisprototype.controller.processing.eventListeners.MoveNodeBtnListener;
import com.envisprototype.controller.processing.eventListeners.SaveMapBtnListener;

public class EditMapApplet extends EnvisPApplet {

final int POINT_RADIUS = 5;
final int TEXT_Y_SHIFT = 15;
int addingStep = 0;
EnvisButton addNodeBtn, deleteNodeBtn, moveNodeBtn, saveMap;

public void setup(){
	super.setup();
	addNodeBtn = new EnvisButton(this, getString(R.string.add_node));
	addNodeBtn.setPlace(DEF_BTN_X, height/30);
	addNodeBtn.addEventListener(new AddNodeBtnListener());
	deleteNodeBtn = new EnvisButton(this, getString(R.string.delete_node_btn));
	deleteNodeBtn.setPlace(DEF_BTN_X, 3*height/30);
	deleteNodeBtn.addEventListener(new DeleteNodeBtnListener());
	moveNodeBtn = new EnvisButton(this, getString(R.string.drag_node_btn));
	moveNodeBtn.setPlace(DEF_BTN_X, 5*height/30);
	moveNodeBtn.addEventListener(new MoveNodeBtnListener());
	envisMap.translateToMiddle();
	envisMap.addEventListener(new EditMapListener(this));
	saveMap = new EnvisButton(this, "Save map");
	saveMap.setPlace(DEF_BTN_X, height-height/20);
	saveMap.addEventListener(new SaveMapBtnListener(this, "map.txt"));
	envisMap.setIfCentered(true);
}

public void draw(){
	super.draw();
	addNodeBtn.drawMe();
	moveNodeBtn.drawMe();
	deleteNodeBtn.drawMe();
	saveMap.drawMe();
	float[] center = envisMap.calculateMiddleCoors();
	if(center != null)
		currentClick.drawText((mouseX + center[Map.indexX] - width/2) + ", " + (mouseY + center[Map.indexY] - height/2)+ "; ");
	pushMatrix();
	translate(width/2, height/2);
	envisMap.drawMe2D();
	popMatrix();
}

public void mouseDragged(){
	envisMap.fireEvent();
}

public void mouseReleased(){
	deleteNodeBtn.fireEvent();
	saveMap.fireEvent();
	moveNodeBtn.fireEvent();
	addNodeBtn.fireEvent();
}

public EnvisButton getAddNodeBtn() {
	return addNodeBtn;
}

public EnvisButton getDeleteNodeBtn() {
	return deleteNodeBtn;
}

public EnvisButton getMoveNodeBtn() {
	return moveNodeBtn;
}


}