package com.envisprototype.view.model;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.envisprototype.R;
import com.envisprototype.LocalDBHelper.MapSensorAssociationDBHelper;
import com.envisprototype.LocalDBHelper.MapSetAssociationDBHelper;
import com.envisprototype.controller.OnSensorPlotCheckBoxChangedListener;
import com.envisprototype.controller.SensorViewButtonController;
import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.model.set.SetListModel;

public class Set_SensorListAdapter extends ArrayAdapter<SensorInterface>{

	Context context;
	String setid;
	
	public Set_SensorListAdapter(Context context, int textViewResourceId,
			List<SensorInterface> objects,String setid) {
		super(context, textViewResourceId, objects);
		this.context=context;
		this.setid=setid;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(inflatedView==null){
			LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflatedView=inflater.inflate(R.layout.row_adapter_for_sensor_list_in_setinfoscreen, null);

		}
		SensorInterface sensor = getItem(position);
		
		TextView sensorname =(TextView) inflatedView.findViewById(R.id.Name);
		sensorname.setText(sensor.getName());
		TextView id =(TextView) inflatedView.findViewById(R.id.ID);
		id.setText(sensor.getId());
		CheckBox plotSensorCb = (CheckBox) inflatedView.findViewById(R.id.plot_sensor_cb);
		if(MapSetAssociationDBHelper.getSingletoneInstance(context).getCoordsForSet(setid) == null){
			plotSensorCb.setVisibility(View.INVISIBLE);
			
		}
		else{
			plotSensorCb.setVisibility(View.VISIBLE);
			plotSensorCb.setOnCheckedChangeListener(new OnSensorPlotCheckBoxChangedListener(sensor));
		}
		//Button del = (Button)inflatedView.findViewById(R.id.del);
		//del.setOnClickListener(new DeleteSensorButtonController(sensor.getId(),setid,context));
		
		Button edit = (Button)inflatedView.findViewById(R.id.edit);
		Log.i("Yahaan Par 1", sensor.getSetid());
		edit.setOnClickListener(new SensorViewButtonController(sensor.getId(),sensor.getSetid(),context));
		
		
		return inflatedView;
	}
	

}
