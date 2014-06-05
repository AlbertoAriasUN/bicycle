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
import android.widget.Toast;

public class LoginAct extends Activity {
	private static final String SERVERIP = "172.18.159.219";
	private static final int SERVERPORT = 10000;
	private Socket mSocket = null;
	private ObjectOutputStream out = null;
	private DataInputStream in = null;
	private LoginData loginData = new LoginData();

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
				//��½��ť�Ĵ�������
				loginData.name = editUserName.getText().toString();
				loginData.password = editPassWord.getText().toString();
				
				if (loginData.name.equals("") || loginData.password.equals(""))  {
					Toast.makeText(LoginAct.this, "�û��������벻��Ϊ��", Toast.LENGTH_LONG).show();
				} else {
					
					new Thread(new Runnable() {
						public void run() {
							try {
								mSocket = new Socket(SERVERIP, SERVERPORT);
								
								out = new ObjectOutputStream(mSocket.getOutputStream());
								in = new DataInputStream(new BufferedInputStream(mSocket.getInputStream()));
								
								out.writeObject(loginData);
								out.flush();
							//	out.close();
									
								int cookie = in.readInt();
							//	in.close();
								
								SharedPreferences sharedPreferences = getSharedPreferences("cookie", MODE_PRIVATE);
								Editor editor = sharedPreferences.edit();
								editor.putInt("cookie", cookie);
								editor.commit();
								
								Intent intent = new Intent(LoginAct.this, ActionAct.class);  
							    startActivity(intent);
							    finish();
							} catch (UnknownHostException e) {
								// TODO �Զ����ɵ� catch ��
								e.printStackTrace();
							} catch (IOException e) {
								// TODO �Զ����ɵ� catch ��
								e.printStackTrace();
							}
							
						}
					}).start();
					
				}
			}
		});
		
		//ע��
		Button registerBut = (Button)findViewById(R.id.LoginAct_Register);
		registerBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ע�ᰴť�Ĵ�������
				Intent intent = new Intent(LoginAct.this,RegisterAct.class);  
			    startActivity(intent); 
			}
		});
	}

}
