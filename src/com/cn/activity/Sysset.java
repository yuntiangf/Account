package com.cn.activity;

import com.cn.dao.PwdDao;
import com.cn.model.Tb_pwd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sysset extends Activity {
	private EditText txtPwd;
	private Button btset,btsetCancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sysset);
		
		txtPwd=(EditText) findViewById(R.id.txtPwd);
		btset=(Button) findViewById(R.id.btSet);
		btsetCancel=(Button) findViewById(R.id.btSetCancel);
		
		btset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PwdDao pwdDao=new PwdDao(Sysset.this);
				Tb_pwd tb_pwd=new Tb_pwd(txtPwd.getText().toString());
				if(pwdDao.getCount()==0){
					pwdDao.add(tb_pwd);
				}else{
					pwdDao.update(tb_pwd);
				}
				Toast.makeText(Sysset.this, "°æ√‹¬Î°ø…Ë÷√≥…π¶£°", Toast.LENGTH_SHORT).show();
			}
		});
		
		btsetCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtPwd.setText("");
				txtPwd.setHint("«Î ‰»Î√‹¬Î");
			}
		});
	}
	
}
