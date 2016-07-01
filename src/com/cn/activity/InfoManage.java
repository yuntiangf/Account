package com.cn.activity;

import java.util.Calendar;

import com.cn.dao.InaccountDao;
import com.cn.dao.OutaccountDao;
import com.cn.model.Tb_inaccount;
import com.cn.model.Tb_outaccount;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InfoManage extends Activity {
	private static final int DATE_DIALOG_ID=0;
	private TextView textTitle,textView;
	private EditText txtMoney,txtTime,txtHA,txtMark;
	private Spinner spType;
	private Button btEdit,btDel;
	private String[] strInfos;
	private String strid,strType;
	
	private int mYear;
	private int mMonth;
	private int mDay;
	
	OutaccountDao outaccountDao=new OutaccountDao(InfoManage.this);
	InaccountDao inaccountDao=new InaccountDao(InfoManage.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infomanage);
		
		textTitle=(TextView) findViewById(R.id.inouttitle);
		textView=(TextView) findViewById(R.id.tvInOut);//获取地点/付款方标签对象
		txtMoney=(EditText) findViewById(R.id.txtInOutMoney);
		txtTime=(EditText) findViewById(R.id.txtInOutTime);
		spType=(Spinner) findViewById(R.id.spInOutType);
		txtHA=(EditText) findViewById(R.id.txtInOut);// 获取地点/付款方文本框
		txtMark=(EditText) findViewById(R.id.txtInOutMark);
		btEdit=(Button) findViewById(R.id.btInOutEdit);
		btDel=(Button) findViewById(R.id.btInOutDelete);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		strInfos=bundle.getStringArray(Showinfo.FLAG);
		strid=strInfos[0];
		strType=strInfos[1];
		if(strType.equals("btoutinfo")){
			textTitle.setText("支出管理");
			textView.setText("	地点:");
			Tb_outaccount tb_outaccount=outaccountDao.find(Integer.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));
			txtTime.setText(tb_outaccount.getTime());
			spType.setPrompt(tb_outaccount.getType());
			txtHA.setText(tb_outaccount.getAddress());
			txtMark.setText(tb_outaccount.getMark());
		}else if(strType.equals("btininfo")){
			textTitle.setText("收入管理");
			textView.setText(" 付款方：");
			Tb_inaccount tb_inaccount=inaccountDao.find(Integer.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));
			txtTime.setText(tb_inaccount.getTime());
			spType.setPrompt(tb_inaccount.getType());
			txtHA.setText(tb_inaccount.getHandler());
			txtMark.setText(tb_inaccount.getMark());
		}
		
		txtTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		
		btEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(strType.equals("btoutinfo")){
					Tb_outaccount tb_outaccount=new Tb_outaccount();
					tb_outaccount.set_id(Integer.parseInt(strid));
					tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
					tb_outaccount.setTime(txtTime.getText().toString());
					tb_outaccount.setType(spType.getSelectedItem().toString());
					tb_outaccount.setAddress(txtHA.getText().toString());
					tb_outaccount.setMark(txtMark.getText().toString());
					outaccountDao.update(tb_outaccount);
				}else if(strType.equals("btininfo")){
					Tb_inaccount tb_inaccount=new Tb_inaccount();
					tb_inaccount.set_id(Integer.parseInt(strid));
					tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
					tb_inaccount.setTime(txtTime.getText().toString());
					tb_inaccount.setType(spType.getSelectedItem().toString());
					tb_inaccount.setHandler(txtHA.getText().toString());
					tb_inaccount.setMark(txtMark.getText().toString());
					inaccountDao.update(tb_inaccount);
				}
				Toast.makeText(InfoManage.this, "【数据】修改成功！", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		btDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(strType.equals("btoutinfo")){
					outaccountDao.delete(Integer.parseInt(strid));
				}else if(strType.equals("btininfo")){
					inaccountDao.delete(Integer.parseInt(strid));
				}
				Toast.makeText(InfoManage.this, "【数据】删除成功！", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		final Calendar c=Calendar.getInstance();
		mYear=c.get(Calendar.YEAR);
		mMonth=c.get(Calendar.MONTH);
		mDay=c.get(Calendar.DAY_OF_MONTH);
		update();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch(id){
		case DATE_DIALOG_ID:
			return new DatePickerDialog(InfoManage.this, mDateSetListener, mYear, mMonth, mDay);
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			mYear=year;
			mMonth=monthOfYear;
			mDay=dayOfMonth;
			update();
		}
	};
	
	private void update(){
		txtTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
	}
}
