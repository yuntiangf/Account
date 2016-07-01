package com.cn.activity;

import com.cn.dao.FlagDao;
import com.cn.model.Tb_flag;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Accountflag extends Activity {
	private EditText txtflag;
	private Button btflagSave,btflagCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountflag);
		
		txtflag=(EditText) findViewById(R.id.txtflag);
		btflagSave=(Button) findViewById(R.id.btflagSave);
		btflagCancel=(Button) findViewById(R.id.btflagCancel);
		
		btflagSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strflag=txtflag.getText().toString();
				if(!strflag.isEmpty()){
					FlagDao flagDao=new FlagDao(Accountflag.this);
					Tb_flag tb_flag=new Tb_flag(flagDao.getMaxId()+1,strflag);
					flagDao.add(tb_flag);
					Toast.makeText(Accountflag.this, "【新增便签】保存成功！", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(Accountflag.this, "请输入便签！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btflagCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtflag.setText("");
			}
		});
	}

}
