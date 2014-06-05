package com.example.bicycle;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterAct extends Activity {
	private static final String SERVERIP = "172.18.159.219";
	private static final int SERVERPORT = 10000;
	private Socket mSocket = null;
	private ObjectOutputStream out = null;
	private DataInputStream in = null;
	private RegisterData registerData = new RegisterData();
	
	private int sex;
	
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
				else if (arg1 == radioFemale.getId())
					sex = 0;
			}
		});
		
		Button loginButton = (Button)findViewById(R.id.RegisterAct_Login);
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 登陆的触发函数
			//	Intent intent = new Intent(RegisterAct.this, LoginAct.class);  
			//  startActivity(intent);
			    finish();
			}
		});
		
		Button registerBut = (Button)findViewById(R.id.RegisterAct_Register);
		registerBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//注册的触发函数
				registerData.name = editUserName.getText().toString();
				registerData.password = editPassWord.getText().toString();
				registerData.sex = sex;
				registerData.phNum = editPhone.getText().toString();
				
				if (registerData.name.equals("") || registerData.password.equals("")) {
					Toast.makeText(RegisterAct.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
				} else if (registerData.name.length() < 4 || registerData.name.length() > 8 
						|| registerData.password.length() < 4 || registerData.password.length() > 8) {
					Toast.makeText(RegisterAct.this, "用户名或密码长度不符合要求", Toast.LENGTH_LONG).show();
				} else if (registerData.phNum.equals("")) {
					Toast.makeText(RegisterAct.this, "手机号码不能为空", Toast.LENGTH_LONG).show();
				} else {
					new Thread(new Runnable() {
						public void run() {
							try {
								mSocket = new Socket(SERVERIP, SERVERPORT);
								
								out = new ObjectOutputStream(mSocket.getOutputStream());
								in = new DataInputStream(new BufferedInputStream(mSocket.getInputStream()));
								
								out.writeObject(registerData);
								out.flush();
							//	out.close();
									
								int cookie = in.readInt();
							//	in.close();
								
								SharedPreferences sharedPreferences = getSharedPreferences("cookie", MODE_PRIVATE);
								Editor editor = sharedPreferences.edit();
								editor.putInt("cookie", cookie);
								editor.commit();
								
								Intent intent = new Intent(RegisterAct.this, ActionAct.class);  
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
}
