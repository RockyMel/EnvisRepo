package com.envisprototype.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TimePicker;

import com.envisprototype.R;
import com.envisprototype.controller.Show3DMapBtnListener;
import com.envisprototype.controller.ShowChartVisualizationButtonController;
import com.envisprototype.view.navigation.NavigationMaker;
import com.envisprototype.view.model.ChartVisualizationSettingsModel;
import com.envisprototype.view.model.ChartVisualizationSettingsListAdapter;


public class ChartVisualizationSettingsActivity extends EnvisActivity {

	Button DateFromPickerButton;
	Button DateToPickerButton;
	Button TimeFromPickerButton;
	Button TimeToPickerButton;
	Button SensorsByType;
	Button SetsButton;
	Button QRButton;
	Switch RealTimeSwitch;
	Button VisualizationButton;
	ListView sensorlist;
	
	String mapId = null;

	protected static final int From_DATE_PICKER_DIALOG = 0;
	protected static final int To_DATE_PICKER_DIALOG = 1;
	protected static final int From_TIME_PICKER_DIALOG = 2;
	protected static final int To_TIME_PICKER_DIALOG = 3;

	private static final String TIME_FORMAT = "kk:mm";

	final Calendar calfrom=Calendar.getInstance();
	final Calendar calto=Calendar.getInstance();

	int MODE=0;
	boolean if3DVis = false;

	ArrayList<String> SetIds = new ArrayList<String>();
	ArrayList<String> SensorIds = new ArrayList<String>();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart_visualization_settings);
		init();
		

	}


	private void init() {
		// TODO Auto-generated method stub
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			if(bundle.containsKey(getString(R.string.flags))){
				if(bundle.getString(getString(R.string.flags)).equals(getString(R.string.three_d_vis_flag_extra))){
					if3DVis = true;
				}
			}
			if(bundle.containsKey(getString(R.string.map_id_extra)))
				mapId = bundle.getString(getString(R.string.map_id_extra));
		}
		DateFromPickerButton =(Button)findViewById(R.id.DateFromButton);
		DateToPickerButton = (Button)findViewById(R.id.DateToButton);
		TimeFromPickerButton =(Button)findViewById(R.id.TimeFromButton);
		TimeToPickerButton = (Button)findViewById(R.id.TimeToButton);
		SensorsByType = (Button)findViewById(R.id.SensorsByTypeButton);
		SetsButton = (Button)findViewById(R.id.SetsButton);
		QRButton = (Button)findViewById(R.id.QR);
		VisualizationButton = (Button)findViewById(R.id.VisualizationButton);
		sensorlist = (ListView)findViewById(R.id.chosensensorslist);
		if(if3DVis == false)
			VisualizationButton.setOnClickListener(new ShowChartVisualizationButtonController(this,SetIds,SensorIds,MODE,calfrom,calto));
		else{
			VisualizationButton.setOnClickListener(new Show3DMapBtnListener(this, mapId, ChartVisualizationSettingsModel.getSingletonInstance().getSetIDs(),ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs(),MODE,calfrom,calto));
		}
		final Context context = this;
		
SetsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(context,SetListActivity.class);
				Intent intent = new Intent(context,SetsForMapExpandableListView.class);
				if(mapId!= null){
					intent.putExtra(v.getContext().getString(R.string.map_id_extra), mapId);
					intent.putExtra((v.getContext().getString(R.string.flags)), v.getContext().getString(R.string.sets_to_vis_extra));
				}
				v.getContext().startActivity(intent);
			}
		});

		SensorsByType.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,SensorsExpandableListView.class);
				if(mapId != null){
					intent.putExtra(v.getContext().getString(R.string.map_id_extra), mapId);
				}
				v.getContext().startActivity(intent);
			}
			
		});

		RealTimeSwitch = (Switch)findViewById(R.id.switchforrealtime);
		RealTimeSwitch.setChecked(false);

		RealTimeSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					DateFromPickerButton.setVisibility(Button.INVISIBLE);
					DateToPickerButton.setVisibility(Button.INVISIBLE);
					TimeFromPickerButton.setVisibility(Button.INVISIBLE);
					TimeToPickerButton.setVisibility(Button.INVISIBLE);
					MODE = 1;
					VisualizationButton.setOnClickListener(new ShowChartVisualizationButtonController(context,SetIds,SensorIds,MODE));
				} else {
					DateFromPickerButton.setVisibility(Button.VISIBLE);
					DateToPickerButton.setVisibility(Button.VISIBLE);
					TimeFromPickerButton.setVisibility(Button.VISIBLE);
					TimeToPickerButton.setVisibility(Button.VISIBLE);
					MODE = 0;
					VisualizationButton.setOnClickListener(new ShowChartVisualizationButtonController(context,SetIds,SensorIds,MODE,calfrom,calto));
				}

			}
		});

		DateFromPickerButton.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(From_DATE_PICKER_DIALOG);
			}

		}); 


		DateToPickerButton.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(To_DATE_PICKER_DIALOG);
			}

		}); 

		TimeToPickerButton.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(To_TIME_PICKER_DIALOG);
			}

		}); 

		TimeFromPickerButton.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(From_TIME_PICKER_DIALOG);
			}

		}); 

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chart_visualization_settings, menu);
		return true;
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{

		switch(id) {
		case From_DATE_PICKER_DIALOG:
			return showDateFromPicker();
		case To_DATE_PICKER_DIALOG:
			return showDateToPicker();
		case From_TIME_PICKER_DIALOG:
			return showTimeFromPicker();
		case To_TIME_PICKER_DIALOG:
			return showTimeToPicker();

		}

		return super.onCreateDialog(id);


	}


	private Dialog showTimeToPicker() {
		// TODO Auto-generated method stub
		TimePickerDialog timePicker =new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {



			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub

				calto.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calto.set(Calendar.MINUTE, minute);
				setTimeButtontoTime(calto);




			}},calto.get(Calendar.HOUR_OF_DAY),calto.get(Calendar.MINUTE),true);


		return timePicker;
	}


	protected void setTimeButtontoTime(Calendar calto) {
		// TODO Auto-generated method stub
		SimpleDateFormat timeformat= new SimpleDateFormat(TIME_FORMAT);
		String timeForButton= timeformat.format(calto.getTime());
		TimeToPickerButton.setText(timeForButton);
	}


	private Dialog showTimeFromPicker() {
		// TODO Auto-generated method stub
		TimePickerDialog timePicker =new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {



			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub

				calfrom.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calfrom.set(Calendar.MINUTE, minute);
				setTimeButtonfromTime(calfrom);




			}},calfrom.get(Calendar.HOUR_OF_DAY),calfrom.get(Calendar.MINUTE),true);


		return timePicker;	
	}
	protected void setTimeButtonfromTime(Calendar calfrom) {
		// TODO Auto-generated method stub
		SimpleDateFormat timeformat= new SimpleDateFormat(TIME_FORMAT);
		String timeForButton= timeformat.format(calfrom.getTime());
		TimeFromPickerButton.setText(timeForButton);
	}

	private Dialog showDateFromPicker() {
		// TODO Auto-generated method stub
		DatePickerDialog datePicker =new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {

				calfrom.set(Calendar.YEAR, year);
				calfrom.set(Calendar.MONTH,monthOfYear);
				calfrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);

				setDateButtonFromDate(calfrom);
				// TODO Auto-generated method stub

			}},calfrom.get(Calendar.YEAR),calfrom.get(Calendar.MONTH),calfrom.get(Calendar.DAY_OF_MONTH));


		return datePicker;
	}


	protected void setDateButtonFromDate(Calendar date) {
		// TODO Auto-generated method stub
		DateFromPickerButton.setText(java.text.DateFormat.getDateInstance().format(
				date.getTime()));
	}


	private Dialog showDateToPicker() {
		// TODO Auto-generated method stub
		DatePickerDialog datePicker =new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {

				calto.set(Calendar.YEAR, year);
				calto.set(Calendar.MONTH,monthOfYear);
				calto.set(Calendar.DAY_OF_MONTH, dayOfMonth);

				setDateButtonToDate(calto);
				// TODO Auto-generated method stub

			}},calto.get(Calendar.YEAR),calto.get(Calendar.MONTH),calto.get(Calendar.DAY_OF_MONTH));


		return datePicker;	
	}


	protected void setDateButtonToDate(Calendar date) {
		// TODO Auto-generated method stub
		DateToPickerButton.setText(java.text.DateFormat.getDateInstance().format(
				date.getTime()));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ChartVisualizationSettingsListAdapter cvsla = new ChartVisualizationSettingsListAdapter(this,0,ChartVisualizationSettingsModel.getSingletonInstance().getSensorIDs());

		sensorlist.setAdapter(cvsla);
		

	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(MODE==1)
		{
			ChartVisualizationSettingsModel.getSingletonInstance().setRealtimeornot(true);
		}
		else
			ChartVisualizationSettingsModel.getSingletonInstance().setRealtimeornot(false);

		ChartVisualizationSettingsModel.getSingletonInstance().setFrom(this.calfrom);
		ChartVisualizationSettingsModel.getSingletonInstance().setTo(this.calto);
		
		
		
		
		
		
	}

}
