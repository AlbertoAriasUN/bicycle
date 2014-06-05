package com.example.bicycle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class BuildAct extends Activity {
	private static final String SERVERIP = "172.18.159.219";
	private static final int SERVERPORT = 10000;
	private Socket mSocket = null;
	private ObjectOutputStream out = null;
//	private DataInputStream in = null;
	private OrderReleased orderReleased;
	
	private Button timeBut;
	private Time time;
	private Calendar c;
	private Spinner orginSpinner;
	private Spinner destSpinner;
	
	private static final String[] LOCATIONSA = {"慎思园", "至善园", "明德园", "格致园",
		"公共教学楼", "图书馆", "体育馆", "行政楼", "北实验楼", "南实验楼", "游泳馆", "牌坊"};
	private static final String[] LOCATIONSB = {"公共教学楼", "图书馆", "体育馆", "行政楼",
		"北实验楼", "南实验楼", "游泳馆", "牌坊", "慎思园", "至善园", "明德园", "格致园"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buildact);
		
		time = new Time();
		c = Calendar.getInstance();
		orderReleased = new OrderReleased();

		orginSpinner = (Spinner)findViewById(R.id.BuildAct_LocationA);
		destSpinner = (Spinner)findViewById(R.id.BuildAct_LocationB);
		timeBut = (Button)findViewById(R.id.BuildAct_Time);
		Button buildBut = (Button)findViewById(R.id.BuildAct_Build);
		
		ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LOCATIONSA);
		ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LOCATIONSB);
		adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		orginSpinner.setAdapter(adapterA);
		destSpinner.setAdapter(adapterB);
		
		timeBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
	            TimePickerDialog timePickerDialog  = new TimePickerDialog(BuildAct.this, mTimeSetListener,
						c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
				timePickerDialog.show();
	            DatePickerDialog datePickerDialog = new DatePickerDialog(BuildAct.this, mDateSetListener,
	            		c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	            datePickerDialog.show();
			}
		});
		
		buildBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				orderReleased.time[0] = time.year;
				orderReleased.time[1] = time.month;
				orderReleased.time[2] = time.monthDay;
				orderReleased.time[3] = time.hour;
				orderReleased.time[4] = time.minute;
				orderReleased.origin = LOCATIONSA[orginSpinner.getSelectedItemPosition()];
				orderReleased.dest = LOCATIONSB[destSpinner.getSelectedItemPosition()];
				
				SharedPreferences sharedPreferences = getSharedPreferences("cookie", MODE_PRIVATE);
				orderReleased.cookie = sharedPreferences.getInt("cookie", 0);
				
				Time now = new Time();
				now.setToNow();
				time.allDay = false;
				if (time.before(now))  {
					Toast.makeText(BuildAct.this, "请选择出发时间，出发时间不能提前于当前时间", Toast.LENGTH_LONG).show();
				} else if (orderReleased.origin.equals(orderReleased.dest)) {
					Toast.makeText(BuildAct.this, "出发地点与目的地点不能相同", Toast.LENGTH_LONG).show();
				} else {
					
					new Thread(new Runnable() {
						public void run() {
							try {
								mSocket = new Socket(SERVERIP, SERVERPORT);
								
								out = new ObjectOutputStream(mSocket.getOutputStream());
							//	in = new DataInputStream(new BufferedInputStream(mSocket.getInputStream()));
								
								out.writeObject(orderReleased);
								out.flush();
							//	out.close();
								
							//  and then?
								
								Toast.makeText(BuildAct.this, "订单发布成功", Toast.LENGTH_LONG).show();
								Intent intent = new Intent(BuildAct.this, OrderAct.class);  
							    startActivity(intent);
							    finish();
							} catch (UnknownHostException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							} catch (IOException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							
						}
					}).start();
					
				}
			}
		});
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			time.set(dayOfMonth, monthOfYear, year);
		}
	};
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			time.hour = hourOfDay;
			time.minute = minute;
			String temp = "";
			temp += time.year + "年" + (time.month + 1) + "月" + time.monthDay + "日";
			temp += time.hour + "时" + time.minute + "分";
			timeBut.setText(temp);
		}}; 
	
}
