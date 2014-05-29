package com.example.bicycle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginact);		
		
		final EditText editUserName=(EditText)findViewById(R.id.LoginAct_Username);
		final EditText editPassWord=(EditText)findViewById(R.id.LoginAct_Password);
		
		Button loginButton = (Button)findViewById(R.id.LoginAct_Login);
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//登陆按钮的触发函数
				String a = "Name:" + editUserName.getText() + "\nPassword:" + editPassWord.getText();
				Toast.makeText(LoginAct.this, a, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(LoginAct.this, ActionAct.class);  
			    startActivity(intent); 
			}
		});
		
		//注册
		
		Button registerBut = (Button)findViewById(R.id.LoginAct_Register);
		registerBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 注册按钮的触动动作
				Intent intent = new Intent(LoginAct.this,RegisterAct.class);  
			    startActivity(intent); 
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return true;
      }

}
