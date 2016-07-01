package com.cn.activity;

import java.util.Calendar;

import com.cn.dao.InaccountDao;
import com.cn.model.Tb_inaccount;

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

public class AddInaccount extends Activity {
	private static final int DATE_DIALOG_ID=0;
	private EditText txtInMoney;
	private EditText txtInTime;
	private EditText txtInHandler;
	private EditText txtInMark;
	private Spinner spInType;
	private Button btInSave;
	private Button btInCancel;
	private int mYear;
	private int mMonth;
	private int mDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addinaccount);
		
		txtInMoney=(EditText) findViewById(R.id.txtInMoney);
		txtInTime=(EditText) findViewById(R.id.txtInTime);
		txtInHandler=(EditText) findViewById(R.id.txtInHandler);
		txtInMark=(EditText) findViewById(R.id.txtInMark);
		spInType=(Spinner) findViewById(R.id.spInType);
		btInSave=(Button) findViewById(R.id.btInSave);
		btInCancel=(Button) findViewById(R.id.btInCancel);
		
		btInSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strInMoney=txtInMoney.getText().toString();
				if(!strInMoney.isEmpty()){
					InaccountDao inaccountDao=new InaccountDao(AddInaccount.this);
					Tb_inaccount tb_inaccount=new Tb_inaccount(inaccountDao.getMaxId()+1,
							Double.parseDouble(strInMoney),txtInTime.getText().toString(),
							spInType.getSelectedItem().toString(),txtInHandler.getText().toString(),
							txtInMark.getText().toString());
					inaccountDao.add(tb_inaccount);
					Toast.makeText(AddInaccount.this, "【新增收入】 数据添加成功！", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(AddInaccount.this, "金额不能为空，请输入收入金额！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btInCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtInMoney.setText("");
				txtInMoney.setHint("0.00");
				txtInTime.setText("");
				txtInTime.setHint("2015-01-01");
				spInType.setSelection(0);
				txtInHandler.setText("");
				txtInMark.setText("");
			}
		});
		
		txtInTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);// 显示日期选择对话框
			}
		});
		
		Calendar c=Calendar.getInstance();
		mYear=c.get(Calendar.YEAR);
		mMonth=c.get(Calendar.MONTH);
		mDay=c.get(Calendar.DAY_OF_MONTH);
		upDate();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch(id){
		case DATE_DIALOG_ID:
			return new DatePickerDialog(AddInaccount.this, mDateSetListener, mYear, mMonth, mDay);
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
			upDate();
		}
	};
	
	private void upDate(){
		txtInTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
	}
}
