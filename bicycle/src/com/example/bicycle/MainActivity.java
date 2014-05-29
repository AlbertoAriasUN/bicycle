package com.example.bicycle;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Timer timer = new Timer();
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
		switch (msg.what) {
		case 1:
			Intent intent = new Intent(MainActivity.this, LoginAct.class);  
		    startActivity(intent); 
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
