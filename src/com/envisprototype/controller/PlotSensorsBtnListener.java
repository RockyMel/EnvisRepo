package com.envisprototype.controller;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.envisprototype.R;
import com.envisprototype.model.sensor.SensorListModel;
import com.envisprototype.view.ThreeDVisualisation;
import com.envisprototype.view.processing.SetPlotPApplet;

public class PlotSensorsBtnListener implements OnClickListener{
        ArrayList<String> sensorIds;
        String mapId;
        
        public PlotSensorsBtnListener(ArrayList<String> sensorIds, String mapId){
                this.sensorIds = sensorIds;
                this.mapId = mapId;
        }

        @Override
        public void onClick(View v) {
                // TODO Auto-generated method stub
                System.out.println("plotting btn pressed");
                Iterator<String> iterator = sensorIds.iterator();
                while(iterator.hasNext()){
                        String id = iterator.next();
                        if(!SensorListModel.getSingletonInstance().findSensorById(id).isIftoPlot()){
                                iterator.remove();
                        }
                }
                Intent intent = new Intent(v.getContext(),SetPlotPApplet.class);
                intent.putExtra(v.getContext().getString(R.string.map_id_extra), mapId);
                intent.putExtra(v.getContext().getString(R.string.flags),
                                v.getContext().getString(R.string.plot_flag_extra));
                intent.putStringArrayListExtra(v.getContext().getString(R.string.sensor_id_extra), sensorIds);
                v.getContext().startActivity(intent);
        }

}