package com.cn.activity;

import java.util.List;

import com.cn.dao.OutaccountDao;
import com.cn.model.Tb_outaccount;

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

public class Outaccountinfo extends Activity {
	private static final String FLAG="id";
	private TextView outTotal;
	private ListView lvinfo;
	private String strType="";
	private LinearLayout total_layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outaccountinfo);
		
		lvinfo=(ListView) findViewById(R.id.listOutaccountinfo);
		outTotal=(TextView) findViewById(R.id.outTotal);
		total_layout=(LinearLayout) findViewById(R.id.total_layout);
		ShowInfo(R.id.btoutinfo);
		
		lvinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String strInfo=String.valueOf(((TextView) view).getText());
				String strid=strInfo.substring(0, strInfo.indexOf('|'));
				Intent intent=new Intent(Outaccountinfo.this,InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strType});
				startActivity(intent);
			}
		});
	}
	
	private void ShowInfo(int intType){
		String[] strInfos=null;
		ArrayAdapter<String> arrayAdapter=null;
		strType="btoutinfo";
		OutaccountDao outaccountinfo= new OutaccountDao(Outaccountinfo.this);
		List<Tb_outaccount> listinfos=outaccountinfo.getScrollData(0, (int) outaccountinfo.getCount());
		strInfos=new String[listinfos.size()];
		int m=0;
		double num=0;
		for (Tb_outaccount tb_outaccount : listinfos) {
			strInfos[m]= tb_outaccount.get_id()+"|"+tb_outaccount.getType()+" "+
						String.valueOf(tb_outaccount.getMoney())+"Ԫ		"+tb_outaccount.getTime();
			num=num+tb_outaccount.getMoney();
			outTotal.setText(num+" Ԫ");
			m++;
		}
		if(num > 0){
			total_layout.setVisibility(View.VISIBLE);
		}else{
			total_layout.setVisibility(View.INVISIBLE);
		}
		arrayAdapter=new ArrayAdapter<String>(Outaccountinfo.this, android.R.layout.simple_list_item_1,strInfos);
		lvinfo.setAdapter(arrayAdapter);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		ShowInfo(R.id.btoutinfo);
	}
}
