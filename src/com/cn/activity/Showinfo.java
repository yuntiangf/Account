package com.cn.activity;

import java.util.List;

import com.cn.dao.FlagDao;
import com.cn.dao.InaccountDao;
import com.cn.dao.OutaccountDao;
import com.cn.model.Tb_flag;
import com.cn.model.Tb_inaccount;
import com.cn.model.Tb_outaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Showinfo extends Activity {
	public static final String FLAG="id";
	private TextView total;
	private Button btininfo,btoutinfo,btflaginfo;
	private ListView lvinfo;
	private String strType="";
	private LinearLayout total_layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showinfo);
		
		lvinfo=(ListView) findViewById(R.id.listinfo);
		total=(TextView) findViewById(R.id.total);
		btininfo=(Button) findViewById(R.id.btininfo);
		btoutinfo=(Button) findViewById(R.id.btoutinfo);
		btflaginfo=(Button) findViewById(R.id.btflaginfo);
		total_layout=(LinearLayout) findViewById(R.id.total_layout);
		
		ShowInfo(R.id.btoutinfo);
		
		btoutinfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowInfo(R.id.btoutinfo);
			}
		});
		
		btininfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowInfo(R.id.btininfo);
			}
		});
		
		btflaginfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowInfo(R.id.btflaginfo);
				total_layout.setVisibility(View.INVISIBLE);
			}
		});
		
		lvinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String strInfo=String.valueOf(((TextView) view).getText());
				String strid=strInfo.substring(0, strInfo.indexOf('|'));
				Intent intent=null;
				if(strType=="btininfo" | strType=="btoutinfo"){
					intent=new Intent(Showinfo.this,InfoManage.class);
					intent.putExtra(FLAG, new String[]{strid,strType});
				}else if(strType.equals("btflaginfo")){
					intent=new Intent(Showinfo.this,FlagManage.class);
					intent.putExtra(FLAG, strid);
				}
				startActivity(intent);
			}
		});
	}
	
	private void ShowInfo(int intType){
		String[] strInfos=null;
		ArrayAdapter<String> arrayAdapter=null;
		double num=0;
		switch(intType){
		case R.id.btoutinfo:
			strType="btoutinfo";
			OutaccountDao outaccountinfo=new OutaccountDao(Showinfo.this);
			List<Tb_outaccount> listoutinfos=outaccountinfo.getScrollData(0, (int) outaccountinfo.getCount());
			strInfos=new String[listoutinfos.size()];
			int i=0;
			for(Tb_outaccount tb_outaccount : listoutinfos){
				strInfos[i] = tb_outaccount.get_id()+"|"+tb_outaccount.getType()+"	"
							+String.valueOf(tb_outaccount.getMoney())+"元	"+tb_outaccount.getTime();
				num=num+tb_outaccount.getMoney();
				total.setText(num+"元");
				i++;
			}
			if(num > 0){
				total_layout.setVisibility(View.VISIBLE);
			}else{
				total_layout.setVisibility(View.INVISIBLE);
			}
			break;
		case R.id.btininfo:
			strType="btininfo";
			InaccountDao inaccountinfo=new InaccountDao(Showinfo.this);
			List<Tb_inaccount> listininfos=inaccountinfo.getScrollData(0, (int) inaccountinfo.getCount());
			strInfos=new String[listininfos.size()];
			int m=0;
			for(Tb_inaccount tb_inaccount : listininfos){
				strInfos[m] =tb_inaccount.get_id()+"|"+tb_inaccount.getType()+"	"
							+String.valueOf(tb_inaccount.getMoney())+"元	"+tb_inaccount.getTime();
				num=num+tb_inaccount.getMoney();
				total.setText(num+" 元");
				m++;
			}
			if(num > 0){
				total_layout.setVisibility(View.VISIBLE);
			}else{
				total_layout.setVisibility(View.INVISIBLE);
			}
			break;
		case R.id.btflaginfo:
			strType="btflaginfo";
			FlagDao flaginfo=new FlagDao(Showinfo.this);
			List<Tb_flag> listflags=flaginfo.getScrollData(0, (int) flaginfo.getCount());
			strInfos=new String[listflags.size()];
			int n=0;
			for(Tb_flag tb_flag : listflags){
				strInfos[n] =tb_flag.get_id()+"|"+tb_flag.getFlag();
				if(strInfos[n].length()>20){
					strInfos[n]=strInfos[n].substring(0, 20)+"……";
				}
				n++;
			}
			break;
		}
		arrayAdapter =new ArrayAdapter<String>(Showinfo.this, android.R.layout.simple_list_item_1,strInfos);
		lvinfo.setAdapter(arrayAdapter);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		ShowInfo(R.id.btoutinfo);
	}
	
	
}
