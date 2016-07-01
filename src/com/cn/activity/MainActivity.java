package com.cn.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	GridView gvinfo;
	String[] titles =new String[] {"新增支出","新增收入","我的支出","我的收入","数据管理","系统设置","收支便签","退出"};
	int[] images =new int[] {R.drawable.addoutaccount,R.drawable.addinaccount,R.drawable.outaccountinfo,
			R.drawable.inaccountinfo,R.drawable.showinfo,R.drawable.sysset,R.drawable.accountflag,R.drawable.exit};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		gvinfo=(GridView) findViewById(R.id.gvinfo);
		pictureAdapter adapter=new pictureAdapter(titles, images, this);
		gvinfo.setAdapter(adapter);
		gvinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=null;
				switch(position){
				case 0:
					intent=new Intent(MainActivity.this,AddOutaccount.class);
					startActivity(intent);
					break;
				case 1:
					intent=new Intent(MainActivity.this,AddInaccount.class);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent(MainActivity.this,Outaccountinfo.class);
					startActivity(intent);
					break;
				case 3:
					intent=new Intent(MainActivity.this,Inaccountinfo.class);
					startActivity(intent);
					break;
				case 4:
					intent=new Intent(MainActivity.this,Showinfo.class);
					startActivity(intent);
					break;
				case 5:
					intent=new Intent(MainActivity.this,Sysset.class);
					startActivity(intent);
					break;
				case 6:
					intent=new Intent(MainActivity.this,Accountflag.class);
					startActivity(intent);
					break;
				case 7:
					finish();
				}
			}
		});
	}
}

 class pictureAdapter extends BaseAdapter{// 创建基于BaseAdapter的子类

	 private LayoutInflater inflater;// 创建LayoutInflater对象
	 private List<Picture> pictures;
	 
	 
	public pictureAdapter(String[] titles, int[] images,Context context) {
		super();
		pictures=new ArrayList<Picture>();
		inflater=LayoutInflater.from(context);
		for (int i = 0; i < images.length; i++) {// 遍历图像数组
			Picture picture=new Picture(titles[i], images[i]);// 使用标题和图像生成Picture对象
			pictures.add(picture);// 将Picture对象添加到泛型集合中
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(pictures!=null){
			return pictures.size();
		}else{
			return 0;
		}
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pictures.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewholder;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.gvitem, null);
			viewholder=new ViewHolder();
			viewholder.title=(TextView) convertView.findViewById(R.id.itemTitle);
			viewholder.image=(ImageView) convertView.findViewById(R.id.itemImage);
			convertView.setTag(viewholder);
		}else{
			viewholder=(ViewHolder) convertView.getTag();
		}
		viewholder.title.setText(pictures.get(position).getTitle());
		viewholder.image.setImageResource(pictures.get(position).getImageId());
		return convertView;
	}
	
}
 
 class ViewHolder{
	 public TextView title;
	 public ImageView image;
 }
 
 class Picture{
	 private String title;
	 private int imageId;
	 
	 public Picture(){
		 super();
	 }
	 
	 public Picture(String title, int imageId) {
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	 
	 
 }
