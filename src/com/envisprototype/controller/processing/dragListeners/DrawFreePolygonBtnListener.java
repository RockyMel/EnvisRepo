package com.envisprototype.controller.processing.dragListeners;

import java.util.EventObject;

import com.envisprototype.R;
import com.envisprototype.view.processing.Map;

public class DrawFreePolygonBtnListener extends AbstractDrawMapListener{


	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton() && !eButton.getText().equals(drawMapApplet.getString(R.string.disabled))){
			drawMapApplet.setIfRectMap(false);
			drawMapApplet.setIfFreeshape(false);
			if(eButton.getText().equals(drawMapApplet.getString(R.string.draw_polygon))){
				drawMapApplet.getRectMapBtn().setText(drawMapApplet.getString(R.string.disabled));
				drawMapApplet.getFreeShapeBtn().setText(drawMapApplet.getString(R.string.disabled));
				eButton.setText(drawMapApplet.getString(R.string.stop_draw_polygon));
				drawMapApplet.setIfFreePolygon(true);
			}
			else{
				if(eButton.getText().equals(drawMapApplet.getString(R.string.stop_draw_polygon))){
					eButton.setText(drawMapApplet.getString(R.string.draw_polygon));
					drawMapApplet.getFreeShapeBtn().setText(drawMapApplet.getString(R.string.draw_free_shape));
					drawMapApplet.setIfFreePolygon(false);
					}
				}
			}
		}
}