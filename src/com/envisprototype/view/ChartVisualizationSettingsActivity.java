package com.envisprototype.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.envisprototype.R;
import com.envisprototype.R.id;
import com.envisprototype.R.layout;
import com.envisprototype.R.menu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TimePicker;


public class ChartVisualizationSettingsActivity extends Activity {

	Button DateFromPickerButton;
	Button DateToPickerButton;
	Button TimeFromPickerButton;
	Button TimeToPickerButton;
	Button SensorsByType;
	Button SetsButton;
	Button QRButton;
	Switch RealTimeSwitch;

	protected static final int From_DATE_PICKER_DIALOG = 0;
	protected static final int To_DATE_PICKER_DIALOG = 1;
	protected static final int From_TIME_PICKER_DIALOG = 2;
	protected static final int To_TIME_PICKER_DIALOG = 3;

	private static final String TIME_FORMAT = "kk:mm";

	final Calendar calfrom=Calendar.getInstance();
	final Calendar calto=Calendar.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart_visualization_settings);
		init();

	}


	private void init() {
		// TODO Auto-generated method stub
		DateFromPickerButton =(Button)findViewById(R.id.DateFromButton);
		DateToPickerButton = (Button)findViewById(R.id.DateToButton);
		TimeFromPickerButton =(Button)findViewById(R.id.TimeFromButton);
		TimeToPickerButton = (Button)findViewById(R.id.TimeToButton);
		SensorsByType = (Button)findViewById(R.id.SensorsByTypeButton);
		SetsButton = (Button)findViewById(R.id.SetsButton);
		QRButton = (Button)findViewById(R.id.QR);
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
				} else {
					DateFromPickerButton.setVisibility(Button.VISIBLE);
					DateToPickerButton.setVisibility(Button.VISIBLE);
					TimeFromPickerButton.setVisibility(Button.VISIBLE);
					TimeToPickerButton.setVisibility(Button.VISIBLE);

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


}