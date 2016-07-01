package com.cn.activity;

import com.cn.dao.PwdDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	private EditText editLogin;
	private Button btLogin;
	private Button btCancle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		editLogin=(EditText) findViewById(R.id.editlogin);
		btLogin=(Button) findViewById(R.id.bt_login);
		
		btLogin.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,MainActivity.class);
				PwdDao pwdDao=new PwdDao(LoginActivity.this);
				if(pwdDao.getCount()==0||pwdDao.find().getPassword().isEmpty()
						&&editLogin.getText().toString().isEmpty()){
					startActivity(intent);
				}else{
					if(pwdDao.find().getPassword().equals(editLogin.getText().toString())){
						startActivity(intent);
					}else{
						Toast.makeText(LoginActivity.this, "密码有误，请重新输入！", Toast.LENGTH_SHORT).show();
					}
				}
				editLogin.setText("");
			}
		});
		
		btCancle=(Button) findViewById(R.id.bt_cancel);
		btCancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	
}
