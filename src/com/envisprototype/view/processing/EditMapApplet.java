package com.envisprototype.view.processing;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.controller.processing.eventListeners.AddNodeBtnListener;
import com.envisprototype.controller.processing.eventListeners.DeleteNodeBtnListener;
import com.envisprototype.controller.processing.eventListeners.DragNodeBtnListener;
import com.envisprototype.controller.processing.eventListeners.EditMapListener;
import com.envisprototype.controller.processing.eventListeners.SaveMapBtnListener;

public class EditMapApplet extends EnvisPApplet {

	final int POINT_RADIUS = 5;
	final int TEXT_Y_SHIFT = 15;
	int addingStep = 0;
	EnvisButton addNodeBtn, deleteNodeBtn, dragNodeBtn, saveMap;

	public void setup(){
		super.setup();
		// Setting up the default set of visual elements

		addNodeBtn = new EnvisButton(this, getString(R.string.add_node));
		addNodeBtn.setPlace(DEF_BTN_X, height/30);
		addNodeBtn.addEventListener(new AddNodeBtnListener());
		deleteNodeBtn = new EnvisButton(this, getString(R.string.delete_node_btn));
		deleteNodeBtn.setPlace(DEF_BTN_X, 3*height/30);
		deleteNodeBtn.addEventListener(new DeleteNodeBtnListener());
		dragNodeBtn = new EnvisButton(this, getString(R.string.drag_node_btn));
		dragNodeBtn.setPlace(DEF_BTN_X, 5*height/30);
		dragNodeBtn.addEventListener(new DragNodeBtnListener());
		Log.i("in editmap","beginning " +  envisMap.getRealCoors().toString());
		envisMap.translateToMiddle();
		Log.i("in editmap","end " +  envisMap.getRealCoors().toString());
		envisMap.addEventListener(new EditMapListener(this));
		saveMap = new EnvisButton(this, "Save map");
		saveMap.setPlace(DEF_BTN_X, height-height/20);
		saveMap.addEventListener(new SaveMapBtnListener());
		envisMap.setIfCentered(true);

	}

	public void draw(){
		super.draw();
		addNodeBtn.drawMe();
		dragNodeBtn.drawMe();
		deleteNodeBtn.drawMe();
		saveMap.drawMe();
		//	float[] center = envisMap.calculateMiddleCoors();
		/* center - means that the map has been centered. (vis coordinates are put 
		 * so that 0,0 point is in the middle of the figure)
		 */
		currentClick.drawMe();
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
		dragNodeBtn.fireEvent();
		addNodeBtn.fireEvent();
	}

	public EnvisButton getAddNodeBtn() {
		return addNodeBtn;
	}

	public EnvisButton getDeleteNodeBtn() {
		return deleteNodeBtn;
	}

	public EnvisButton getMoveNodeBtn() {
		return dragNodeBtn;
	}

	public EnvisButton getSaveMap() {
		return saveMap;
	}

	public void setSaveMap(EnvisButton saveMap) {
		this.saveMap = saveMap;
	}


}