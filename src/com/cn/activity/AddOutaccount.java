package com.cn.activity;

import java.util.Calendar;

import com.cn.dao.OutaccountDao;
import com.cn.model.Tb_outaccount;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddOutaccount extends Activity {
	private static final int DATE_DIALOG_ID=0;
	private EditText txtOutMoney;
	private EditText txtOutTime;
	private EditText txtOutAddress;
	private EditText txtOutMark;
	private Spinner spOutType;
	private Button btOutSave;
	private Button btOutCancel;
	private int mYear;
	private int mMonth;
	private int mDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addoutaccount);
		
		txtOutMoney=(EditText) findViewById(R.id.txtOutMoney);
		txtOutTime=(EditText) findViewById(R.id.txtOutTime);
		txtOutAddress=(EditText) findViewById(R.id.txtOutAddress);
		txtOutMark=(EditText) findViewById(R.id.txtOutMark);
		spOutType=(Spinner) findViewById(R.id.spOutType);
		btOutSave=(Button) findViewById(R.id.btOutSave);
		btOutCancel=(Button) findViewById(R.id.btOutCancel);
		
		btOutSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strOutmoney=txtOutMoney.getText().toString();
				if(!strOutmoney.isEmpty()){
					OutaccountDao outaccountDao=new OutaccountDao(AddOutaccount.this);
					Tb_outaccount tb_outaccount=new Tb_outaccount(
							outaccountDao.getMaxId()+1, Double.parseDouble(strOutmoney), 
							txtOutTime.getText().toString(),spOutType.getSelectedItem().toString(),
							txtOutAddress.getText().toString(), txtOutMark.getText().toString());
					outaccountDao.add(tb_outaccount);
					Toast.makeText(AddOutaccount.this, "【新增支出】 数据添加成功！", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(AddOutaccount.this, "金额不能为空，请输入支出金额！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btOutCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtOutMoney.setText("");
				txtOutMoney.setHint("0.00");
				txtOutTime.setText("");
				txtOutTime.setHint("2015-01-01");
				spOutType.setSelection(0);
				txtOutAddress.setText("");
				txtOutMark.setText("");
			}
		});
		
		txtOutTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		
		Calendar c=Calendar.getInstance();
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
			return new DatePickerDialog(AddOutaccount.this, mDateSetListener, mYear, mMonth, mDay);
		}
		return super.onCreateDialog(id);
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
		txtOutTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
	}
}
