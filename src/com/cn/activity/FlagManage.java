package com.cn.activity;

import com.cn.dao.FlagDao;
import com.cn.model.Tb_flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FlagManage extends Activity {
	private EditText txtflagManage;
	private Button btflagEdit,btflagDel;
	private String strid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flagmanage);
		
		txtflagManage=(EditText) findViewById(R.id.txtFlagManage);
		btflagEdit=(Button) findViewById(R.id.btflagEdit);
		btflagDel=(Button) findViewById(R.id.btflagDel);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		strid=bundle.getString(Showinfo.FLAG);
		final FlagDao flagDao=new FlagDao(FlagManage.this);
		txtflagManage.setText(flagDao.find(Integer.parseInt(strid)).getFlag());
		btflagEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Tb_flag tb_flag=new Tb_flag();
				tb_flag.set_id(Integer.parseInt(strid));
				tb_flag.setFlag(txtflagManage.getText().toString());
				flagDao.update(tb_flag);
				Toast.makeText(FlagManage.this, "【便签数据】修改成功！", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		btflagDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flagDao.delete(Integer.parseInt(strid));
				Toast.makeText(FlagManage.this, "【便签数据】删除成功！", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}

}
