package com.example.bicycle;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class BuildAct extends Activity {

	Button timeBut;
	int time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buildact);
		time = 0;
	
		timeBut = (Button)findViewById(R.id.BuildAct_Time);
		timeBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {							
	            DatePickerDialog datePickerDialog = new DatePickerDialog(BuildAct.this, 
	            		mDateSetListener,2014,5,1 );
	            datePickerDialog.show();  
			}
		});
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			
			time = year%100;
			time = time*100 + monthOfYear + 1;
			time = time*100 + dayOfMonth;
			TimePickerDialog timePickerDialog  = new TimePickerDialog(BuildAct.this, mTimeSetListener, 9, 00, false);
			timePickerDialog.show();
			
		}
	};
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			time = time * 100 + hourOfDay;
			time = time * 100 + minute;
			String temp = "";
			temp += time/100000000 + "年" + time/1000000%100 + "月" + time/10000%100 + "日";
			temp += time/100%100 + "时" + time%100 + "分";
			timeBut.setText(temp);
		}}; 
	
}
