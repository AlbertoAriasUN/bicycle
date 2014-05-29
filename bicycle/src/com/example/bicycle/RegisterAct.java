package com.example.bicycle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterAct extends Activity {

	int sex = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeract);		
	
		final EditText editUserName=(EditText)findViewById(R.id.RegisterAct_Username);
		final EditText editPassWord=(EditText)findViewById(R.id.RegisterAct_Password);
		final EditText editPhone=(EditText)findViewById(R.id.RegisterAct_Phone);
		RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
		final RadioButton radioMale=(RadioButton)findViewById(R.id.radioMale);
		final RadioButton radioFemale=(RadioButton)findViewById(R.id.radioFemale);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(arg1 == radioMale.getId())
					sex = 1;
				else
					sex = 0;
			}
		});
		
		Button loginButton = (Button)findViewById(R.id.RegisterAct_Login);
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 登陆的触发函数
				Intent intent = new Intent(RegisterAct.this, LoginAct.class);  
			    startActivity(intent); 
			}
		});
		
		Button registerBut = (Button)findViewById(R.id.RegisterAct_Register);
		registerBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//注册的触发函数
				
				String a = "Name:"+editUserName.getText()+"\nPassword:"+editPassWord.getText()+"\nSex:"+sex+"\nPhone:"+editPhone.getText();
				Toast.makeText(RegisterAct.this, a, Toast.LENGTH_SHORT).show();
				Intent intentB = new Intent(RegisterAct.this, ActionAct.class);  
			    startActivity(intentB); 
			}
		}); 
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return true;
      }
}
