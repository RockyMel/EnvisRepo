package com.envisprototype.controller;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.envisprototype.model.sensor.SensorInterface;
import com.envisprototype.view.ChooseSensorTypeActivity;

public class AddSensorButtonController implements OnClickListener {
	String setid;
	List<SensorInterface> sli;
	String flag;
	Context context;
	private EditText id;
	public AddSensorButtonController(String setid,
			List<SensorInterface> sli, String flag,
			Context context) {
		// TODO Auto-generated constructor stub
		
		this.setid=setid;
		this.sli=sli;
		this.flag=flag;
		this.context=context;
	}

	public AddSensorButtonController(EditText id, List<SensorInterface> list,
			String flag2, Context context) {
		// TODO Auto-generated constructor stub
		this.id =id;
		this.setid=setid;
		this.sli=sli;
		this.flag=flag;
		this.context=context;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
			Intent intent=new Intent(view.getContext(),ChooseSensorTypeActivity.class);
			//intent.putExtra("mode", "sensor");
			intent.putExtra("setid",id.getText().toString());
			//Log.i("test", setid);
			//intent.putExtra("",id);
			
			view.getContext().startActivity(intent);
		
		
	}

}
