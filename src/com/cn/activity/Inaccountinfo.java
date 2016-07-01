package com.cn.activity;

import java.util.List;

import com.cn.dao.InaccountDao;
import com.cn.model.Tb_inaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Inaccountinfo extends Activity {
	private static final String FLAG="id";
	private TextView inTotal;
	private ListView lvinfo;
	private String strType="";
	private LinearLayout total_layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inaccountinfo);
		
		lvinfo=(ListView) findViewById(R.id.listInaccountinfo);
		inTotal=(TextView) findViewById(R.id.inTotal);
		total_layout=(LinearLayout) findViewById(R.id.total_layout);
		ShowInfo(R.id.btininfo);
		
		lvinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String strInfo=String.valueOf(((TextView) view).getText());
				String strid=strInfo.substring(0, strInfo.indexOf('|'));
				Intent intent=new Intent(Inaccountinfo.this,InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strType});
				startActivity(intent);
			}
		});
	}
	
	private void ShowInfo(int intType){
		String[] strInfos=null;
		ArrayAdapter<String> arrayAdapter=null;
		strType="btininfo";
		InaccountDao inaccountinfo=new InaccountDao(Inaccountinfo.this);
		List<Tb_inaccount> listinfos=inaccountinfo.getScrollData(0, (int) inaccountinfo.getCount());
		strInfos=new String[listinfos.size()];
		int m=0;
		double num=0;
		for(Tb_inaccount tb_inaccount : listinfos){
			strInfos[m]=tb_inaccount.get_id()+"|"+tb_inaccount.getType()+" "
								+String.valueOf(tb_inaccount.getMoney())+"Ԫ     	"+tb_inaccount.getTime();
			num=num+tb_inaccount.getMoney();
			inTotal.setText(num+" Ԫ");
			m++;		
		}
		if(num > 0){
			total_layout.setVisibility(View.VISIBLE);
		}else{
			total_layout.setVisibility(View.INVISIBLE);
		}
		arrayAdapter=new ArrayAdapter<String>(Inaccountinfo.this, android.R.layout.simple_list_item_1,strInfos);
		lvinfo.setAdapter(arrayAdapter);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		ShowInfo(R.id.btininfo);
	}
}
