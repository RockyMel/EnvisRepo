package com.envisprototype.controller;


import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.envisprototype.model.sensor.SensorInterface;

public class OnSensorPlotCheckBoxChangedListener implements OnCheckedChangeListener{
        SensorInterface sensor;
        
        
        public OnSensorPlotCheckBoxChangedListener(SensorInterface sensor){
                this.sensor = sensor;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                sensor.setIftoPlot(isChecked);
        }

}