package com.example.bicycle;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity {

	Timer timer = new Timer();
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				SharedPreferences sharedPreferences = getSharedPreferences("cookie", MODE_PRIVATE);
				int cookie = sharedPreferences.getInt("cookie", 0);
				
				Intent intent;
				if (cookie == 0) {
					intent = new Intent(MainActivity.this, LoginAct.class);
				} else {
					intent = new Intent(MainActivity.this, ActionAct.class);
				}
				
			    startActivity(intent);
			    finish();
			    break;
			}
			super.handleMessage(msg);
		}
	};
	TimerTask task = new TimerTask(){
		public void run() {
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		timer.schedule(task, 1000); 		
	}

}