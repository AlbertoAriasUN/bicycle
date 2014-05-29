package com.example.bicycle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActionAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionact);		
		
		Button buildButton = (Button)findViewById(R.id.ActionAct_Build);
		buildButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 填写订单信息界面				
				Intent intent = new Intent(ActionAct.this, BuildAct.class);  
			    startActivity(intent); 
			}
		});
		
		Button SearchButton = (Button)findViewById(R.id.ActionAct_Search);
		SearchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 司机
				Intent intent = new Intent(ActionAct.this, SearchAct.class);  
			    startActivity(intent); 
			}
		});
		
		Button OrderButton = (Button)findViewById(R.id.ActionAct_Oreder);
		OrderButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 订单
				Intent intent = new Intent(ActionAct.this, OrderAct.class);  
			    startActivity(intent); 
			}
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return true;
      }
}
