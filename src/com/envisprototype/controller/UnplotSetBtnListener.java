package com.envisprototype.controller;

import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.envisprototype.model.DBHelper.MapInfoDBHelper;
import com.envisprototype.model.LocalDBHelper.MapSensorAssociationDBHelper;
import com.envisprototype.model.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.model.maps.MapListModel;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.sensor.SensorListModel;

public class UnplotSetBtnListener implements OnClickListener{
        
        String setId;
        ImageButton btnToHide;

        public UnplotSetBtnListener(String setid, ImageButton plotSensors) {
                // TODO Auto-generated constructor stub
                this.setId = setid;
                this.btnToHide = plotSensors;
        }

        @Override
        public void onClick(View v) {
        	if(!Checks.isOnline(v.getContext()))
    			return;
                // TODO Auto-generated method stub
                MapSetAssociationDBHelper.getSingletoneInstance(v.getContext()).removeAssociation(setId);
                List<SensorInterface> sensorsToUnplot = SensorListModel.getSingletonInstance().getSensorListBySetID(setId);
                for(SensorInterface sensor: sensorsToUnplot){
                        MapSensorAssociationDBHelper.getSingletoneInstance(v.getContext()).removeAssociation(sensor.getId());
                }
                Thread thread = new Thread()
        		{
        			@Override
        			public void run() {
        				MapInfoDBHelper.unplotSetFromMap(setId);
        			}
        		};
        		thread.start();
                btnToHide.setVisibility(Button.INVISIBLE);
        }

}