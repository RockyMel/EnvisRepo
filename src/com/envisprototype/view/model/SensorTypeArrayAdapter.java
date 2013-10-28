package com.envisprototype.view.model;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.envisprototype.R;
import com.envisprototype.controller.ChosenSensorTypeButtonController;

public class SensorTypeArrayAdapter extends ArrayAdapter<Integer>{

	public final static int AIRQUALITY = 1;
	public final static int HUMIDITY = 2;
	public final static int LIGHT = 3;
	public final static int MOTION = 4;
	public final static int TEMPERATURE = 5;
	public final static int WATERLEVEL = 6;
	public final static int OTHERS = 7;
	
	String setid;
	Activity context;
	public SensorTypeArrayAdapter(Context context, int resource,
			List<Integer> objects, String setid) {
		super(context, resource, objects);
		this.setid = setid;
		this.context = (Activity)context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(inflatedView==null){
			LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflatedView=inflater.inflate(R.layout.adapterforsensortype, null);

		}
		Log.i("setid in array adap", setid);
		Log.i("context in array adap", context +"");
		
		ImageView img = (ImageView)inflatedView.findViewById(R.id.SensorImage);
		TextView Heading = (TextView)inflatedView.findViewById(R.id.SensorHeading);
		TextView Desc = (TextView)inflatedView.findViewById(R.id.SensorDesc);
		ImageButton next = (ImageButton)inflatedView.findViewById(R.id.NextButton);
		int Type = getItem(position);
		switch(Type){

		case AIRQUALITY:img.setImageResource(R.drawable.airquality);
		Heading.setText("AIR QUALITY");
		Desc.setText("Sensor Measuring amount of Carbon Monoxide (CO) level in a closed environment.");
		ChosenSensorTypeButtonController cstbc = new ChosenSensorTypeButtonController(setid,AIRQUALITY, context);
		next.setOnClickListener(cstbc);
		break;
		case HUMIDITY: img.setImageResource(R.drawable.humidity);
		Heading.setText("HUMIDITY");
		Desc.setText("Sensor Measuring amount of Humidity in the surrounding environment.");
		next.setOnClickListener(new ChosenSensorTypeButtonController(setid,HUMIDITY, context));
		break;

		case LIGHT : img.setImageResource(R.drawable.light);
		Heading.setText("LIGHT");
		Desc.setText("Sensor Measuring amount of light environment.");
		next.setOnClickListener(new ChosenSensorTypeButtonController(setid,LIGHT, context));
		break;

		case MOTION: img.setImageResource(R.drawable.ir);
		Heading.setText("MOTION");
		Desc.setText("Sensor detecting interruptions in its projected infra-red beams.");
		next.setOnClickListener(new ChosenSensorTypeButtonController(setid,MOTION, context));
		break;

		case TEMPERATURE: img.setImageResource(R.drawable.temp);
		Heading.setText("TEMPERATURE");
		Desc.setText("Sensor Measuring temperature in celcius in the surrounding environment.");
		next.setOnClickListener(new ChosenSensorTypeButtonController(setid,TEMPERATURE, context));
		break;

		case WATERLEVEL: img.setImageResource(R.drawable.waterlevel);
		Heading.setText("WATER LEVEL");
		Desc.setText("Sensor Measuring the water level.");
		next.setOnClickListener(new ChosenSensorTypeButtonController(setid,WATERLEVEL, context));
		break;

		case OTHERS: img.setImageResource(R.drawable.others);
		Heading.setText("OTHERS");
		Desc.setText("Sensors other than the ones listed above.");
		next.setOnClickListener(new ChosenSensorTypeButtonController(setid,OTHERS, context));
		break;

		default: break;
		}




		return inflatedView;
	}




}
