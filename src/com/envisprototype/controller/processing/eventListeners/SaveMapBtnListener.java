package com.envisprototype.controller.processing.eventListeners;

import java.util.EventObject;

import android.util.Log;

import com.envisprototype.R;
import com.envisprototype.model.DBHelper.MapInfoDBHelper;
import com.envisprototype.model.LocalDBHelper.MapLocalDBHelper;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.processing.Coordinates;
import com.envisprototype.view.processing.DrawMapApplet;
import com.envisprototype.view.processing.EnvisPApplet;

public class SaveMapBtnListener extends AbstractEnvisButtonListener{

	//CoordinateWriter output;
	public SaveMapBtnListener(){
		//output = new CoordinateWriter(epApplet);
	}
	
	@Override
	public void handleEnvisClassEvent(EventObject e) {
		// TODO Auto-generated method stub
		super.handleEnvisClassEvent(e);
		if(ifHitTheButton()){
			// closing figure
			String mapId = eButton.getEpApplet().getEnvisMap().getMapId();
			eButton.getEpApplet().getEnvisMap().closeFigure();
			if(eButton.getEpApplet().getEnvisMap().isIfCentered()){
				Coordinates coorsToSave = eButton.getEpApplet().getEnvisMap().getRealCoors();
				Log.i("zzz",Integer.toString(eButton.getEpApplet().getEnvisMap().getCOOR_Z()));
				MapLocalDBHelper.getSingletonInstance(eButton.getEpApplet()).
				saveCoorsForMap(mapId,coorsToSave, eButton.getEpApplet().getEnvisMap().getCOOR_Z());
				eButton.setText(eButton.getEpApplet().getString(R.string.saved));
				//MapInfoDBHelper.editMap(MapListModel.getSingletonInstance().findMapById(mapId));
//				if(eButton.getEpApplet() instanceof DrawMapApplet)
//					eButton.getEpApplet().getEnvisMap().translateToMiddle();
			}
			//eButton.getEpApplet().getEnvisMap().translateToMiddle();
		}
		}

	
	
	
}
