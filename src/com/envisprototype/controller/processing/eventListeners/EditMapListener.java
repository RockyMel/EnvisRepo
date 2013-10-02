package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.view.processing.EditMapApplet;
import com.envisprototype.view.processing.EnvisPApplet;
import com.envisprototype.view.processing.Map;

public class EditMapListener implements EnvisButtonListener{
	
	private static boolean ifDelete = false;
	private static boolean ifDrag = false;
	private static boolean isAdding = false;
	private int addingStep = 0;
	private int firstIndex, secondIndex;
	
	private EditMapApplet envisPApplet;
	private Map envisMap;
	
	public EditMapListener(EnvisPApplet envisPApplet){
		this.envisPApplet = (EditMapApplet) envisPApplet;
		this.envisMap = envisPApplet.getEnvisMap();
	}

	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
			//eButton.getEpApplet().getEnvisMap().
			// delete node
		float tempX = envisPApplet.mouseX;
		float tempY = envisPApplet.mouseY;
		float mouseX = tempX - envisPApplet.width/2;// + center[Map.indexX] - envisPApplet.width/2;
		float mouseY = tempY - envisPApplet.height/2;// + center[Map.indexY] - envisPApplet.height/2;
		int nodeToModify = envisMap.ifHitNode(mouseX, mouseY);
			if(ifDelete){
				Log.i("edit", "Node to remove id: " + nodeToModify);
				if(nodeToModify != -1){
					envisMap.removeNode(nodeToModify);
					//ifDelete = false;
					}
			}
			if(ifDrag){
				if(nodeToModify != -1){
					envisMap.dragNode(nodeToModify);
					//ifDrag = false;
					}
			}
			if(isAdding){
				if(addingStep == 0){
			          // first step - choose the first point where to add
				          firstIndex = envisMap.ifHitNode(mouseX, mouseY);
				          if(firstIndex == -1)
				        	  return;
			          envisPApplet.getAddNodeBtn().setName(envisPApplet.getString(R.string.add_node_step_two));
			          }
			          if(addingStep == 1){
			        	  // second step
			        		  secondIndex = envisMap.ifHitNode(mouseX, mouseY);
			        		  if(secondIndex == -1)
					        	  return;
			            envisPApplet.getAddNodeBtn().setName(envisPApplet.getString(R.string.add_node_step_three));
			          }
				if(addingStep == 2){
					//adding new sensor
					envisPApplet.getAddNodeBtn().setName(envisPApplet.getString(R.string.add_node));
					int chosenIndex = (firstIndex > secondIndex )? firstIndex: secondIndex;
//					float[] center = envisMap.calculateMiddleCoors();
					envisMap.addNewNode(0, 0);
					envisMap.shiftNodes(chosenIndex);
					envisMap.dragNode(chosenIndex, tempX, tempY);
					addingStep = 0;
					isAdding = false;
				  }
				  addingStep++;
			}
			else{
				addingStep = 0;
			}
	}

	public static boolean isIfDelete() {
		return ifDelete;
	}

	public static void setIfDelete(boolean ifDelete) {
		EditMapListener.ifDelete = ifDelete;
	}

	public static boolean isIfDrag() {
		return ifDrag;
	}

	public static void setIfDrag(boolean ifDrag) {
		EditMapListener.ifDrag = ifDrag;
	}

	public static boolean isAdding() {
		return isAdding;
	}

	public static void setAdding(boolean isAdding) {
		EditMapListener.isAdding = isAdding;
	}
	
	
}